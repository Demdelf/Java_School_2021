package shop.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Category;
import shop.domain.Property;

@Repository
public class PropertyDao extends AbstractDao<Property> implements Dao<Property> {

    public PropertyDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Property.class);
    }

//    public Property findByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Property> query = criteriaBuilder.createQuery(Property.class);
//        Root<Property> root = query.from(Property.class);
//        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
//        return entityManager.createQuery(query).getSingleResult();
//    }
}
