package shop.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long userId;
    private Integer quantity;
    private Map<ProductDto, Integer> products;
    private boolean fromCart = false;
    private Double sum;

    public Map<Long, Integer> getFrontProducts(Map<ProductDto, Integer> products){
        Map<Long, Integer> map = new HashMap<>();
        for (Entry<ProductDto, Integer> e : products.entrySet()
        ) {
            map.put(e.getKey().getId(), e.getValue());
        }
        return map;
    }

    public void addProductDto(ProductDto dto){
        if (products.containsKey(dto)){
            products.put(dto, products.get(dto) + 1);
        }else {
            products.put(dto, 1);
        }
        sum += dto.getPrice();
        quantity++;
    }

    public void addProductDto(ProductDto dto, Integer q){
        if (products.containsKey(dto)){
            products.put(dto, products.get(dto) + q);
        }else {
            products.put(dto, q);
        }
        sum += dto.getPrice()*q;
        quantity += q;
    }

    public void subProductDto(ProductDto productDto) {
        int q = products.get(productDto);
        if (q == 1){
            products.remove(productDto);
        }else {
            products.put(productDto, q - 1);
        }
        sum -= productDto.getPrice();
        quantity--;
    }

    public void deleteProductDto(ProductDto productDto) {
        int q = products.get(productDto);
        products.remove(productDto);
        sum -= productDto.getPrice()*q;
        quantity -= q;
    }

    public void clear() {
        quantity = 0;
        sum = 0.0;
        products.clear();
    }

    public void addProductsFromAnotherDto(CartDTO cartDto) {
        for (Entry<ProductDto, Integer> map: cartDto.products.entrySet()
        ) {
            addProductDto(map.getKey(), map.getValue());
        }
    }
}
