package shop.dao;

import java.util.List;
import shop.domain.CustomerAddress;
import shop.domain.User;
import shop.dto.UserAccountDto;

public interface UserDaoInt {
    User create(User user);
    User getUserByEmail(String email);
    List<User> findAll(int pageSize);

    User find(Long id);

    User update(User user);

}
