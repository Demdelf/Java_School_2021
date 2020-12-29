package shop.dao;

import java.util.List;
import shop.domain.Property;

public interface PropertyDao {

    List<Property> getAll();
    Property getByName(String name);
    Property getById(int id);
    void create(Property property);
    void update(Property property);
    void delete(String name);
}
