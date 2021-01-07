package shop.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import shop.domain.Category;
import shop.domain.Property;


public interface CategoryService extends Service<Category> {

    List<Property> getAllProperties(Long categoryId);
}
