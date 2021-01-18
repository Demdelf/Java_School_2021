package shop.dto;

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

    public void addProductDto(ProductDto dto){
        if (products.containsKey(dto)){
            products.put(dto, products.get(dto) + 1);
        }else {
            products.put(dto, 1);
        }

        quantity++;
    }

    public void addProductDto(ProductDto dto, Integer q){
        if (products.containsKey(dto)){
            products.put(dto, products.get(dto) + q);
        }else {
            products.put(dto, q);
        }

        quantity += q;
    }

    public void subProductDto(ProductDto productDto) {
        int q = products.get(productDto);
        if (q == 1){
            products.remove(productDto);
        }else {
            products.put(productDto, q - 1);
        }
        quantity--;
    }

    public void deleteProductDto(ProductDto productDto) {
        int q = products.get(productDto);
        products.remove(productDto);
        quantity -= q;
    }

    public void clear() {
        quantity = 0;
        products.clear();
    }

    public void addProductsFromAnotherDto(CartDTO cartDto) {
        for (Entry<ProductDto, Integer> map: cartDto.products.entrySet()
        ) {
            addProductDto(map.getKey(), map.getValue());
        }
    }
}
