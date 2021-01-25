package shop.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDto {
    private Map<ProductDto, Double> bestProducts;
    private Map<UserAccountDto, Double> bestCustomers;
    private Double revenue;
}
