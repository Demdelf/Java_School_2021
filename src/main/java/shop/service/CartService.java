package shop.service;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import shop.domain.Cart;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.ProductDto;

public interface CartService extends Service<Cart>{

    CartDTO getCartDtoByUserOrCreate(User user);

    CartDTO clearCart(User user);

    void updateCartByUser(User user, CartDTO dto);

    void addToCartByUser(User user, ProductDto productDto);

    void subFromCartByUser(User user, ProductDto productDto);

    void deleteFromCartByUser(User user, ProductDto productDto);

    default boolean isAuthorized(Principal principal) {
        return principal != null;
    }

    CartDTO getCartDtoForPrincipal(Principal principal, CartDTO cartDto, HttpServletRequest request);
}
