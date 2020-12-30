package shop.controller;

import java.util.Locale;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.domain.Product;
import shop.dto.ProductDto;
import shop.service.impl.CategoryService;
import shop.service.impl.ProductService;
import shop.service.impl.PropertyValueService;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final PropertyValueService propertyValueService;

    @ModelAttribute("product")
    public Product formBackingObject() {
        return new Product();
    }

    @GetMapping("/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", productService.findAll(10));
        model.addAttribute("values", productService.findAll(10));
        return "editProducts";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("product") @Valid ProductDto productDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll(10));
            model.addAttribute("categories", productService.findAll(10));
            model.addAttribute("values", productService.findAll(10));
            return "editProducts";
        }

        productService.create(productDto);
        return "redirect:/products/all";
    }
}
