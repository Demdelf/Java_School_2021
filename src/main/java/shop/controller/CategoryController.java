package shop.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.domain.Category;
import shop.domain.Product;
import shop.dto.CategoryDto;
import shop.dto.ProductDto;
import shop.service.CategoryService;

@Controller
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @ModelAttribute("categoryDto")
    public CategoryDto getNewDto() {
        return new CategoryDto();
    }

    @GetMapping("/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("categories", categoryService.findAll(10));
        return "categories";
    }

    @GetMapping("/edit/{id}")
    public String editOne(
            @PathVariable("id") Long id, Locale locale, Model model
    ) {
        CategoryDto categoryDto = categoryService.getDtoById(id);

        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("categories", categoryService.getAllCategoryDto(10));

        return "editCategory";
    }

    @PostMapping("/edit/{id}")
    public String saveEditOne(
            @PathVariable("id") Long id, Locale locale, Model model
            ,@ModelAttribute("categoryDto") @Valid CategoryDto dto
    ) {
        dto.setId(id);
        categoryService.update(dto);
        return "redirect:/categories/all";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute("categoryDto") @Valid CategoryDto categoryDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll(10));
            return "categories";
        }
        Category category = categoryService.create(categoryDto);
        Long id = category.getId();
        categoryDto.setId(id);
        return "redirect:/categories/all";
    }
}
