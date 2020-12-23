package shop.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.domain.Customer;
import shop.service.CustomerService;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll(){
        return customerService.findAll();
    }

    @PostMapping
    public Long create(@RequestBody Customer customer){
        Long id = customerService.create(customer).getId();
        return id;
    }

}
