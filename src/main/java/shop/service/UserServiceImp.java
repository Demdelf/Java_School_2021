package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.dao.DaoInt;
import shop.dao.UserDao;
import shop.dao.UserDaoImp;
import shop.domain.User;

//@Service
////@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImp extends AbstractService<User> implements UserDetailsService {

    private final DaoInt dao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return dao.getUserByEmail(email);
    }
}
