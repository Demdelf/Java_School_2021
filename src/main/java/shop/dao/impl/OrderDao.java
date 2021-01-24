package shop.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.CustomerAddress;
import shop.domain.DeliveryMethod;
import shop.domain.DeliveryStatus;
import shop.domain.Order;
import shop.domain.OrderProduct;
import shop.domain.PaymentMethod;
import shop.domain.PaymentStatus;
import shop.domain.User;
import shop.dto.CustomerAddressDto;

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

    public OrderProduct saveOrderProduct(OrderProduct orderProduct){
        entityManager.persist(orderProduct);
        return orderProduct;
    }

    public List<OrderProduct> getOrderProductsByOrderId(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderProduct> query = criteriaBuilder.createQuery(OrderProduct.class);
        Root<OrderProduct> root = query.from(OrderProduct.class);
        query.select(root).where(criteriaBuilder.equal(root.get("order"), id));
        return entityManager.createQuery(query).getResultList();
    }

    public DeliveryMethod getDeliveryMethodByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeliveryMethod> query = criteriaBuilder.createQuery(DeliveryMethod.class);
        Root<DeliveryMethod> root = query.from(DeliveryMethod.class);
        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        return entityManager.createQuery(query).getSingleResult();
    }

    public DeliveryStatus getDeliveryStatusByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeliveryStatus> query = criteriaBuilder.createQuery(DeliveryStatus.class);
        Root<DeliveryStatus> root = query.from(DeliveryStatus.class);
        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        return entityManager.createQuery(query).getSingleResult();
    }

    public PaymentMethod getPaymentMethodByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentMethod> query = criteriaBuilder.createQuery(PaymentMethod.class);
        Root<PaymentMethod> root = query.from(PaymentMethod.class);
        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        return entityManager.createQuery(query).getSingleResult();
    }
    public PaymentStatus getPaymentStatusByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PaymentStatus> query = criteriaBuilder.createQuery(PaymentStatus.class);
        Root<PaymentStatus> root = query.from(PaymentStatus.class);
        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        return entityManager.createQuery(query).getSingleResult();
    }

    public List<Order> getOrdersForUser(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        query.select(root).where(criteriaBuilder.equal(root.get("user"), user.getId()));
        return entityManager.createQuery(query).getResultList();
    }

    public CustomerAddress getAddressById(Long id) {
        return entityManager.find(CustomerAddress.class, id);
    }

    public List<CustomerAddress> getUserAddresses(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerAddress> query = criteriaBuilder.createQuery(CustomerAddress.class);
        Root<CustomerAddress> root = query.from(CustomerAddress.class);
        query.select(root).where(criteriaBuilder.equal(root.get("user"), user.getId()));
        return entityManager.createQuery(query).getResultList();
    }

    public void updateAddress(CustomerAddress address) {
        entityManager.merge(address);
    }

    public void createAddress(CustomerAddress customerAddress) {
        entityManager.persist(customerAddress);
    }

    public List<DeliveryMethod> findAllDeliveryMethods() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DeliveryMethod> query = criteriaBuilder.createQuery(DeliveryMethod.class);
        Root<DeliveryMethod> root = query.from(DeliveryMethod.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }
}
