package shop.service;

import shop.domain.Cart;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.ProductDto;

public interface CartService extends Service<Cart>{

    CartDTO getCartDtoByUserIdOrCreate(User user);

    CartDTO clearCart(User user);

    void updateCartByUser(User user, CartDTO dto);

    void addToCartByUser(User user, ProductDto productDto);
}
