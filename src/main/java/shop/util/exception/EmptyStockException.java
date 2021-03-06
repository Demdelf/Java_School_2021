package shop.util.exception;

import lombok.Getter;
import lombok.Setter;
import shop.dto.ProductDto;

@Getter
@Setter
public class EmptyStockException extends Throwable {

    private ProductDto productDto;

    public EmptyStockException(ProductDto productDto) {
        this.productDto = productDto;
    }
}
