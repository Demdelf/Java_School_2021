package shop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.Dao;
import shop.dao.impl.CategoryDao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;
import shop.dto.CategoryDto;
import shop.dto.ProductDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
    @Transactional
    public Category create(Category category) {
        return dao.create(category);
    }

    @Override
    @Transactional
    public void delete(Category category) {

    }

    @Transactional
    public Category create(CategoryDto dto) {
        Category category = convertCategoryDtoToCategory(dto);
        return create(category);
    }

    @Transactional
    private Category convertCategoryDtoToCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(category.getName());
        return create(category);
    }

    public Category findByName(String name){
        return dao.findByName(name);
    }

    @Override
    @Transactional
    public Category update(Category p) {
        return dao.update(p);
    }

    @Override
    public List<Property> getAllProperties(Long categoryId) {
        return dao.getAllProperties(categoryId);
    }
}
