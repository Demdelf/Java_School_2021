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
    private String userEmail;
    private Long addressId;
    private String address;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String deliveryStatus;
    private Map<ProductDto, Integer> orderProducts;
    private int numberOfProducts;
    private double fullCost;
}
