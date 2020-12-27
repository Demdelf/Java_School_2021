package shop.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface Service<T> {
    T findOne(long id);

    List<T> findAll(int pageSize);

    T create(T t);

    void delete(T t);
}
