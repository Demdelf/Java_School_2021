package shop.dao;

import java.util.List;
import shop.domain.Category;

public interface CategoryDao {

    List<Category> getAll();
    Category getById(int id);
    Category getByName(String name);
    void create(String name);
    void update(Category category);
    void delete(Category category);
}
