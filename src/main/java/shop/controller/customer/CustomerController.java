package shop.controller.customer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.domain.Product;
import shop.domain.PropertyValue;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.CategoryDto;
import shop.dto.FilterDto;
import shop.dto.OrderDto;
import shop.dto.ProductDto;
import shop.dto.UserRegDto;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.Service;
import shop.service.impl.CartService;
import shop.service.impl.UserService;
import shop.util.exception.EmailExistsException;

import org.apache.log4j.Logger;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
@SessionAttributes({"cart", "path", "cartAfterLogin"})
@Slf4j
public class CustomerController {
    private static final Logger logger =
            Logger.getLogger(CustomerController.class);
    private static final String CUSTOMER_NEW = "customer/new";
    private final ProductService productService;
    private final CartService cartService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Service<PropertyValue> propertyValueService;

//    @ModelAttribute("product")
//    public Product formBackingObject() {
//        return new Product();
//    }
//
//    @ModelAttribute("productDto")
//    public ProductDto getNewDto() {
//        return new ProductDto();
//    }

    @ModelAttribute("categoryDto")
    public CategoryDto getNewCategoryDto() {
        return new CategoryDto();
    }

    @ModelAttribute("path")
    public String getPath() {
        return "customer";
    }

    @ModelAttribute("cart")
    public CartDTO cartDTO() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setQuantity(0);
        cartDTO.setSum(0.0);
        cartDTO.setProducts(new HashMap<>());
        return cartDTO;
    }

    @ModelAttribute("cartAfterLogin")
    public CartDTO cartDTOAfterLogin() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setQuantity(0);
        cartDTO.setSum(0.0);
        cartDTO.setProducts(new HashMap<>());
        return cartDTO;
    }

    @GetMapping("username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("after-login")
    public String afterLogin(
            Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO, @ModelAttribute("path") String path,
            RedirectAttributes redirectAttributes, Principal principal, HttpServletRequest request
    ) {
        model.addAttribute("products", productService.findAllVisible(100));
        model.addAttribute("categories", categoryService.findAllVisible(100));
        CartDTO cart = cartService.getCartDtoAfterLogin(principal, cartDTO, request);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("path", "customer");

        return "customer/main";
    }



    @GetMapping("")
    public String homeInit(
            Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO, @ModelAttribute("path") String path,
            Principal principal, @ModelAttribute("filterDto") FilterDto filterDto
    ) {
        log.info("Log Alive");
        logger.info("Handle log Alive");
        model.addAttribute("products", productService.findAllVisible(100));
        model.addAttribute("categories", categoryService.findAllVisible(100));

        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("path", "customer");

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        Set<String> roles = authentication.getAuthorities().stream()
//                .map(r -> r.getAuthority()).collect(Collectors.toSet());

        return "customer/main";
//        return "customer/bootProd";
    }

    @PostMapping("")
    public String getMainFiltered(
            Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO, @ModelAttribute("path") String path,
            Principal principal, @ModelAttribute("filterDto") FilterDto filterDto
    ) {
        model.addAttribute("products", productService.findAllFiltered(filterDto));
        model.addAttribute("categories", categoryService.findAll(100));
        model.addAttribute("filterDto", filterDto);
        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("path", "customer");

        return "customer/main";
    }

    @GetMapping("/products/all")
    public String showAllProducts(
            Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO, @ModelAttribute("path") String path,
            Principal principal
    ) {
        model.addAttribute("products", productService.findAllVisible(100));
        model.addAttribute("categories", categoryService.findAllVisible(100));
        model.addAttribute("values", propertyValueService.findAll(100));
        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("path", "customer/products/all");
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showProduct(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO,
            @ModelAttribute("path") String path, Principal principal, HttpServletRequest request
    ) {
        model.addAttribute("categories", categoryService.findAllVisible(100));
        ProductDto productDto = productService.getDtoById(id);
        List<String> properties = new ArrayList<>(productDto.getProperties().values());
        model.addAttribute("properties", properties);
        List<String> propertyValues = new ArrayList<>(productDto.getPropertyValues().values());
        model.addAttribute("propertyValues", propertyValues);
        model.addAttribute("productDto", productDto);
        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("path", "customer/products/" + id);
        return "customer/product";
    }

    @GetMapping("/categories/all")
    public String getAllCategories(
            Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO, @ModelAttribute("path") String path,
            Principal principal, HttpServletRequest request
    ) {
        model.addAttribute("categories", categoryService.findAllVisible(100));
        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);

        model.addAttribute("path", "categories/all");
        return "categories";
    }


    /**
     * Show all products of category
     *
     * @param id category id
     * @return category page
     */
    @GetMapping("/categories/{id}")
    public String showCategory(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO,
            @ModelAttribute("path") String path, Principal principal, @ModelAttribute("filterDto") FilterDto filterDto
    ) {
        model.addAttribute("categoryDto", categoryService.getDtoById(id));
        model.addAttribute("products", productService.findAllByCategoryId(id));
        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("categories", categoryService.findAllVisible(100));
        model.addAttribute("path", "customer/categories/" + id);
        return "customer/category";
    }

    @PostMapping("/categories/{id}")
    public String getCategoryFiltered(
            @PathVariable("id") Long id, Model model, @ModelAttribute("cart") CartDTO cartDTO,
            @ModelAttribute("path") String path, Principal principal, @ModelAttribute("filterDto") FilterDto filterDto
    ) {
        model.addAttribute("categoryDto", categoryService.getDtoById(id));
        model.addAttribute("products", productService.findAllByCategoryIdFiltered(id, filterDto));
        model.addAttribute("categories", categoryService.findAllVisible(100));
        model.addAttribute("filterDto", filterDto);
        CartDTO cart = cartService.getCartDtoByPrincipal(principal, cartDTO);
        cart.setFromCart(false);
        model.addAttribute("cart", cart);
        model.addAttribute("path", "customer");

        return "customer/category";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegDto", new UserRegDto());
        return CUSTOMER_NEW;
    }

//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    public String postRegistrationForm(
//            Model model,
//            @Valid UserRegDto userRegDto, BindingResult bindingResult,
//            @ModelAttribute(value = "cart") CartDTO cartDto
//    ) {
//        model.addAttribute("userRegDto", userRegDto); // place user data back to redirect him back to pre-filled registration form
//        if (bindingResult.hasErrors()){
//            return CUSTOMER_NEW;
//        }
//        User user;
//        try {
//            user = userService.saveUserFromUserRegDto(userRegDto);
//        } catch (EmailExistsException e) {
//            bindingResult.addError(e.getFieldError());
//            return CUSTOMER_NEW;
//        }
//
//        return "login";
//    }

}
