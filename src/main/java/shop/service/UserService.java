package shop.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.UserDao;
import shop.dao.UserDaoImp;
import shop.domain.User;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserDaoImp dao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getUserByEmail(s);
    }

    public List<User> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }
    @Transactional
    public User create(final User entity) {
        return dao.create(entity);
    }
}
