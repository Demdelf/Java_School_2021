package shop.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import shop.domain.Product;

@RequiredArgsConstructor
public abstract class AbstractDao<T> {

    private Class<T> clazz;

    @PersistenceContext
    protected final EntityManager entityManager;

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public Optional<T> findOne(long id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    public List<T> findAll(int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);

        Root<T> root = query.from(clazz);
        query.select(root);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    public T create(final T entity) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(entity);
//        transaction.commit();

        entityManager.persist(entity);

        return entity;
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId).get();
        delete(entity);
    }

    public T findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root).where(criteriaBuilder.equal(root.get("name"), name));
        return entityManager.createQuery(query).getSingleResult();
    }

}
