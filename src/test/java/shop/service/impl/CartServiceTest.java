package shop.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import shop.dao.impl.CartDao;
import shop.domain.Cart;
import shop.domain.Property;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CartServiceTest {

    private CartService cartService;

    @Mock
    CartDao cartDao;
    @Mock
    UserService userService;
    @Mock
    ProductService productService;

    CartDTO cartDTO;
    Cart cart;
    List<Cart> carts;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(cartDao, userService);
        cartDTO = new CartDTO();
        cartDTO.setId(1L);
        cartDTO.setSum(200.00);
        cartDTO.setQuantity(2);

        user = new User();
        user.setEmail("name");
        user.setId(1L);

        cart = new Cart();
        cart.setId(1L);
        cart.setQuantity(2);
        cart.setUser(user);

        carts = new ArrayList<>();
        carts.add(cart);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCartDtoByUserOrCreate() {
//        when(cartDao.getAllCartsByUserId(any(Long.class))).thenReturn(carts);
//        Property property1 = propertyService.findOne(1L);
//        assertNotNull(property1);
//        assertEquals(Long.valueOf(1), property1.getId());
    }

    @Test
    void convertCartToCartDto() {
        assertEquals(cartDTO.getId(), cartService.convertCartToCartDto(cart).getId());
    }

    @Test
    void clearCart() {
    }

    @Test
    void updateCartByUser() {
    }

    @Test
    void addToCartByUser() {
    }

    @Test
    void subFromCartByUser() {
    }

    @Test
    void deleteFromCartByUser() {
    }

    @Test
    void getCartDtoForPrincipal() {
    }

    @Test
    void convertCartsToCartDto() {
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByName() {
    }

    @Test
    void update() {
    }

    @Test
    void getCartDtoAfterLogin() {
    }

    @Test
    void getCartDtoByPrincipal() {
    }
}