package shop.controller.customer;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.domain.Product;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.OrderDto;
import shop.dto.ProductDto;
import shop.service.UserService;
import shop.service.impl.OrderService;

@Controller
@RequestMapping("customer/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/{id}")
    public String showProduct(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
            , @ModelAttribute("path") String path
    ) {
        OrderDto orderDto = orderService.getDtoById(id);
        model.addAttribute("orderDto", orderDto);
        return "customer/order";
    }

    @PostMapping("/create")
    public String createOrder(
            @ModelAttribute("cart") CartDTO cartDTO, BindingResult result, Model model,
            Principal principal,
            RedirectAttributes redirectAttributes
    ) {
        if (isAuthorized(principal)) {
            Long id = orderService.create(principal, cartDTO);
            return "redirect:/customer/orders/" + id;
        }else {
            return "login";
        }
    }

    private boolean isAuthorized(Principal principal) {
        return principal != null;
    }
}
