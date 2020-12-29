package shop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dao.Dao;
import shop.dao.impl.CategoryDao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.PropertyValue;
import shop.dto.CategoryDto;
import shop.dto.ProductDto;

@Service
@RequiredArgsConstructor
public class CategoryService implements shop.service.Service<Category> {

    private final CategoryDao dao;

    @Override
    public Category findOne(long id) {
        return null;
    }

    @Override
    public List<Category> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }

    @Override
    public Category create(Category category) {
        return dao.create(category);
    }

    @Override
    public void delete(Category category) {

    }

    public Category create(CategoryDto dto) {
        Category category = convertCategoryDtoToCategory(dto);
        return create(category);
    }

    private Category convertCategoryDtoToCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(category.getName());
        return create(category);
    }

    public Category findByName(String name){
        return dao.findByName(name);
    }
}
