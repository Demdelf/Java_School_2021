package shop.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Category;
import shop.domain.Property;
import shop.domain.User;

@Repository
public class CategoryDao extends AbstractDao<Category> implements Dao<Category> {

    public CategoryDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Category.class);
    }

//    public Category findByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
//        Root<Category> root = query.from(Category.class);
//        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
//        return entityManager.createQuery(query).getSingleResult();
//    }

    public List<Property> getAllProperties(Long categoryId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = criteriaBuilder.createQuery(Property.class);
        Root<Property> root = query.from(Property.class);
        query.select(root).where(criteriaBuilder.equal(root.get("category"), categoryId));
        return entityManager.createQuery(query).getResultList();
    }

    public Property getPropertyById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Property> query = criteriaBuilder.createQuery(Property.class);
        Root<Property> root = query.from(Property.class);
        query.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }
}
