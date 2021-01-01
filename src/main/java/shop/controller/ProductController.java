package shop.controller;

import java.util.Locale;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.PropertyValue;
import shop.dto.ProductDto;
import shop.service.ProductService;
import shop.service.Service;
import shop.service.impl.CategoryService;
import shop.service.impl.PropertyValueService;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @Autowired
    private Service<Category> categoryService;
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

    @GetMapping("/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("values", propertyValueService.findAll(10));
        return "editProducts";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("productDto") @Valid ProductDto product, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll(10));
            model.addAttribute("categories", categoryService.findAll(10));
            model.addAttribute("values", propertyValueService.findAll(10));
            return "editProducts";
        }

        productService.create(product);
        return "redirect:/products/all";
    }
}
