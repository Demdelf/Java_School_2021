package shop.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class UserAccountDto {
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String birthday;

}
