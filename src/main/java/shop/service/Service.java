package shop.service;

import java.util.List;


public interface Service<T> {
    T findOne(long id);

    List<T> findAll();

    T create(T t);

    void delete(T t);
}
