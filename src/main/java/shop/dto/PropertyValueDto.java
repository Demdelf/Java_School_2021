package shop.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.domain.Product;
import shop.domain.Property;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyValueDto {

    private Long id;
    private String name;
    private String value;
    private String property;
    private String product;
}
