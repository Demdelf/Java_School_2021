package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import shop.dao.UserDaoInt;
import shop.domain.User;

//@Service
////@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImp extends AbstractService<User> implements UserDetailsService {

    private final UserDaoInt dao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return dao.getUserByEmail(email);
    }
}
