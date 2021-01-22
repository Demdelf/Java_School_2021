package shop.controller;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.domain.User;
import shop.dto.UserAccountDto;
import shop.dto.UserRegDto;
import shop.service.UserServiceInterface;

@Controller
//@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceInterface userService;

    @GetMapping("/get-all")
    public List<User> getAll(int pageSize){
        return userService.findAll(pageSize);
    }

    @PostMapping("/create")
    public Long create(@ModelAttribute("user") @Valid User user){
        Long id = userService.create(user).getId();
        return id;
    }

    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

    @GetMapping("/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("users", userService.findAll(10));
        return "editUsers";
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", userService.findAll(10));
            return "editUsers";
        }

        userService.create(user);
        return "redirect:/";
    }

    @PostMapping("/reg")
    public String addUser(@ModelAttribute("userRegDto") @Valid UserRegDto userRegDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userRegDto.getPassword().equals(userRegDto.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.create(userRegDto)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/account";
    }

    @GetMapping("account")
    public String currentUserName(Principal principal, Model model){

        UserAccountDto accountDto = userService.getUserAccountDtoForPrincipal(principal);
        model.addAttribute("accountDto", accountDto);

        return "customer/account";
    }
}
