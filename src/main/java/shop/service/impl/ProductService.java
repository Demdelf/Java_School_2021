package shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
public class ProductService implements shop.service.Service<Product> {

    private final ProductDao dao;
    private final CategoryService categoryService;
    private final PropertyValueService propertyValueService;

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

    public Product create(ProductDto dto) {
        Product product = convertProductDtoToProduct(dto);
        return create(product);
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
        for (String p: dto.getPropertyValues()){
            PropertyValue propertyValue = propertyValueService.findByName(p);
            if (propertyValue != null){
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
