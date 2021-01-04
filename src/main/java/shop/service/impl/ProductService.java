package shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.dao.Dao;
import shop.dao.impl.ProductDao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;
import shop.dto.ProductDto;


@Service
@RequiredArgsConstructor

public class ProductService implements shop.service.ProductService {

    private final ProductDao dao;
    @Autowired
    private shop.service.Service<Category> categoryService;
    @Autowired
    private shop.service.Service<PropertyValue> propertyValueService;
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
        Map<Long, String> map = new HashMap<>();
        Category category = categoryService.findByName(dto.getCategory());
        for (Property p: category.getProperties()
        ) {
            map.put(p.getId(), p.getType());
        }
        dto.setPropertyValues(map);
        Product product = convertProductDtoToProduct(dto);
        create(product);
        for (PropertyValue p: product.getPropertyValues()
        ) {
            p.setProduct(product);
            propertyValueService.create(p);
        }
        return product;
    }

    @Override
    public ProductDto update(ProductDto productDtoFlash) {
        Product product = convertProductDtoToProduct(productDtoFlash);
        dao.update(product);
        return convertProductToDto(product);
    }

    private ProductDto convertProductToDto(Product product) {
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
            propertyValues.put(p.getId(), "");
        }
        productDto.setPropertyValues(propertyValues);
        return productDto;
    }

    private Product convertProductDtoToProduct(ProductDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setVolume(dto.getVolume());
        product.setWeight(dto.getWeight());
        Category category = categoryService.findByName(dto.getCategory());
        product.setCategory(category);
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
}
