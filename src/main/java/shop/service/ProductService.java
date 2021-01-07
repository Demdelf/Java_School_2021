package shop.service;

import org.springframework.transaction.annotation.Transactional;
import shop.domain.Product;
import shop.dto.ProductDto;


public interface ProductService extends Service<Product> {

    Product create(ProductDto dto);

    ProductDto update(ProductDto productDtoFlash);

    ProductDto convertProductToDto(Product product);
}
