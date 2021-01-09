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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.domain.Category;
import shop.domain.Product;
import shop.domain.PropertyValue;
import shop.dto.ProductDto;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.Service;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

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

    @GetMapping("/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("values", propertyValueService.findAll(10));
        return "products";
    }

    @GetMapping("/edit/{id}")
    public String editOne(
            @PathVariable("id") Long id, Locale locale, Model model
    ) {
        ProductDto productDto = productService.getDtoById(id);

        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", categoryService.getAllCategoryDto(10));

        return "editProduct";
    }

    @PostMapping("/edit/{id}")
    public String saveEditOne(
            @PathVariable("id") Long id, Locale locale, Model model
            ,@ModelAttribute("productDto") @Valid ProductDto product
            ,@ModelAttribute("productDtoFlash") ProductDto productDtoFlash
    ) {
        productService.update(product);
        return "redirect:/products/all";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult result, Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll(10));
            model.addAttribute("categories", categoryService.findAll(10));
            model.addAttribute("values", propertyValueService.findAll(10));
            return "products";
        }

        Product product = productService.create(productDto);
        Long id = product.getId();
        productDto.setId(product.getId());
        redirectAttributes.addFlashAttribute("productDtoFlash", productDto);
        return "redirect:/products/edit/" + id;
    }
}
