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
import shop.domain.CustomerAddress;
import shop.domain.DeliveryMethod;
import shop.domain.DeliveryStatus;
import shop.domain.Order;
import shop.domain.OrderProduct;
import shop.domain.PaymentMethod;
import shop.domain.PaymentStatus;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.CustomerAddressDto;
import shop.dto.OrderDto;
import shop.dto.ProductDto;
import shop.dto.UserEditAccountDto;

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
        List<OrderProduct> orderProducts = getOrderProductsByOrderId(id);
        order.setOrderProducts(orderProducts);
        return convertOrderToDto(order);
    }

    private List<OrderProduct> getOrderProductsByOrderId(Long id) {
        return orderDao.getOrderProductsByOrderId(id);
    }

    private DeliveryMethod getDeliveryMethodByName(String name){
        return orderDao.getDeliveryMethodByName(name);
    }

    private DeliveryStatus getDeliveryStatusByName(String name){
        return orderDao.getDeliveryStatusByName(name);
    }

    private PaymentMethod getPaymentMethodByName(String name){
        return orderDao.getPaymentMethodByName(name);
    }

    private PaymentStatus getPaymentStatusByName(String name){
        return orderDao.getPaymentStatusByName(name);
    }

    private OrderDto convertOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        Map<ProductDto, Integer> products = new HashMap<>();
        int numberOfProducts = 0;
        double fullCost = 0;
        for (OrderProduct orderProduct: order.getOrderProducts()
        ) {
            ProductDto productDto = new ProductDto();
            productDto.setId(orderProduct.getId());
            productDto.setName(orderProduct.getName());
            productDto.setPrice(orderProduct.getPrice());
            productDto.setWeight(orderProduct.getWeight());
            productDto.setVolume(orderProduct.getVolume());
            products.put(productDto, orderProduct.getQuantity());
            numberOfProducts += orderProduct.getQuantity();
            fullCost += orderProduct.getPrice() * orderProduct.getQuantity();
        }
        orderDto.setNumberOfProducts(numberOfProducts);
        orderDto.setFullCost(fullCost);
        orderDto.setOrderProducts(products);
        if (order.getAddress() != null){
            orderDto.setAddress(convertAddressToDto(order.getAddress()));
        }
        orderDto.setDeliveryMethod(order.getDeliveryMethod().getName());
        orderDto.setDeliveryStatus(order.getDeliveryStatus().getName());
        orderDto.setPaymentMethod(order.getPaymentMethod().getName());
        orderDto.setPaymentStatus(order.getPaymentStatus().getName());
        return orderDto;
    }


    public List<CustomerAddressDto> getUserAddressDtoListForPrincipal(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());

        return getUserAddressDtoList(user);
    }

    private List<CustomerAddressDto> getUserAddressDtoList(User user) {
        List<CustomerAddress> addresses = orderDao.getUserAddresses(user);
        List<CustomerAddressDto> addressDtoList = new ArrayList<>();
        for (CustomerAddress a: addresses
        ) {
            addressDtoList.add(convertAddressToDto(a));
        }
        return addressDtoList;
    }

    private CustomerAddressDto convertAddressToDto(CustomerAddress address) {
        CustomerAddressDto dto = new CustomerAddressDto();
        dto.setId(address.getId());
        dto.setApartment(address.getApartment());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setStreet(address.getStreet());
        dto.setHouse(address.getHouse());
        dto.setPostcode(address.getPostcode());
        dto.setUserId(address.getUser().getId());
        return dto;
    }

    @Override
    public OrderDto createOrderDtoFromCartDto(CartDTO cartDTO) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(cartDTO.getUserId());
        orderDto.setOrderProducts(cartDTO.getProducts());
        orderDto.setDeliveryMethod(orderDao.findDeliveryMethod(1).getName());
        orderDto.setDeliveryStatus(orderDao.findDeliveryStatus(1).getName());
        orderDto.setPaymentMethod(orderDao.findPaymentMethod(1).getName());
        orderDto.setPaymentStatus(orderDao.findPaymentStatus(1).getName());
        return orderDto;
    }

    @Override
    public Long create(Principal principal) {

        User user = (User) userService.loadUserByUsername(principal.getName());
        CartDTO cartDTO = cartService.getCartDtoByUserOrCreate(user);
        OrderDto orderDto = createOrderDtoFromCartDto(cartDTO);
        orderDto.setUserId(user.getId());
        Order order = convertDtoToOrder(orderDto);
        order = orderDao.create(order);
        for (OrderProduct o: order.getOrderProducts()
        ) {
            o.setOrder(order);
            orderDao.saveOrderProduct(o);
        }

        return order.getId();
    }

    private Order convertDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        User user = userService.find(orderDto.getUserId());
        order.setUser(user);
        List<OrderProduct> orderProducts = getOrderProducts(orderDto);
        order.setOrderProducts(orderProducts);
        if (orderDto.getAddress() != null){
            order.setAddress(convertDtoToAddress(orderDto.getAddress()));
        }
        order.setDeliveryMethod(getDeliveryMethodByName(orderDto.getDeliveryMethod()));
        order.setDeliveryStatus(getDeliveryStatusByName(orderDto.getDeliveryStatus()));
        order.setPaymentMethod(getPaymentMethodByName(orderDto.getPaymentMethod()));
        order.setPaymentStatus(getPaymentStatusByName(orderDto.getPaymentStatus()));
        return order;
    }

    private CustomerAddress convertDtoToAddress(CustomerAddressDto addressDto) {
        CustomerAddress address = new CustomerAddress();
        address.setId(addressDto.getId());
        address.setApartment(addressDto.getApartment());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        address.setHouse(addressDto.getHouse());
        address.setPostcode(addressDto.getPostcode());
        if (addressDto.getUserId() != null){
            address.setUser(userService.find(addressDto.getUserId()));
        }

        return address;
    }

    private CustomerAddress getAddressById(Long id) {
        return orderDao.getAddressById(id);
    }


    private List<OrderProduct> getOrderProducts(OrderDto orderDto) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Entry<ProductDto, Integer> e: orderDto.getOrderProducts().entrySet()
        ) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(e.getKey().getId());
            orderProduct.setName(e.getKey().getName());
            orderProduct.setPrice(e.getKey().getPrice());
            orderProduct.setWeight(e.getKey().getWeight());
            orderProduct.setVolume(e.getKey().getVolume());
            orderProduct.setQuantity(e.getValue());
            orderProducts.add(orderProduct);
        }
        return orderProducts;
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

    public OrderDto updateMethodsById(Long id, OrderDto updateOrderDto) {
        OrderDto orderDto = getDtoById(id);
        orderDto.setPaymentMethod(updateOrderDto.getPaymentMethod());
        orderDto.setDeliveryMethod(updateOrderDto.getDeliveryMethod());
        updateExistOrderByDto(id, updateOrderDto);
        return orderDto;
    }

    private void updateExistOrderByDto(Long id, OrderDto updateOrderDto) {
        Order order = findOne(id);
        if (updateOrderDto.getOrderProducts() != null && !updateOrderDto.getOrderProducts().isEmpty()){
            order.setOrderProducts(getOrderProducts(updateOrderDto));
        }
        if (updateOrderDto.getAddress() != null){
            order.setAddress(convertDtoToAddress(updateOrderDto.getAddress()));
        }
        if (updateOrderDto.getDeliveryMethod() != null){
            order.setDeliveryMethod(getDeliveryMethodByName(updateOrderDto.getDeliveryMethod()));
        }

        if (updateOrderDto.getDeliveryStatus() != null){
            order.setDeliveryStatus(getDeliveryStatusByName(updateOrderDto.getDeliveryStatus()));
        }
        if (updateOrderDto.getPaymentMethod() != null){
            order.setPaymentMethod(getPaymentMethodByName(updateOrderDto.getPaymentMethod()));
        }
        if (updateOrderDto.getPaymentStatus() != null){
            order.setPaymentStatus(getPaymentStatusByName(updateOrderDto.getPaymentStatus()));
        }
        update(order);
    }

    public List<OrderDto> getAllUserOrders(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        List<Order> orders = orderDao.getOrdersForUser(user);
        for (Order o: orders
        ) {

            o.setOrderProducts(orderDao.getOrderProductsByOrderId(o.getId()));
        }
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order o: orders
        ) {
            orderDtoList.add(convertOrderToDto(o));
        }
        return orderDtoList;
    }

    public OrderDto getDtoById(Long id, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        OrderDto orderDto = getDtoById(id);
        if (orderDto.getUserId().equals(user.getId())) {
            return orderDto;
        }else {
            return null;
        }
    }

    public CustomerAddressDto getUserAddressDtoForPrincipal(Long id, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        CustomerAddress address = getAddressById(id);
        if (address.getUser().equals(user)) {
            return convertAddressToDto(address);
        }else {
            return null;
        }
    }

    public void updateUserAddressDtoForPrincipal(Long id, Principal principal, CustomerAddressDto addressDto) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        CustomerAddress address = getAddressById(id);
        if (address.getUser().equals(user)) {
            updateAddressViaDto(address, addressDto);
        }
    }

    private void updateAddressViaDto(CustomerAddress address, CustomerAddressDto addressDto) {
        address.setApartment(addressDto.getApartment());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());
        address.setStreet(addressDto.getStreet());
        address.setHouse(addressDto.getHouse());
        address.setPostcode(addressDto.getPostcode());
        orderDao.updateAddress(address);
    }

    public void crateAddressForPrincipal(Principal principal, CustomerAddressDto addressDto) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        CustomerAddress customerAddress = convertDtoToAddress(addressDto);
        customerAddress.setUser(user);
        orderDao.createAddress(customerAddress);
    }
}
