package shop.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findOne(long id);

    List<T> findAll();

    T create(T t);

    void delete(T t);
}
