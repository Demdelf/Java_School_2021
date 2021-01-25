package shop.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Cart;
import shop.domain.PropertyValue;

@Repository
public class CartDao extends AbstractDao<Cart> implements Dao<Cart> {

    public CartDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Cart.class);
    }

    public List<Cart> getAllCartsByUserId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> query = criteriaBuilder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);
        query.select(root).where(criteriaBuilder.equal(root.get("user"), id));
        return entityManager.createQuery(query).getResultList();
    }

    public Cart findByUserAndProduct(Cart c) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> query = criteriaBuilder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);
        query.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("user"), c.getUser().getId())
                ,criteriaBuilder.equal(root.get("product"), c.getProduct().getId())));
        Cart cart = null;
        try {
            cart = entityManager.createQuery(query).getSingleResult();
        }
        catch (NoResultException nre){
        }
        return cart;
    }
}
