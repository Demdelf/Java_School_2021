package shop.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.dao.UserDaoInt;
import shop.domain.User;

@Repository
@RequiredArgsConstructor
public class UserDaoImp implements UserDaoInt {

    @PersistenceContext
    private final EntityManager entityManager;


    public Optional<User> findOne(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public List<User> findAll(int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);

        Root<User> root = query.from(User.class);
        query.select(root);

        TypedQuery<User> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    @Override
    public User find(Long id) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        query.select(root).where(criteriaBuilder.equal(root.get("id"), id));
//        Optional<User> optionalUser = Optional.ofNullable(entityManager.createQuery(query).getResultList()
//                .stream().findFirst().orElse(null));
//        return optionalUser.orElse(null);
        return entityManager.find(User.class, id);
    }

    @Override
    public User create(User user) {

//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
        entityManager.persist(user);
//        transaction.commit();
        return user;
    }


    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserByEmail(String email){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        Optional<User> optionalUser = Optional.ofNullable(entityManager.createQuery(query).getResultList()
                .stream().findFirst().orElse(null));
        return optionalUser.orElse(null);
    }
}
