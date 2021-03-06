package shop.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import shop.dao.UserDaoInt;
import shop.domain.User;
import shop.dto.UserAccountDto;
import shop.dto.UserEditAccountDto;
import shop.dto.UserRegDto;
import shop.service.RoleService;
import shop.service.UserServiceInterface;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserDaoInt dao;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getUserByEmail(s);
    }

    public List<User> findAll(int pageSize) {
        return dao.findAll(pageSize);
    }
    public User find(Long id) {
        return dao.find(id);
    }


    public User create(final User entity) {
        TransactionStatus transactionStatus = TransactionAspectSupport.currentTransactionStatus();
        return dao.create(entity);
    }

    @Override
    public UserAccountDto getUserAccountDtoForPrincipal(Principal principal) {
        User user = (User) loadUserByUsername(principal.getName());

        return convertUserToAccountDto(user);
    }



    @Override
    public Boolean create(UserRegDto userRegDto) {
        User userFromDB = dao.getUserByEmail(userRegDto.getEmail());

        if (userFromDB != null) {
            return false;
        }
        saveUserFromUserRegDto(userRegDto);
        return true;
    }

    @Override
    public void updateUserAccountDtoForPrincipal(
            UserEditAccountDto userEditAccountDto, Principal principal
    ) {
        User user = (User) loadUserByUsername(principal.getName());
        user.setBirthday(LocalDate.parse(userEditAccountDto.getBirthday()));
        user.setFirstName(userEditAccountDto.getFirstName());
        user.setLastName(userEditAccountDto.getLastName());
        user.setEmail(userEditAccountDto.getEmail());

        dao.update(user);
    }

    @Override
    public void updateUserPasswordForPrincipal(UserEditAccountDto userEditAccountDto, Principal principal) {
        User user = (User) loadUserByUsername(principal.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        dao.update(user);
    }




    private UserAccountDto convertUserToAccountDto(User user) {
        UserAccountDto accountDto = new UserAccountDto();
        accountDto.setId(user.getId());
        if (user.getFirstName() != null)
        accountDto.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
        accountDto.setLastName(user.getLastName());
        if (user.getBirthday() != null)
        accountDto.setBirthday(user.getBirthday().toString());
        accountDto.setEmail(user.getEmail());
        return accountDto;
    }

    public User convertUserRegDtoToUser(UserRegDto dto){
//        if (loadUserByUsername(dto.getEmail()) != null){
//            throw new EmailExistsException();
//        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(roleService.findOne(1));
        return user;
    }

    public User saveUserFromUserRegDto(UserRegDto dto)
    {
        User user = convertUserRegDtoToUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return dao.create(user);
    }

    public UserAccountDto convertUserToUserAccountDto(User u) {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setId(u.getId());
        userAccountDto.setEmail(u.getEmail());
        if (u.getFirstName() != null){
            userAccountDto.setFirstName(u.getFirstName());
        }
        if (u.getLastName() != null) {
            userAccountDto.setLastName(u.getLastName());
        }
        if (u.getBirthday() != null) {
            userAccountDto.setBirthday(u.getBirthday().toString());
        }
        return userAccountDto;
    }

//    public List<OrderDto> getAllUserOrders(Principal principal) {
//        User user = (User) loadUserByUsername(principal.getName());
//
//    }
}
