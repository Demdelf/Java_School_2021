package shop.service.impl;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Product findOne(long id) {
        return null;
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
    public ProductDto create(ProductDto dto) {
        Product product = convertProductDtoToProduct(dto);
        create(product);
        return convertProductToDto(product);
    }

    private ProductDto convertProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setVolume(product.getVolume());
        productDto.setWeight(product.getWeight());
        productDto.setCategory(product.getCategory().getName());
        List<String> propertyValues = new ArrayList<>();
        for (PropertyValue p: product.getPropertyValues()){
            propertyValues.add(p.getName());
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
            for (String p: dto.getPropertyValues()){
                PropertyValue propertyValue = propertyValueService.findByName(p);
                if (propertyValue != null){
                    propertyValues.add(propertyValue);
                }
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
