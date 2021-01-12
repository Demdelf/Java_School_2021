package shop.controller;

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
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.PropertyValue;
import shop.dto.CategoryDto;
import shop.dto.ProductDto;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.Service;

@Controller
@RequestMapping("customer")
@RequiredArgsConstructor
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

    @GetMapping("")
    public String homeInit(Locale locale, Model model) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        return "customer/main";
    }

    @GetMapping("/products/all")
    public String showAllProducts(Locale locale, Model model) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("values", propertyValueService.findAll(10));
        return "products";
    }

    @GetMapping("/products/{id}")
    public String showProduct(
            @PathVariable("id") Long id, Locale locale, Model model
    ) {
        ProductDto productDto = productService.getDtoById(id);
        model.addAttribute("productDto", productDto);
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
            @PathVariable("id") Long id, Locale locale, Model model
    ) {
        CategoryDto categoryDto = categoryService.getDtoById(id);
        model.addAttribute("categoryDto", categoryDto);
        List<Product> products = productService.findAllByCategoryId(id);
        model.addAttribute("products", products);
        return "customer/category";
    }

}
