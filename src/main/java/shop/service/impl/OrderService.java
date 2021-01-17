package shop.service.impl;

import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.OrderDao;
import shop.domain.Order;
import shop.domain.OrderProduct;
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
    public Order update(Order p) {
        return null;
    }

    @Override
    public OrderDto getDtoById(Long id) {
        return null;
    }

    @Override
    public OrderDto createOrderFromCartDto(CartDTO cartDTO) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(cartDTO.getUserId());
        orderDto.setOrderProducts(cartDTO.getProducts());
        return orderDto;
    }

    @Override
    public Long create(Principal principal, CartDTO cartDTO) {
        OrderDto orderDto = createOrderFromCartDto(cartDTO);
        User user = (User) userService.loadUserByUsername(principal.getName());
        Order order = convertDtoToOrder(orderDto);

        return null;
    }

    private Order convertDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        return null;
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
}
