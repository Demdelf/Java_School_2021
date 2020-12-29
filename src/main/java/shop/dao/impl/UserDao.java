package shop.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import shop.dao.AbstractDao;
import shop.dao.Dao;
import shop.domain.User;


//@Repository
public class UserDao extends AbstractDao<User> implements Dao<User> {

    public UserDao(EntityManager entityManager) {
        super(entityManager);
        setClazz(User.class);
    }

    public User getUserByEmail(String email){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        return entityManager.createQuery(query).getSingleResult();
    }
}
