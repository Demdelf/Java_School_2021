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
    private static final String CART_BASE = "cart";

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
            model.addAttribute("cart", carDtoFromDB);
        } else {
            model.addAttribute("cart", cartDto);
        }
        return CART_BASE;
    }

    @PostMapping("/clear")
    public String clearCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto)
    {
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            CartDTO clearedCartDto = cartService.clearCart(user);
            model.addAttribute("cart", clearedCartDto);
        } else {
            Map<ProductDto, Integer> map = new HashMap<>();
            CartDTO newCartDto = new CartDTO();
            newCartDto.setProducts(map);
            model.addAttribute("cart", newCartDto);
        }
        return "redirect:/" + CART_BASE;
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

        return "redirect:/" + CART_BASE;
    }

    @PostMapping("/add/{id}")
    public String addToCart(
            Principal principal, Model model,
            @SessionAttribute(value = "cart") CartDTO cartDto, @PathVariable("id") Long id
            , @ModelAttribute(value = "path") String path)
    {
        ProductDto productDto = productService.getDtoById(id);
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            cartService.addToCartByUser(user, productDto);
        }
        cartDto.addProductDto(productDto);

        model.addAttribute("cart", cartDto);

        return "redirect:/" + path;
    }

    private boolean isAuthorized(Principal principal) {
        return principal != null;
    }
}
