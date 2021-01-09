package shop.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import shop.domain.Category;
import shop.domain.Property;
import shop.dto.CategoryDto;


public interface CategoryService extends Service<Category> {

    List<Property> getAllProperties(Long categoryId);
    List<CategoryDto> getAllCategoryDto(int pageSize);
}
