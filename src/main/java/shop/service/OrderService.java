package shop.service;

import java.security.Principal;
import shop.domain.Order;
import shop.dto.CartDTO;
import shop.dto.OrderDto;

public interface OrderService extends Service<Order>{

    OrderDto getDtoById(Long id);

    OrderDto createOrderDtoFromCartDto(CartDTO cartDTO);

    Long create(Principal principal);
}
