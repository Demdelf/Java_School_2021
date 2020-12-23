package shop.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import shop.dao.Dao;

@RequiredArgsConstructor
public abstract class AbstractService<T> {

    private Dao<T> dao;

    public final void setDao(final Dao<T> dao) {
        this.dao = dao;
    }

    public T findOne(long id) {
        return dao.findOne(id).get();
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    public T create(final T entity) {
        return dao.create(entity);
    }

    public T update(final T entity) {
        return dao.create(entity);
    }

    public void delete(final T entity) {
        dao.delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

}
