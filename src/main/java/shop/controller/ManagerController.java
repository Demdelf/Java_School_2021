package shop.controller;

import java.security.Principal;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
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
import shop.dto.CategoryDto;
import shop.dto.OrderDto;
import shop.dto.ProductDto;
import shop.dto.PropertyDto;
import shop.service.CategoryService;
import shop.service.ProductService;
import shop.service.Service;
import shop.service.impl.OrderService;
import shop.service.impl.PropertyService;

@Controller
@RequestMapping("manage")
@RequiredArgsConstructor
public class ManagerController {

    private final ProductService productService;
    private final OrderService orderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Service<PropertyValue> propertyValueService;
    private final PropertyService propertyService;

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
        return "manage/manage";
    }

    @GetMapping("/products/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("products", productService.findAll(10));
        model.addAttribute("categories", categoryService.findAll(10));
        model.addAttribute("values", propertyValueService.findAll(10));
        return "manage/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editOne(
            @PathVariable("id") Long id, Locale locale, Model model
    ) {
        ProductDto productDto = productService.getDtoById(id);

        model.addAttribute("productDto", productDto);
        model.addAttribute("categories", categoryService.getAllCategoryDto(10));

        return "manage/editProduct";
    }

    @PostMapping("/products/edit/{id}")
    public String saveEditOne(
            @PathVariable("id") Long id, Locale locale, Model model,
            @ModelAttribute("productDto") @Valid ProductDto product,
            @ModelAttribute("productDtoFlash") ProductDto productDtoFlash
    ) {
        productService.update(product);
        return "redirect:/manage/products/all";
    }

    @PostMapping("/products/create")
    public String createProduct(
            @ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult result, Model model,
            RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll(10));
            model.addAttribute("categories", categoryService.findAll(10));
            model.addAttribute("values", propertyValueService.findAll(10));
            return "manage/products";
        }

        Product product = productService.create(productDto);
        Long id = product.getId();
        productDto.setId(product.getId());
        redirectAttributes.addFlashAttribute("productDtoFlash", productDto);
        return "redirect:/manage/products/edit/" + id;
    }


    @GetMapping("/categories/all")
    public String getAllCategories(Locale locale, Model model) {
        model.addAttribute("categories", categoryService.findAll(10));
        return "manage/categories";
    }

    @GetMapping("/orders")
    public String getAllOrders(Locale locale, Model model) {
        model.addAttribute("orders", orderService.findAllDtos(100));
        return "manage/orders";
    }

    @GetMapping("/stat")
    public String getStat(Locale locale, Model model) {
        model.addAttribute("stat", orderService.getStatistic());
        return "manage/stat";
    }

    @GetMapping("/orders/{id}")
    public String getManageOrderForm(
            @PathVariable("id") Long id, Locale locale, Model model, @ModelAttribute("path") String path,
            Principal principal
    ) {
        model.addAttribute("paymentStatuses", orderService.getAllPaymentStatuses());
        model.addAttribute("deliveryStatuses", orderService.getAllDeliveryStatuses());
        model.addAttribute("addresses", orderService.getAllCustomerAddressesByOrderId(id));
        model.addAttribute("orderDto", orderService.findOneDto(id));
        return "manage/orderManage";
    }


    @PostMapping("/orders/{id}")
    public String manageOrder(
            @PathVariable("id") Long id, Model model, HttpServletRequest request, Principal principal,
            @ModelAttribute("orderDto") OrderDto orderDto
    ) {
        orderService.update(id, orderDto);
        return "redirect:/manage/orders";
    }

    @GetMapping("/properties/{id}/add")
    public String getAddPropertyPage(
            @PathVariable("id") Long categoryId, Locale locale, Model model
    ) {
        PropertyDto propertyDto = propertyService.getNewPropertyDto(categoryId);

        model.addAttribute("propertyDto", propertyDto);
        model.addAttribute("categoryId", categoryId);

        return "manage/addProperty";
    }

    @PostMapping("/properties/{id}/add")
    public String addProperty(
            @PathVariable("id") Long categoryId, Locale locale, Model model,
            @ModelAttribute("propertyDto") @Valid PropertyDto dto
    ) {
        propertyService.create(dto);

        return "redirect:/manage/categories/edit/" + categoryId;
    }


    @GetMapping("/categories/edit/{id}")
    public String getEditCategoryPage(
            @PathVariable("id") Long id, Locale locale, Model model
    ) {
        CategoryDto categoryDto = categoryService.getDtoById(id);

        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("properties", propertyService.findAllUniq());
        model.addAttribute("categories", categoryService.getAllCategoryDto(10));

        return "manage/editCategory";
    }

    @PostMapping("/categories/edit/{id}")
    public String saveEditCategory(
            @PathVariable("id") Long id, Locale locale, Model model,
            @ModelAttribute("categoryDto") @Valid CategoryDto dto
    ) {
        dto.setId(id);
        categoryService.update(dto);
        return "redirect:/manage/categories/all";
    }

    @PostMapping("/categories/create")
    public String createCategory(
            @ModelAttribute("categoryDto") @Valid CategoryDto categoryDto, BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll(10));
            return "manage/categories";
        }
        Category category = categoryService.createFromDto(categoryDto);
        Long id = category.getId();
        categoryDto.setId(id);
        return "redirect:/manage/categories/all";
    }
}
