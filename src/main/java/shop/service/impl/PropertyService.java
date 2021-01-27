package shop.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.CategoryDao;
import shop.dao.impl.PropertyDao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.dto.ProductDto;
import shop.dto.PropertyDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PropertyService implements shop.service.Service<Property> {

    private final PropertyDao dao;
    private final CategoryService categoryService;

    @Override

    public Property findOne(long id) {
        return dao.findOne(id).orElse(null);
    }

    @Override
    public List<Property> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    @Transactional
    public Property create(Property property) {
        return dao.create(property);
    }

    @Override
    @Transactional
    public void delete(Property property) {
        dao.delete(property);
    }

    @Transactional
    public Property create(PropertyDto dto) {
        Property property = convertPropertyDtoToProperty(dto);
        return create(property);
    }

    @Transactional
    Property convertPropertyDtoToProperty(PropertyDto dto) {
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

    @Override
    @Transactional
    public Property update(Property p) {
        return dao.update(p);
    }

    public List<Property> findAllUniq() {
        return dao.findAll().stream().distinct().collect(Collectors.toList());
    }

    public PropertyDto getNewPropertyDto(Long categoryId) {
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setCategory(categoryService.findOne(categoryId).getName());
        return propertyDto;
    }

//    public PropertyDto addNewProperty(Long categoryId, PropertyDto dto) {
//        c
//    }
}
