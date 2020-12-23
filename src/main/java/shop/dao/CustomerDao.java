package shop.dao;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import shop.domain.Customer;


@Repository
public class CustomerDao extends AbstractDao<Customer> implements Dao<Customer> {

    public CustomerDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(Customer.class);
    }
}
