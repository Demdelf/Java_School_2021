package shop.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import shop.dao.UserDaoInt;
import shop.domain.User;
import shop.dto.UserRegDto;
import shop.util.exception.EmailExistsException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserDaoInt dao;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getUserByEmail(s);
    }

    public List<User> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }


    public User create(final User entity) {
        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        return dao.create(entity);
    }

    public User convertUserRegDtoToUser(UserRegDto dto) throws EmailExistsException {
        if (loadUserByUsername(dto.getEmail()) != null){
            throw new EmailExistsException();
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(roleService.findOne(1));
        return user;
    }

    public User saveUserFromUserRegDto(UserRegDto dto) throws EmailExistsException {
        User user = convertUserRegDtoToUser(dto);
        return dao.create(user);
    }

}
