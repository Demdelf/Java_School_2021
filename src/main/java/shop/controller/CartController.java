package shop.controller;

import static java.util.stream.Collectors.toMap;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.ProductDto;
import shop.service.CartService;
import shop.service.UserService;
import shop.service.impl.ProductService;

@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
//@SessionAttributes("cart")
public class CartController {
    private static final String CART_BASE = "customer/cart";

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;



    @GetMapping
    public String getCart(
            Principal principal, HttpServletRequest request, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto)
    {
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            CartDTO carDtoFromDB = cartService.getCartDtoByUserIdOrCreate(user);
            request.getSession().setAttribute("cartDto", carDtoFromDB);
            carDtoFromDB.setFromCart(true);
            model.addAttribute("cart", carDtoFromDB);
        } else {
            cartDto.setFromCart(true);
            model.addAttribute("cart", cartDto);
        }
        return CART_BASE;
    }

    @PostMapping("/clear")
    public String clearCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto, @SessionAttribute(value = "path") String path)
    {
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            CartDTO clearedCartDto = cartService.clearCart(user);
            model.addAttribute("cart", clearedCartDto);
        } else {
            cartDto.clear();
            model.addAttribute("cart", cartDto);
        }
        return "redirect:/" + path;
    }

    @PostMapping("/update")
    public String updateCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto)
    {
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            cartService.updateCartByUser(user, cartDto);
        }

        model.addAttribute("cart", cartDto);

        return CART_BASE;
    }

    @PostMapping("/add/{id}")
    public String addToCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto, @PathVariable("id") Long id
            , @SessionAttribute(value = "path") String path)
    {
        ProductDto productDto = productService.getDtoById(id);
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            cartService.addToCartByUser(user, productDto);
        }
        cartDto.addProductDto(productDto);
        model.addAttribute("cart", cartDto);
        String redirect = cartDto.isFromCart() ? "cart" : path;
        cartDto.setFromCart(false);

        return "redirect:/" + redirect;
    }

    @PostMapping("/sub/{id}")
    public String subFromCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto, @PathVariable("id") Long id
            , @SessionAttribute(value = "path") String path)
    {
        ProductDto productDto = productService.getDtoById(id);
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            cartService.subFromCartByUser(user, productDto);
        }
        cartDto.subProductDto(productDto);
        model.addAttribute("cart", cartDto);

        String redirect = cartDto.isFromCart() ? "cart" : path;
        cartDto.setFromCart(false);

        return "redirect:/" + redirect;
    }

    @PostMapping("/delete/{id}")
    public String deleteFromCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto, @PathVariable("id") Long id
            , @SessionAttribute(value = "path") String path)
    {
        ProductDto productDto = productService.getDtoById(id);
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            cartService.deleteFromCartByUser(user, productDto);
        }
        cartDto.deleteProductDto(productDto);
        model.addAttribute("cart", cartDto);

        String redirect = cartDto.isFromCart() ? "cart" : path;
        cartDto.setFromCart(false);

        return "redirect:/" + redirect;
    }

//    @ModelAttribute("cartPath")
//    public String getCartPath() {
//        return "cart";
//    }

    private boolean isAuthorized(Principal principal) {
        return principal != null;
    }
}
