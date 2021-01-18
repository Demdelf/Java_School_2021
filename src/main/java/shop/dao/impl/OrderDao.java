package shop.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.DeliveryMethod;
import shop.domain.DeliveryStatus;
import shop.domain.Order;
import shop.domain.PaymentMethod;
import shop.domain.PaymentStatus;

@Repository
public class OrderDao  extends AbstractDao<Order> implements Dao<Order> {

    public OrderDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Order.class);
    }

    public DeliveryMethod findDeliveryMethod(long id) {
        return entityManager.find(DeliveryMethod.class, id);
    }
    public DeliveryStatus findDeliveryStatus(long id) {
        return entityManager.find(DeliveryStatus.class, id);
    }
    public PaymentMethod findPaymentMethod(long id) {
        return entityManager.find(PaymentMethod.class, id);
    }
    public PaymentStatus findPaymentStatus(long id) {
        return entityManager.find(PaymentStatus.class, id);
    }

    public List<PaymentMethod> findAllPaymentMethods() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentMethod> query = criteriaBuilder.createQuery(PaymentMethod.class);
        Root<PaymentMethod> root = query.from(PaymentMethod.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }
}
