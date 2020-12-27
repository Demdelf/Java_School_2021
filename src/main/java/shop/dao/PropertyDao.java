package shop.dao;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.domain.Property;

@Repository
public class PropertyDao extends AbstractDao<Property> implements Dao<Property>{

    public PropertyDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Property.class);
    }
}
