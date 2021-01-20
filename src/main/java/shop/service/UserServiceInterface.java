package shop.service;

import java.security.Principal;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import shop.domain.User;
import shop.dto.UserAccountDto;

public interface UserServiceInterface extends UserDetailsService {

    List<User> findAll(int pageSize);
    User create(final User entity);

    UserAccountDto getUserAccountDtoForPrincipal(Principal principal);
}
