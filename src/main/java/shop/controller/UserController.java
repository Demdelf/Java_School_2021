package shop.controller;

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
import shop.service.UserService;

@Controller
//@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @GetMapping("/")
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
}
