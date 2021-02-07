package shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.dto.ProductDto;
import shop.dto.ProductsDto;
import shop.service.impl.OrderService;
import shop.service.impl.ProductService;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    private final OrderService orderService;

    @GetMapping
    public ProductsDto getBestProducts(){
        Map<ProductDto, Double> bestProducts = orderService.getBestProducts();
        List<ProductDto> dtos = new ArrayList<>();
        ProductsDto productsDto = new ProductsDto();
        for (Entry<ProductDto, Double> e : bestProducts.entrySet()
        ) {
                dtos.add(e.getKey());
        }
        productsDto.setProducts(dtos);
        System.out.println("getBestProducts has called");
        return productsDto;
    }
}
