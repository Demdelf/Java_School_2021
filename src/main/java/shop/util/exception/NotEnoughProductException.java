package shop.util.exception;

import lombok.Getter;
import lombok.Setter;
import shop.dto.ProductDto;


@Getter
@Setter
public class NotEnoughProductException extends Throwable {

    private ProductDto productDto;

    public NotEnoughProductException(ProductDto o) {
        this.productDto = o;
    }
}
