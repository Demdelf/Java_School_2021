package shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.ProductDao;
import shop.domain.Category;
import shop.domain.OrderProduct;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;
import shop.dto.FilterDto;
import shop.dto.ProductDto;
import shop.dto.ProductsDto;
import shop.util.exception.EmptyStockException;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements shop.service.ProductService {

    private final ProductDao dao;
    @Autowired
    private shop.service.Service<Category> categoryService;
    @Autowired
    private shop.service.PropertyValueService propertyValueService;
    @Autowired
    private shop.service.Service<Property> propertyService;

    @Override
    @Transactional
    public Product findOne(long id) {
        Product product = dao.findOne(id).orElse(null);
        return product;
    }

    @Override
    @Transactional
    public ProductDto getDtoById(long id){
        Product product = dao.findOne(id).orElse(null);
        return convertProductToDto(product);
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
        return dao.findAllByCategory(categoryId);
    }

    @Override
    public List<Product> findAllFiltered(FilterDto filterDto) {
        List<Product> products = findAll(1000);
        return getFilteredProducts(filterDto, products);
    }

    private List<Product> getFilteredProducts(FilterDto filterDto, List<Product> products) {
        List<Product> result = new ArrayList<>();
        for (Product p: products
        ) {
            double price = p.getPrice();
            if (price >= filterDto.getMinPrice() && price <= filterDto.getMaxPrice()){
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public List<Product> findAllByCategoryIdFiltered(Long id, FilterDto filterDto) {
        List<Product> products = findAllByCategoryId(id);
        return getFilteredProducts(filterDto, products);
    }

    @Override
    public List<Product> findAllVisible(int i) {
        return dao.findAllVisible();
    }

    @Override
    public List<Product> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return dao.create(product);
    }

    @Override
    @Transactional
    public Product create(ProductDto dto) {
        Product product = convertProductDtoToNewProduct(dto);
        create(product);
        setProductForPropertyValues(product);
        return product;
    }

    @Transactional
    void setProductForPropertyValues(Product product) {
        for (PropertyValue p: product.getPropertyValues()
        ) {
            p.setProduct(product);
            propertyValueService.create(p);
        }
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDtoFlash) {
        Product product = convertProductDtoToExistProduct(productDtoFlash);
        dao.update(product);
        return convertProductToDto(product);
    }

    @Transactional
    Product convertProductDtoToExistProduct(ProductDto dto) {
        Product product = dao.findOne(dto.getId()).get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setVolume(dto.getVolume());
        product.setVisible(dto.getVisible());
        product.setImage(dto.getImage());
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
    @Transactional
    public ProductDto convertProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setVolume(product.getVolume());
        productDto.setVisible(product.getVisible());
        productDto.setWeight(product.getWeight());
        productDto.setImage(product.getImage());
        productDto.setStringImage(Base64.encode(product.getImage()));
        productDto.setCategory(product.getCategory().getName());
        Map<Long, String> propertyValues = new HashMap<>();
        Map<Long, String> properties = new HashMap<>();
        for (PropertyValue p: product.getPropertyValues()){
            propertyValues.put(p.getProperty().getId(), p.getValue());
            properties.put(p.getProperty().getId(), p.getProperty().getName());
        }
        productDto.setPropertyValues(propertyValues);
        productDto.setProperties(properties);
        return productDto;
    }

    @Transactional
    Product convertProductDtoToNewProduct(ProductDto dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setVolume(dto.getVolume());
        product.setWeight(dto.getWeight());
        product.setVisible(dto.getVisible());
        product.setImage(dto.getImage());
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
    @Transactional
    public void delete(Product product) {

    }

    public Product findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    @Transactional
    public Product update(Product p) {
        return dao.update(p);
    }

    @Transactional
    public Integer updateAfterOrder(ProductDto o, Integer q) throws EmptyStockException {
        Product product = findOne(o.getId());
        int stock = product.getStock();
        if (q <= stock){
            product.setStock(stock - q);
            update(product);
            return q;
        }else if (stock > 0){
            product.setStock(0);
            update(product);
            return stock;
        }else throw new EmptyStockException();
    }

    @Transactional
    public Map<ProductDto, Integer> checkAndUpdateOrderedProducts(Map<ProductDto, Integer> products) {
        List<ProductDto> unableToOrderProduct = new ArrayList<>();
        for (Entry<ProductDto, Integer> e: products.entrySet()
        ) {
            int value = 0;
            try {
                value = updateAfterOrder(e.getKey(), e.getValue());
                e.setValue(value);
                products.put(e.getKey(), e.getValue());
            } catch (EmptyStockException emptyStockException) {
                emptyStockException.printStackTrace();
                unableToOrderProduct.add(e.getKey());
            }
        }
        for (ProductDto p: unableToOrderProduct
        ) {
            products.remove(p);
        }
        return products;
    }
}
