package shop.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Category;
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
        return entityManager.createQuery(query).getSingleResult();
    }
}
