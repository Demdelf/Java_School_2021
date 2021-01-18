package shop.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.OrderDao;
import shop.domain.Order;
import shop.domain.OrderProduct;
import shop.domain.PaymentMethod;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.OrderDto;
import shop.dto.ProductDto;
import shop.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService implements shop.service.OrderService {

    private final OrderDao orderDao;
    private final UserService userService;
    private final CartService cartService;

    @Override
    public Order findOne(long id) {
        return orderDao.findOne(id).orElse(null);
    }

    @Override
    public List<Order> findAll(int pageSize) {
        return null;
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public Order findByName(String name) {
        return null;
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public OrderDto getDtoById(Long id) {
        Order order = findOne(id);
        return convertOrderToDto(order);
    }

    private OrderDto convertOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        Map<ProductDto, Integer> products = new HashMap<>();
        for (OrderProduct orderProduct: order.getOrderProducts()
        ) {
            ProductDto productDto = new ProductDto();
            productDto.setId(orderProduct.getId());
            productDto.setName(orderProduct.getName());
            productDto.setPrice(orderProduct.getPrice());
            productDto.setWeight(orderProduct.getWeight());
            productDto.setVolume(orderProduct.getVolume());
            products.put(productDto, orderProduct.getQuantity());
        }
        orderDto.setOrderProducts(products);
        orderDto.setAddress(order.getAddress());
        orderDto.setDeliveryMethod(order.getDeliveryMethod());
        orderDto.setDeliveryStatus(order.getDeliveryStatus());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setPaymentStatus(order.getPaymentStatus());
        return orderDto;
    }

    @Override
    public OrderDto createOrderDtoFromCartDto(CartDTO cartDTO) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(cartDTO.getUserId());
        orderDto.setOrderProducts(cartDTO.getProducts());
        orderDto.setDeliveryMethod(orderDao.findDeliveryMethod(1));
        orderDto.setDeliveryStatus(orderDao.findDeliveryStatus(1));
        orderDto.setPaymentMethod(orderDao.findPaymentMethod(1));
        orderDto.setPaymentStatus(orderDao.findPaymentStatus(1));
        return orderDto;
    }

    @Override
    public Long create(Principal principal) {

        User user = (User) userService.loadUserByUsername(principal.getName());
        CartDTO cartDTO = cartService.getCartDtoByUserOrCreate(user);
        OrderDto orderDto = createOrderDtoFromCartDto(cartDTO);
        orderDto.setUserId(user.getId());
        Order order = convertDtoToOrder(orderDto);
        orderDao.create(order);
        return order.getId();
    }

    private Order convertDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        User user = userService.find(orderDto.getUserId());
        order.setUser(user);
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Entry<ProductDto, Integer> e:orderDto.getOrderProducts().entrySet()
        ) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(e.getKey().getId());
            orderProduct.setName(e.getKey().getName());
            orderProduct.setPrice(e.getKey().getPrice());
            orderProduct.setWeight(e.getKey().getWeight());
            orderProduct.setVolume(e.getKey().getVolume());
            orderProduct.setQuantity(e.getValue());
            orderProduct.setOrder(order);
            orderProducts.add(orderProduct);
        }
        order.setOrderProducts(orderProducts);
        order.setAddress(orderDto.getAddress());
        order.setDeliveryMethod(orderDto.getDeliveryMethod());
        order.setDeliveryStatus(orderDto.getDeliveryStatus());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        return order;
    }

    private OrderProduct convertProductDtoToOrderProduct(ProductDto dto){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(dto.getId());
        orderProduct.setPrice(dto.getPrice());
        orderProduct.setName(dto.getName());
        orderProduct.setVolume(dto.getVolume());
        orderProduct.setWeight(dto.getWeight());
        return orderProduct;
    }

    public List<PaymentMethod> getAllPaymentMethods() {
        return orderDao.findAllPaymentMethods();
    }

    public OrderDto updateDtoById(Long id, OrderDto updateOrderDto) {
        OrderDto orderDto = getDtoById(id);
        orderDto.setOrderProducts(updateOrderDto.getOrderProducts());
        orderDto.setAddress(updateOrderDto.getAddress());
        orderDto.setPaymentStatus(updateOrderDto.getPaymentStatus());
        orderDto.setPaymentMethod(updateOrderDto.getPaymentMethod());
        orderDto.setDeliveryStatus(updateOrderDto.getDeliveryStatus());
        orderDto.setDeliveryMethod(updateOrderDto.getDeliveryMethod());
        update(convertDtoToOrder(orderDto));
        return orderDto;
    }
}
