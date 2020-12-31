package shop.service;

import shop.domain.Product;
import shop.dto.ProductDto;

public interface ProductService extends Service<Product> {

    ProductDto create(ProductDto dto);
}
