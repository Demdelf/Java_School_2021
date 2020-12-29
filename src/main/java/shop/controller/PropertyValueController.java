package shop.controller;

import java.util.List;
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
import shop.domain.PropertyValue;
import shop.domain.User;
import shop.service.Service;

@Controller
@RequestMapping("property/values")
@RequiredArgsConstructor
public class PropertyValueController {

    private final Service<PropertyValue> service;

    @GetMapping("/get-all")
    public List<PropertyValue> getAll(int pageSize){
        return service.findAll(pageSize);
    }

    @ModelAttribute("value")
    public PropertyValue formBackingObject() {
        return new PropertyValue();
    }

    @GetMapping("/all")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("values", service.findAll(10));
        return "editValues";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("value") @Valid PropertyValue value, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("values", service.findAll(10));
            return "editValues";
        }

        service.create(value);
        return "redirect:/property/values/all";
    }

}
