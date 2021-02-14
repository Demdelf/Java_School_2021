package shop.dto;

import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.domain.Category;
import shop.domain.PropertyValue;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private String category;

    private Double weight;

    private Double volume;

    private Integer stock;

    private Boolean visible;

    private byte[] image;
    private String stringImage;

    private Map<Long, String> propertyValues;
    private Map<Long, String> properties;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        ProductDto dto = (ProductDto) obj;

        return (dto.id.equals(this.id));
    }
    @Override
    public int hashCode()
    {
        return Math.toIntExact(this.id);
    }
}
