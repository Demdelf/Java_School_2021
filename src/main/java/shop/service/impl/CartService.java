package shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.CartDao;
import shop.domain.Cart;
import shop.domain.Product;
import shop.domain.Property;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.ProductDto;
import shop.service.ProductService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService implements shop.service.CartService {

    private final CartDao cartDao;
    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public CartDTO getCartDtoByUserIdOrCreate(User user) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        if (carts == null){
            return convertCartToCartDto(createCart(user));
        }
        return convertCartsToCartDto(carts);
    }

    @Transactional
    private CartDTO convertCartToCartDto(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProducts(new HashMap<>());
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        return cartDTO;
    }

    @Override
    @Transactional
    public CartDTO clearCart(User user) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        for (Cart c: carts
        ) {
            cartDao.delete(c);
        }
        return convertCartToCartDto(createCart(user));
    }

    @Override
    @Transactional
    public void updateCartByUser(User user, CartDTO dto) {
        clearCart(user);
        for (Entry<ProductDto, Integer> e: dto.getProducts().entrySet()
        ) {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(productService.findOne(e.getKey().getId()));
            cart.setQuantity(e.getValue());
            cartDao.create(cart);
        }
    }

    @Override
    @Transactional
    public void addToCartByUser(User user, ProductDto productDto) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        boolean haveProduct = false;
        for (Cart c: carts
        ) {
            if(c.getProduct().getId().equals(productDto.getId())){
                c.setQuantity(c.getQuantity() + 1);
                update(c);
                haveProduct = true;
                break;
            }
        }
        if (!haveProduct){
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(productService.findOne(productDto.getId()));
            cart.setQuantity(1);
            create(cart);
        }
    }

    private Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartDao.create(cart);
    }

    @Transactional
    private CartDTO convertCartsToCartDto(List<Cart> carts) {
        CartDTO cartDTO = new CartDTO();
        Map<ProductDto, Integer> products = new HashMap<>();
        for (Cart c: carts
        ) {
            if (cartDTO.getId() == null){
                cartDTO.setId(c.getId());
            }
            if (cartDTO.getUserId() == null && c.getUser() != null){
                cartDTO.setUserId(c.getUser().getId());
            }

            ProductDto productDto = productService.getDtoById(c.getProduct().getId());
            products.put(productDto, c.getQuantity());
        }
        cartDTO.setProducts(products);
        return cartDTO;
    }

    @Override
    public Cart findOne(long id) {
        return null;
    }

    @Override
    public List<Cart> findAll(int pageSize) {
        return null;
    }

    @Override
    public Cart create(Cart cart) {
        return null;
    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public Cart findByName(String name) {
        return null;
    }

    @Override
    @Transactional
    public Cart update(Cart p) {
        return cartDao.update(p);
    }
}
