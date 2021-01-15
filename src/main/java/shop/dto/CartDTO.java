package shop.dto;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long userId;
    private Integer quantity;
    private Map<ProductDto, Integer> products;

    public void addProductDto(ProductDto dto){
        if (products.containsKey(dto)){
            products.put(dto, products.get(dto) + 1);
        }else {
            products.put(dto, 1);
        }

        quantity++;
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
}
