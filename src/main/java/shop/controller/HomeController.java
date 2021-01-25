package shop.controller;

import java.security.Principal;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.UserRegDto;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String homeInit(Locale locale, Model model) {

        return "redirect:/customer";
    }


    @ModelAttribute("user")
    public User formBackingObject() {
        return new User();
    }

    @GetMapping(value = "/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @SessionAttribute(value = "cart") CartDTO cartDto,
			Model model
    ) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        model.addAttribute("cart", cartDto);
        return "login";
    }

    @GetMapping(value = "/reg")
    public String regPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto
    ) {
        model.addAttribute("cart", cartDto);
        return "registration";
    }


    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response,
            @SessionAttribute(value = "cart") CartDTO cartDto, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("cart", cartDto);
        return "redirect:/login?logout=true";
    }
}
