package shop.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.PropertyValue;
import shop.dto.CartDTO;
import shop.dto.CategoryDto;
import shop.dto.ProductDto;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.Service;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
@SessionAttributes("cart")
public class CustomerController {
    private final ProductService productService;
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
            , RedirectAttributes redirectAttributes) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("cart", cartDTO);
        String path = "customer";
        redirectAttributes.addFlashAttribute("rePath", path);
        return "customer/main";
    }

    @GetMapping("/products/all")
    public String showAllProducts(Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("values", propertyValueService.findAll(10));
        model.addAttribute("cart", cartDTO);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showProduct(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("cart") CartDTO cartDTO
    ) {
        ProductDto productDto = productService.getDtoById(id);
        model.addAttribute("productDto", productDto);
        model.addAttribute("cart", cartDTO);
        return "customer/product";
    }

    @GetMapping("/categories/all")
    public String getAllCategories(Locale locale, Model model) {
        model.addAttribute("categories", categoryService.findAll(10));
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
    ) {
        CategoryDto categoryDto = categoryService.getDtoById(id);
        model.addAttribute("categoryDto", categoryDto);
        List<Product> products = productService.findAllByCategoryId(id);
        model.addAttribute("products", products);
        model.addAttribute("cart", cartDTO);
        return "customer/category";
    }

}
