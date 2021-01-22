package shop.dto;

import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.Setter;
import shop.domain.CustomerAddress;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long userId;
    private CustomerAddress address;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String deliveryStatus;
    private Map<ProductDto, Integer> orderProducts;

    public int getGoodsCount(){
        return orderProducts.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getGoodsFullCost(){
        int cost = 0;
        for (Entry<ProductDto, Integer> e: orderProducts.entrySet()
        ) {
            cost += e.getKey().getPrice() * e.getValue();
        }
        return cost;
    }
}
