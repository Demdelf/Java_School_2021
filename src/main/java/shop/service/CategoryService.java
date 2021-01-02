package shop.service;

import java.util.List;
import shop.domain.Category;
import shop.domain.Property;

public interface CategoryService extends Service<Category> {

    List<Property> getAllProperties(Long categoryId);
}
