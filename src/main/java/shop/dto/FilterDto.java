package shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDto {
    private double minPrice;
    private double maxPrice = 1000000.0;
}
