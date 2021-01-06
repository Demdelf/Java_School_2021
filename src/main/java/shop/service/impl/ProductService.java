package shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.ProductDao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;
import shop.dto.ProductDto;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements shop.service.ProductService {

    private final ProductDao dao;
    @Autowired
    private shop.service.Service<Category> categoryService;
    @Autowired
    private shop.service.PropertyValueService propertyValueService;
    @Autowired
    private shop.service.Service<Property> propertyService;

    @Override
    public Product findOne(long id) {
        return dao.findOne(id).orElse(null);
    }

    @Override
    public List<Product> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    public Product create(Product product) {
        return dao.create(product);
    }

    @Override
    public Product create(ProductDto dto) {
        Product product = convertProductDtoToNewProduct(dto);
        create(product);
        setProductForPropertyValues(product);
        return product;
    }

    private void setProductForPropertyValues(Product product) {
        for (PropertyValue p: product.getPropertyValues()
        ) {
            p.setProduct(product);
            propertyValueService.create(p);
        }
    }

    @Override
    public ProductDto update(ProductDto productDtoFlash) {
        Product product = convertProductDtoToExistProduct(productDtoFlash);
//        for (PropertyValue p: product.getPropertyValues()
//        ) {
//            p.setProduct(product);
//            propertyValueService.update(p);
//        }
        dao.update(product);
        return convertProductToDto(product);
    }

    private Product convertProductDtoToExistProduct(ProductDto dto) {
        Product product = dao.findOne(dto.getId()).get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setVolume(dto.getVolume());
        product.setWeight(dto.getWeight());
        /**Пока не придумал как добавлять новые значения характеристик при изменении категории
         * в иделале менять на фронте отображение редактируемой таблицы с названиями и значениями характеристик*/
//        Category category = categoryService.findByName(dto.getCategory());
//        product.setCategory(category);
        List<PropertyValue> propertyValues = new ArrayList<>();
        if(dto.getPropertyValues()!=null){
            for (Map.Entry<Long, String> e: dto.getPropertyValues().entrySet()
            ) {
                Property property = propertyService.findOne(e.getKey());

                PropertyValue propertyValue = propertyValueService.findByProductAndProperty(product, property);
                propertyValue.setValue(e.getValue());
                propertyValues.add(propertyValue);
            }
        }
        product.setPropertyValues(propertyValues);
        return product;
    }

    @Override
    public ProductDto convertProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setVolume(product.getVolume());
        productDto.setWeight(product.getWeight());
        productDto.setCategory(product.getCategory().getName());
        Map<Long, String> propertyValues = new HashMap<>();
        for (PropertyValue p: product.getPropertyValues()){
            propertyValues.put(p.getId(), p.getValue());
        }
        productDto.setPropertyValues(propertyValues);
        return productDto;
    }

    private Product convertProductDtoToNewProduct(ProductDto dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setVolume(dto.getVolume());
        product.setWeight(dto.getWeight());

        Map<Long, String> map = new HashMap<>();
        Category category = categoryService.findByName(dto.getCategory());
        product.setCategory(category);

        for (Property p: category.getProperties()
        ) {
            map.put(p.getId(), p.getType());
        }
        dto.setPropertyValues(map);

        List<PropertyValue> propertyValues = new ArrayList<>();
        if(dto.getPropertyValues()!=null){
            for (Map.Entry<Long, String> e: dto.getPropertyValues().entrySet()
            ) {
                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setProperty(propertyService.findOne(e.getKey()));
                propertyValue.setValue(e.getValue());
                propertyValues.add(propertyValue);
            }
        }
        product.setPropertyValues(propertyValues);
        return product;
    }


    @Override
    public void delete(Product product) {

    }

    public Product findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Product update(Product p) {
        return dao.update(p);
    }
}
