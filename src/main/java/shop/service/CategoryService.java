package shop.service;

import java.util.List;
import shop.domain.Category;
import shop.domain.Property;
import shop.dto.CategoryDto;
import shop.dto.FilterDto;


public interface CategoryService extends Service<Category> {

    List<Property> getAllPropertiesByCategoryId(Long categoryId);
    List<CategoryDto> getAllCategoryDto(int pageSize);

    CategoryDto getDtoById(Long id);

    CategoryDto update(CategoryDto dto);

    Category createFromDto(CategoryDto categoryDto);

    Object getAllProperties();

    List<Category> findAllFiltered(FilterDto filterDto);

    List<Category> findAllVisible(int i);
}
