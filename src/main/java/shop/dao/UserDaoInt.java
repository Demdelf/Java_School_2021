package shop.dao;

import java.util.List;
import shop.domain.User;

public interface UserDaoInt {
    User create(User user);
    User getUserByEmail(String email);
    List<User> findAll(int pageSize);
}
