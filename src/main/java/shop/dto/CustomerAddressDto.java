package shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerAddressDto {
    private Long id;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private String house;
    private String apartment;
    private Long userId;
}
