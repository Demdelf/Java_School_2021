package shop.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import shop.domain.User;

public interface UserServiceInterface extends UserDetailsService {

    List<User> findAll(int pageSize);
    User create(final User entity);
}
