package shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.CategoryDao;
import shop.domain.Category;
import shop.domain.Property;
import shop.dto.CategoryDto;
import shop.dto.FilterDto;
import shop.dto.PropertyDto;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService implements shop.service.CategoryService {

    private final CategoryDao dao;

    @Override
    public Category findOne(long id) {
        return dao.findOne(id).orElse(null);
    }

    @Override

    public List<Category> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override

    public Category create(Category category) {
        category = dao.create(category);
        for (Property p: category.getProperties()
        ) {
            p.setCategory(category);
            dao.saveProperty(p);
        }
        return category;
    }

    @Override

    public void delete(Category category) {

    }

    public Category createFromDto(CategoryDto dto) {
        Category category = convertCategoryDtoToCategory(dto);
        return create(category);
    }

    @Override
    public Object getAllProperties() {
        return null;
    }

    @Override
    public List<Category> findAllFiltered(FilterDto filterDto) {

        return null;
    }

    @Override
    public List<Category> findAllVisible(int i) {
        return dao.findAllVisible();
    }


    private Category convertCategoryDtoToCategory(CategoryDto dto) {
        Category category;
        if (dto.getId() == null){
            category = new Category();
        }else {
            category = dao.findOne(dto.getId()).orElse(new Category());
        }
        category.setName(dto.getName());
        category.setVisible(dto.getVisible());
        List<Property> list = new ArrayList<>();
        if (dto.getProperties() != null && !dto.getProperties().isEmpty()){
            for (PropertyDto propertyDto: dto.getProperties()
            ) {
                list.add(getPropertyById(propertyDto.getId()));
            }
        }
        if (dto.getProp() != null && dto.getProp().length > 0){
            for (String id: dto.getProp()
            ) {
                list.add(getPropertyById(Long.valueOf(id)));
            }
        }

        category.setProperties(list);
        return category;
    }

    private Property getPropertyById(Long id) {
        return dao.getPropertyById(id);
    }

//    private Property convertDtoToProperty(PropertyDto propertyDto) {
//        Property property = new Property();
//        property.setName(propertyDto.getName());
//        property.setType(propertyDto.getType());
//        property.setCategory(dao.findByName(propertyDto.getCategory()));
//        property.setId(propertyDto.getId());
//    }


    private CategoryDto convertCategoryToDto(Category category){
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setVisible(category.getVisible());
        ArrayList<PropertyDto> propertyDtos = new ArrayList<>();
        for (Property p: category.getProperties()
        ) {
            PropertyDto propertyDto = new PropertyDto();
            propertyDto.setId(p.getId());
            propertyDto.setName(p.getName());
            propertyDto.setType(p.getType());
            propertyDto.setCategory(p.getCategory().getName());
            propertyDtos.add(propertyDto);
        }
        dto.setProperties(propertyDtos);

        return dto;
    }

    public Category findByName(String name){
        return dao.findByName(name);
    }

    @Override

    public Category update(Category p) {
        return dao.update(p);
    }

    @Override
    public List<Property> getAllPropertiesByCategoryId(Long categoryId) {
        return dao.getAllProperties(categoryId);
    }

    @Override

    public List<CategoryDto> getAllCategoryDto(int pageSize) {
        List<Category> categories = findAll(pageSize);
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category c: categories
        ) {
            dtos.add(convertCategoryToDto(c));
        }
        return dtos;
    }

    @Override

    public CategoryDto getDtoById(Long id) {
        Category category = findOne(id);
        category.setProperties(getAllPropertiesByCategoryId(id));
        return convertCategoryToDto(category);
    }

    @Override

    public CategoryDto update(CategoryDto dto) {
        Category category = convertCategoryDtoToCategory(dto);
        updateExist(category);
        return convertCategoryToDto(category);
    }

    private void updateExist(Category category) {
        ArrayList<Property> properties = new ArrayList<>();
        for (Property p: category.getProperties()
        ) {
            Property property = new Property();
            property.setName(p.getName());
            property.setType(p.getType());
            property.setCategory(category);
            dao.saveProperty(property);
            properties.add(property);
        }
        category.setProperties(properties);
        dao.update(category);
    }

}
