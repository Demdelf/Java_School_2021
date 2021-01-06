package shop.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.PropertyValue;
import shop.domain.User;

@Repository
public class PropertyValueDao  extends AbstractDao<PropertyValue> implements Dao<PropertyValue> {

    public PropertyValueDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(PropertyValue.class);
    }

    public PropertyValue findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PropertyValue> query = criteriaBuilder.createQuery(PropertyValue.class);
        Root<PropertyValue> root = query.from(PropertyValue.class);
        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        PropertyValue propertyValue = null;
        try {
            propertyValue = entityManager.createQuery(query).getSingleResult();
        }
        catch (NoResultException nre){
                    }
        return propertyValue;
    }

    public PropertyValue findByCategoryAndProperty(Product product, Property property) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PropertyValue> query = criteriaBuilder.createQuery(PropertyValue.class);
        Root<PropertyValue> root = query.from(PropertyValue.class);
        query.select(root)
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("product"), product)
                                , criteriaBuilder.equal(root.get("property"), property)
                        )
                );
        PropertyValue propertyValue = null;
        try {
            propertyValue = entityManager.createQuery(query).getSingleResult();
        }
        catch (NoResultException nre){
        }
        return propertyValue;
    }
}
