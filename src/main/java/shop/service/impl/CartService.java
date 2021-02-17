package shop.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.CartDao;
import shop.domain.Cart;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.ProductDto;
import shop.service.ProductService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService implements shop.service.CartService {

    private final CartDao cartDao;
    private final UserService userService;
    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public CartDTO getCartDtoByUserOrCreate(User user) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        if (carts.isEmpty()) {
            return convertCartToCartDto(createCart(user));
        }
        return convertCartsToCartDto(carts);
    }

    @Transactional
    CartDTO convertCartToCartDto(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        if (cart.getProduct() != null) {
            cartDTO.setSum(cart.getProduct().getPrice() * cartDTO.getQuantity());
        } else {
            cartDTO.setSum(0.0);
        }

        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setProducts(new HashMap<>());
        cartDTO.setId(cart.getId());
        cartDTO.setUserId(cart.getUser().getId());
        return cartDTO;
    }

    @Override
    @Transactional
    public CartDTO clearCart(User user) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        for (Cart c : carts) {
            cartDao.delete(c);
        }
        return getFreshCartDTOForUser(user);
    }

    @Override
    @Transactional
    public void updateCartByUser(User user, CartDTO dto) {
        clearCart(user);
        for (Entry<ProductDto, Integer> e : dto.getProducts().entrySet()) {
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
        for (Cart c : carts) {
            if (c.getProduct().getId().equals(productDto.getId())) {
                c.setQuantity(c.getQuantity() + 1);
                update(c);
                haveProduct = true;
                break;
            }
        }
        if (!haveProduct) {
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(productService.findOne(productDto.getId()));
            cart.setQuantity(1);
            create(cart);
//            update(cart);
        }
    }

    @Override
    @Transactional
    public void subFromCartByUser(User user, ProductDto productDto) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        for (Cart c : carts) {
            if (c.getProduct().getId().equals(productDto.getId())) {
                int quantity = c.getQuantity();
                if (quantity == 1) {
                    delete(c);
                } else {
                    c.setQuantity(c.getQuantity() - 1);
                    update(c);
                }
                break;
            }
        }
    }

    @Override
    @Transactional
    public void deleteFromCartByUser(User user, ProductDto productDto) {
        List<Cart> carts = cartDao.getAllCartsByUserId(user.getId());
        for (Cart c : carts) {
            if (c.getProduct().getId().equals(productDto.getId())) {
                delete(c);
                break;
            }
        }
    }

    @Override
    @Deprecated
    public CartDTO getCartDtoForPrincipal(
            Principal principal, CartDTO cartDto, HttpServletRequest request
    ) {
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            CartDTO carDtoFromDB = getCartDtoByUserOrCreate(user);
            if (!carDtoFromDB.getProducts().isEmpty()) {
                carDtoFromDB.addProductsFromAnotherDto(cartDto);
            }
            updateCartByUser(user, carDtoFromDB);
//            carDtoFromDB.setFromCart(true);
            request.getSession().setAttribute("cartDto", getFreshCartDTOForUser(user));
            return carDtoFromDB;
        } else {
//            cartDto.setFromCart(true);
            return cartDto;
        }
    }

    private CartDTO getFreshCartDTOForUser(User user) {
        CartDTO cartDTO = convertCartToCartDto(createCart(user));
        cartDTO.setSum(0.0);
        return cartDTO;
    }

    private CartDTO getFreshCartDTO() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setQuantity(0);
        cartDTO.setSum(0.0);
        return cartDTO;
    }


    private Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setQuantity(0);
        return cart;
//        return cartDao.create(cart);
    }

    @Transactional
    CartDTO convertCartsToCartDto(List<Cart> carts) {
        CartDTO cartDTO = new CartDTO();
        Map<ProductDto, Integer> products = new HashMap<>();
        int q = 0;
        double sum = 0.0;
        for (Cart c : carts) {
//            if (cartDTO.getId() == null) {
//                cartDTO.setId(c.getId());
//            }
            if (cartDTO.getUserId() == null && c.getUser() != null) {
                cartDTO.setUserId(c.getUser().getId());
            }

            ProductDto productDto = productService.getDtoById(c.getProduct().getId());
            products.put(productDto, c.getQuantity());
            q += c.getQuantity();
            sum += productDto.getPrice() * c.getQuantity();
        }
        cartDTO.setSum(sum);
        cartDTO.setQuantity(q);
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
        return cartDao.create(cart);
    }

    @Override
    @Transactional
    public void delete(Cart cart) {
        cartDao.delete(cart);
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

    public CartDTO getCartDtoAfterLogin(
            Principal principal, CartDTO cartDTO, HttpServletRequest request
    ) {
        CartDTO dto;
        User user = (User) userService.loadUserByUsername(principal.getName());
        dto = getCartDtoByUserOrCreate(user);
        if (!cartDTO.getProducts().isEmpty()) {
//            dto.addProductsFromAnotherDto(cartDTO);
            Map<ProductDto, Integer> sumProducts = dto.getProducts();
            Double sum = dto.getSum();
            Integer quantity = dto.getQuantity();
//            Integer quantity = 0;
            for (Entry<ProductDto, Integer> sessionProduct : cartDTO.getProducts().entrySet()) {
                Integer q = sessionProduct.getValue();
                Integer stock = sessionProduct.getKey().getStock();
                Integer resultQuantity = 0;
                if (sumProducts.containsKey(sessionProduct.getKey())) {
                    Integer productQuantityFromBD = sumProducts.get(sessionProduct.getKey());
                    resultQuantity = productQuantityFromBD + q < stock ? productQuantityFromBD + q : stock;
                    sumProducts.put(sessionProduct.getKey(), resultQuantity);
                } else {
                    resultQuantity = q < stock ? q : stock;
                    sumProducts.put(sessionProduct.getKey(), resultQuantity);
                }

                sum += sessionProduct.getKey().getPrice() * resultQuantity;
                quantity += resultQuantity;
            }
            dto.setProducts(sumProducts);
            dto.setSum(sum);
            dto.setQuantity(sumProducts.values().stream().mapToInt(Integer::intValue).sum());
            save(dto);
            request.getSession().setAttribute("cartDto", getFreshCartDTO());
        }
        return dto;
    }

    private void save(CartDTO dto) {
        List<Cart> carts = convertDtoToCarts(dto);
        for (Cart c : carts) {
            Cart cartFromDB = cartDao.findByUserAndProduct(c);
            if (cartFromDB != null) {
                cartFromDB.setQuantity(c.getQuantity());
                cartDao.updateR(cartFromDB);
            } else {
                cartDao.create(c);
            }
        }

    }

    private List<Cart> convertDtoToCarts(CartDTO dto) {
        List<Cart> carts = new ArrayList<>();
        for (Entry<ProductDto, Integer> e : dto.getProducts().entrySet()) {
            Cart cart = new Cart();
            cart.setQuantity(e.getValue());
            cart.setUser(userService.find(dto.getUserId()));
            cart.setProduct(productService.findOne(e.getKey().getId()));
            carts.add(cart);
        }
        return carts;
    }


    public CartDTO getCartDtoByPrincipal(Principal principal, CartDTO cartDTO) {
        if (isAuthorized(principal)) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            return getCartDtoByUserOrCreate(user);
        } else {
            return cartDTO;
        }
    }
}
