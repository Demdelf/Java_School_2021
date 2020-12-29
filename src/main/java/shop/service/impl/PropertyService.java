package shop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dao.impl.CategoryDao;
import shop.dao.impl.PropertyDao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.dto.ProductDto;
import shop.dto.PropertyDto;

@Service
@RequiredArgsConstructor
public class PropertyService implements shop.service.Service<Property> {

    private final PropertyDao dao;
    private final CategoryService categoryService;

    @Override
    public Property findOne(long id) {
        return null;
    }

    @Override
    public List<Property> findAll(int pageSize) {
        return null;
    }

    @Override
    public Property create(Property property) {
        return null;
    }

    @Override
    public void delete(Property property) {

    }

    public Property create(PropertyDto dto) {
        Property property = convertPropertyDtoToProperty(dto);
        return create(property);
    }

    private Property convertPropertyDtoToProperty(PropertyDto dto) {
        Property property = new Property();
        property.setName(dto.getName());
        property.setType(dto.getType());
        Category category = categoryService.findByName(dto.getCategory());
        property.setCategory(category);
        return create(property);
    }

    public Property findByName(String name){
        return dao.findByName(name);
    }

}
