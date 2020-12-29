package shop.dao;

import java.util.List;
import java.util.Map;
import shop.domain.Category;
import shop.domain.Product;


public interface ProductDao {

    void create(Product productEntity);
    void update(Product productEntity);
    void deleteById(long id);
    Product getById(long id);
    List<Product> getAll();
    List<Product> getProductsByCategory(Category category);
    List<Product> getTopProducts();
}
