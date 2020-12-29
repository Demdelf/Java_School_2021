package shop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dao.Dao;
import shop.dao.impl.PropertyValueDao;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;
import shop.dto.ProductDto;
import shop.dto.PropertyValueDto;


@Service
@RequiredArgsConstructor
public class PropertyValueService implements shop.service.Service<PropertyValue> {

    private final PropertyValueDao dao;
    private final PropertyService propertyService;
    private final ProductService productService;

    @Override
    public PropertyValue findOne(long id) {
        return dao.findOne(id).get();
    }

    @Override
    public List<PropertyValue> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    public PropertyValue create(PropertyValue propertyValue) {
        return dao.create(propertyValue);
    }

    @Override
    public void delete(PropertyValue propertyValue) {
        dao.delete(propertyValue);
    }

    public PropertyValue create(PropertyValueDto dto) {
        PropertyValue propertyValue = convertPropertyValueDtoToPropertyValue(dto);
        return create(propertyValue);
    }

    private PropertyValue convertPropertyValueDtoToPropertyValue(PropertyValueDto dto) {
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setName(dto.getName());
        propertyValue.setValue(dto.getValue());
        Property property = propertyService.findByName(dto.getProperty());
        propertyValue.setProperty(property);
        Product product = productService.findByName(dto.getProduct());
        propertyValue.setProduct(product);
        return propertyValue;
    }

    public PropertyValue findByName(String p) {
        return dao.findByName(p);
    }
}
