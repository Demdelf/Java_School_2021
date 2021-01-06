package shop.service;

import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;

public interface PropertyValueService extends shop.service.Service<PropertyValue>{

    PropertyValue findByProductAndProperty(Product product, Property property);
}
