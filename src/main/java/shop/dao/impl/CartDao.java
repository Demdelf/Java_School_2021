package shop.dao.impl;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Cart;
import shop.domain.Category;

@Repository
public class CartDao extends AbstractDao<Cart> implements Dao<Cart> {

    public CartDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Cart.class);
    }
}
