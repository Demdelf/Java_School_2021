package shop.controller.customer;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import shop.dto.ProductDto;
import shop.dto.UserRegDto;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.Service;
import shop.service.impl.UserService;
import shop.util.exception.EmailExistsException;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
@SessionAttributes({"cart", "path"})

public class CustomerController {

    private static final String CUSTOMER_NEW = "customer/new";
    private final ProductService productService;
    private final UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Service<PropertyValue> propertyValueService;

    @ModelAttribute("product")
    public Product formBackingObject() {
        return new Product();
    }

    @ModelAttribute("productDto")
    public ProductDto getNewDto() {
        return new ProductDto();
    }

    @ModelAttribute("categoryDto")
    public CategoryDto getNewCategoryDto() {
        return new CategoryDto();
    }

    @ModelAttribute("path")
    public String getPath() {
        return "customer";
    }

    @ModelAttribute("cart")
    public CartDTO cartDTO(){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setQuantity(0);
        cartDTO.setProducts(new HashMap<>());
        return cartDTO;
    }

    @GetMapping("username")
    @ResponseBody
    public String currentUserName(Principal principal){
        return principal.getName();
    }



    @GetMapping("")
    public String homeInit(Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
            , @ModelAttribute("path") String path
            , RedirectAttributes redirectAttributes) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        cartDTO.setFromCart(false);
        model.addAttribute("cart", cartDTO);
        model.addAttribute("path", "customer");

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        Set<String> roles = authentication.getAuthorities().stream()
//                .map(r -> r.getAuthority()).collect(Collectors.toSet());

//        return "customer/main";
        return "customer/bootProd";
    }

    @GetMapping("/products/all")
    public String showAllProducts(Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
            , @ModelAttribute("path") String path
    ) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("values", propertyValueService.findAll(10));
        cartDTO.setFromCart(false);
        model.addAttribute("cart", cartDTO);
        model.addAttribute("path", "customer/products/all");
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showProduct(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
            , @ModelAttribute("path") String path
    ) {
        ProductDto productDto = productService.getDtoById(id);
        model.addAttribute("productDto", productDto);
        cartDTO.setFromCart(false);
        model.addAttribute("cart", cartDTO);
        model.addAttribute("path", "customer/products/" + id);
        return "customer/product";
    }

    @GetMapping("/categories/all")
    public String getAllCategories(Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
            , @ModelAttribute("path") String path) {
        model.addAttribute("categories", categoryService.findAll(10));
        cartDTO.setFromCart(false);
        model.addAttribute("path", "categories/all");
        model.addAttribute("cart", cartDTO);
        return "categories";
    }


    /**
     * Show all products of category
     *
     * @param id category id
     *
     * @return category page
     */
    @GetMapping("/categories/{id}")
    public String showCategory(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
            , @ModelAttribute("path") String path
    ) {
        CategoryDto categoryDto = categoryService.getDtoById(id);
        model.addAttribute("categoryDto", categoryDto);
        List<Product> products = productService.findAllByCategoryId(id);
        cartDTO.setFromCart(false);
        model.addAttribute("products", products);
        model.addAttribute("cart", cartDTO);
        model.addAttribute("path", "customer/categories/" + id);
        return "customer/category";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegDto", new UserRegDto());
        return CUSTOMER_NEW;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postRegistrationForm(
            Model model,
            @Valid UserRegDto userRegDto, BindingResult bindingResult,
            @ModelAttribute(value = "cart") CartDTO cartDto
    ) {
        model.addAttribute("userRegDto", userRegDto); // place user data back to redirect him back to pre-filled registration form
        if (bindingResult.hasErrors()){
            return CUSTOMER_NEW;
        }
        User user;
        try {
            user = userService.saveUserFromUserRegDto(userRegDto);
        } catch (EmailExistsException e) {
            bindingResult.addError(e.getFieldError());
            return CUSTOMER_NEW;
        }

        return "login";
    }

}
