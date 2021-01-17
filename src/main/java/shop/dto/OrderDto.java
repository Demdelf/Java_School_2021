package shop.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import shop.domain.CustomerAddress;
import shop.domain.DeliveryMethod;
import shop.domain.DeliveryStatus;
import shop.domain.PaymentMethod;
import shop.domain.PaymentStatus;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long userId;
    private CustomerAddress address;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private PaymentStatus paymentStatus;
    private DeliveryStatus deliveryStatus;
    private Map<ProductDto, Integer> orderProducts;
}
