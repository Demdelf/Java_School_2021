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
import shop.domain.Product;

@Repository
public class ProductDao  extends AbstractDao<Product> implements Dao<Product> {

    public ProductDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Product.class);
    }

    public List<Product> findAllByCategory(Long categoryId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(criteriaBuilder.equal(root.get("category"), categoryId));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Product> findAllVisible() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(criteriaBuilder.equal(root.get("visible"), true));
        return entityManager.createQuery(query).getResultList();
    }

//    public Product findByName(String name) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
//        Root<Product> root = query.from(Product.class);
//        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
//        return entityManager.createQuery(query).getSingleResult();
//    }
}
