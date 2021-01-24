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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.domain.User;
import shop.dto.CustomerAddressDto;
import shop.dto.UserAccountDto;
import shop.dto.UserEditAccountDto;
import shop.dto.UserRegDto;
import shop.service.UserServiceInterface;
import shop.service.impl.OrderService;

@Controller
//@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceInterface userService;
    private final OrderService orderService;

    @GetMapping("/get-all")
    public List<User> getAll(int pageSize) {
        return userService.findAll(pageSize);
    }

    @PostMapping("/create")
    public Long create(@ModelAttribute("user") @Valid User user) {
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
    public String addUser(
            @ModelAttribute("userRegDto") @Valid UserRegDto userRegDto, BindingResult bindingResult, Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userRegDto.getPassword().equals(userRegDto.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.create(userRegDto)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/account";
    }

    @GetMapping("account")
    public String currentUserAcc(Principal principal, Model model) {

        UserAccountDto accountDto = userService.getUserAccountDtoForPrincipal(principal);
        List<CustomerAddressDto> addressDtoList = orderService.getUserAddressDtoListForPrincipal(principal);
        model.addAttribute("accountDto", accountDto);
        model.addAttribute("addressDtoList", addressDtoList);

        return "customer/account";
    }

    @GetMapping("account/edit")
    public String editFormUserAcc(Principal principal, Model model) {

        UserAccountDto accountDto = userService.getUserAccountDtoForPrincipal(principal);
        model.addAttribute("accountDto", accountDto);

        return "customer/editAccount";
    }

    @GetMapping("account/addresses/edit/{id}")
    public String editFormUserAddress(@PathVariable("id") Long id, Principal principal, Model model) {
        CustomerAddressDto addressDto = orderService.getUserAddressDtoForPrincipal(id, principal);

        model.addAttribute("addressDto", addressDto);

        return "customer/editAddress";
    }

    @GetMapping("account/addresses/create")
    public String createFormUserAddress(Principal principal, Model model) {
        CustomerAddressDto addressDto = new CustomerAddressDto();

        model.addAttribute("addressDto", addressDto);

        return "customer/createAddress";
    }

    @PostMapping("account/addresses/create")
    public String createUserAddress(
            @ModelAttribute("addressDto") @Valid CustomerAddressDto addressDto,
            BindingResult bindingResult, Principal principal, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/account/addresses/create";
        }

        orderService.crateAddressForPrincipal(principal, addressDto);

        return "redirect:/account";
    }


    @PostMapping("account/addresses/edit/{id}")
    public String editUserAddress(
            @PathVariable("id") Long id,
            @ModelAttribute("addressDto") @Valid CustomerAddressDto addressDto,
            BindingResult bindingResult, Principal principal, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/account/addresses/edit/" + id;
        }

        orderService.updateUserAddressDtoForPrincipal(id, principal, addressDto);

        return "redirect:/account";
    }

    @PostMapping("account/edit")
    public String editUserAcc(
            @ModelAttribute("userEditAccountDto") @Valid UserEditAccountDto userEditAccountDto,
            BindingResult bindingResult, Principal principal, Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "redirect:/account/edit";
        }
//        if (!userEditAccountDto.getPassword().equals(userEditAccountDto.getPasswordConfirm())){
//            model.addAttribute("passwordError", "Пароли не совпадают");
//            return "redirect:/account";
//        }
        userService.updateUserAccountDtoForPrincipal(userEditAccountDto, principal);

        return "redirect:/account";
    }

    @PostMapping("account/editPassword")
    public String editUserPassword(
            @ModelAttribute("userEditAccountDto") @Valid UserEditAccountDto userEditAccountDto,
            BindingResult bindingResult, Principal principal, Model model
    ) {

//        if (bindingResult.hasErrors()) {
//            return "redirect:/account/edit";
//        }
        if (!userEditAccountDto.getPassword().equals(userEditAccountDto.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "redirect:/account/edit";
        }
        userService.updateUserPasswordForPrincipal(userEditAccountDto, principal);

        return "redirect:/account";
    }
}
