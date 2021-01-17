package shop.dao.impl;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.Order;

@Repository
public class OrderDao  extends AbstractDao<Order> implements Dao<Order> {

    public OrderDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Order.class);
    }
}
