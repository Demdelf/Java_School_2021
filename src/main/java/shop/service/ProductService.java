package shop.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import shop.domain.Category;
import shop.domain.Product;
import shop.dto.ProductDto;


public interface ProductService extends Service<Product> {

    Product create(ProductDto dto);

    ProductDto update(ProductDto productDtoFlash);

    ProductDto convertProductToDto(Product product);

    ProductDto getDtoById(long id);

    List<Product> findAllByCategoryId(Long categoryId);
}
