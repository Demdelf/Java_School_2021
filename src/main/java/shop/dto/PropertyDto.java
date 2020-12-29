package shop.dto;

import java.util.List;
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
public class PropertyDto {

    private Long id;
    private String name;

    private String type;

    private String category;
}
