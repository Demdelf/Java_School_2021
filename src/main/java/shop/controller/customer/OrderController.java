package shop.controller.customer;

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import shop.dto.CartDTO;
import shop.dto.OrderDto;
import shop.service.impl.OrderService;

@Controller
@RequestMapping("customer/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/create")
    public String editOrder(Locale locale, Model model
            , @ModelAttribute("path") String path, Principal principal
    ) {
        if (isAuthorized(principal)) {
            OrderDto orderDto = orderService.getNewOrderDto(principal);
            if (orderDto == null){
                return "404";
            }
            model.addAttribute("paymentMethods", orderService.getAllPaymentMethods());
            model.addAttribute("deliveryMethods", orderService.getAllDeliveryMethods());
            model.addAttribute("addresses", orderService.getAllCustomerAddresses(principal));
            model.addAttribute("orderDto", orderDto);
            return "customer/order";
        }else {
            return "login";
        }
    }

    @GetMapping("")
    public String userOrders(Locale locale, Model model, Principal principal){
        List<OrderDto> orderDtoList = orderService.getAllUserOrders(principal);
        model.addAttribute("orderDtoList", orderDtoList);
        return "customer/ordersHistory";
    }


//    @PostMapping("/new")
//    public String createOrder(Model model,
//            Principal principal, @ModelAttribute("orderDto") OrderDto orderDto
//    ) {
//        if (isAuthorized(principal)) {
//            Long id = orderService.create(principal);
//            return "redirect:/customer/create";
//        }else {
//            return "login";
//        }
//    }

    @PostMapping("/create")
    public String saveOrder(Model model, HttpServletRequest request,
            Principal principal, @ModelAttribute("orderDto") OrderDto orderDto
    ) {
        if (isAuthorized(principal)) {

            orderService.create(principal, orderDto, request);
            return "customer/thanks";
        }else {
            return "login";
        }
    }

//    @PostMapping("/update/{id}")
//    public String createOrder(@PathVariable("id") Long id, Locale locale, Model model
//            , @ModelAttribute("updateOrderDto") OrderDto updateOrderDto
//    ) {
//        OrderDto orderDto = orderService.updateMethodsById(id, updateOrderDto);
//
//        model.addAttribute("paymentMethods", orderService.getAllPaymentMethods());
//        model.addAttribute("orderDto", orderDto);
//        return "customer/orders/" + id;
//    }

    private boolean isAuthorized(Principal principal) {
        return principal != null;
    }
}
