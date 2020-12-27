package shop.dao;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.domain.PropertyValue;
import shop.domain.User;

@Repository
public class PropertyValueDao  extends AbstractDao<PropertyValue> implements Dao<PropertyValue>{

    public PropertyValueDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(PropertyValue.class);
    }
}
