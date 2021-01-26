package shop.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import shop.domain.Category;
import shop.domain.Property;
import shop.dto.CategoryDto;


public interface CategoryService extends Service<Category> {

    List<Property> getAllPropertiesByCategoryId(Long categoryId);
    List<CategoryDto> getAllCategoryDto(int pageSize);

    CategoryDto getDtoById(Long id);

    CategoryDto update(CategoryDto dto);

    Category createFromDto(CategoryDto categoryDto);

    Object getAllProperties();
}
