package shop.dao;

import javax.persistence.EntityManager;
import shop.domain.User;


//@Repository
public class CustomerDao extends AbstractDao<User> implements Dao<User> {

    public CustomerDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(User.class);
    }
}
