package shop.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class UserRegDto {
    private Long id;

    @NotEmpty
    @Size(max = 50)
    @Pattern(regexp = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]+$")
    private String email;

    @Size(min = 6, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;

    @Size(min = 6, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String passwordConfirm;
}
