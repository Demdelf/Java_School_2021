package shop.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

//@AllArgsConstructor
//@Repository
public class AdminDao /*implements Dao<Admin>*/{
    /*private final EntityManager entityManager;



    @Override
    public Optional<Admin> findOne(long id) {
        return Optional.ofNullable(entityManager.find(Admin.class, id));
    }

    @Override
    public List<Admin> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Admin> query = criteriaBuilder.createQuery(Admin.class);

        Root<Admin> root = query.from(Admin.class);
        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Admin create(Admin admin) {
        try {
            entityManager.getTransaction().begin();
            if (admin.getId() == null){
                entityManager.persist(admin);
            } else {
                entityManager.merge(admin);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }
        return admin;
    }

    @Override
    public void delete(Admin admin) {
        executeInsideTransaction(entityManager -> entityManager.remove(admin));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }*/
}
