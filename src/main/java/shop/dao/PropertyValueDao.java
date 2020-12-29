package shop.dao;

import java.util.List;
import shop.domain.PropertyValue;

public interface PropertyValueDao {
    List<PropertyValue> getAll();
    PropertyValue getById(int id);
    PropertyValue getByName(String name);
    void create(String name);
    void update(PropertyValue value);
    void delete(PropertyValue value);
}
