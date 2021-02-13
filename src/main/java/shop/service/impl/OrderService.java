package shop.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.impl.OrderDao;
import shop.domain.Cart;
import shop.domain.CustomerAddress;
import shop.domain.DeliveryMethod;
import shop.domain.DeliveryStatus;
import shop.domain.Order;
import shop.domain.OrderProduct;
import shop.domain.PaymentMethod;
import shop.domain.PaymentStatus;
import shop.domain.Product;
import shop.domain.User;
import shop.dto.CartDTO;
import shop.dto.CustomerAddressDto;
import shop.dto.OrderDto;
import shop.dto.ProductDto;
import shop.dto.StatisticDto;
import shop.dto.UserAccountDto;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService implements shop.service.OrderService {

    private final OrderDao orderDao;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @Override
    public Order findOne(long id) {
        return orderDao.findOne(id).orElse(null);
    }

    @Override
    public List<Order> findAll(int pageSize) {
        List<Order> orders =  orderDao.findAll(pageSize);
        orders.stream().forEach(order -> order.setOrderProducts(getOrderProductsByOrderId(order.getId())));
        return orders;
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
        double fullCost = 0.0;
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
            orderDto.setAddress(order.getAddress().toString());
            orderDto.setAddressId(order.getAddress().getId());
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
        if (orderDto.getUserId() != null){
            User user = userService.find(orderDto.getUserId());
            order.setUser(user);
        }
        List<OrderProduct> orderProducts = getOrderProducts(orderDto);

        if (orderDto.getAddressId() != null){
            order.setAddress(getAddressById(orderDto.getAddressId()));
        }
        order.setDeliveryMethod(getDeliveryMethodByName(orderDto.getDeliveryMethod()));
        if (orderDto.getDeliveryStatus() != null){
            order.setDeliveryStatus(getDeliveryStatusByName(orderDto.getDeliveryStatus()));
        }else {
            order.setDeliveryStatus(getDeliveryStatusByName("Order created"));
        }

        order.setPaymentMethod(getPaymentMethodByName(orderDto.getPaymentMethod()));
        if (orderDto.getPaymentStatus() != null){
            order.setPaymentStatus(getPaymentStatusByName(orderDto.getPaymentStatus()));
        }else {
            order.setPaymentStatus(getPaymentStatusByName("Unpaid"));
        }
        orderProducts.stream().forEach(orderProduct -> orderProduct.setOrder(order));
        order.setOrderProducts(orderProducts);
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

    public List<DeliveryMethod> getAllDeliveryMethods() {
        return orderDao.findAllDeliveryMethods();
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
            order.setAddress(getAddressById(updateOrderDto.getAddressId()));
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

    public OrderDto getNewOrderDto(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return getDtoByUser(user);
    }

    private OrderDto getDtoByUser(User user) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(user.getId());
        Map<ProductDto, Integer> products = getProductsDtoFromCartsByUser(user);
        orderDto.setFullCost(getFullCostOfProductDtoMap(products));
        orderDto.setOrderProducts(products);
        return orderDto;
    }

    private double getFullCostOfProductDtoMap(Map<ProductDto, Integer> products) {
        double sum = 0.0;
        for (Entry<ProductDto, Integer> p: products.entrySet()
        ) {
            sum += p.getKey().getPrice()*p.getValue();
        }
        return sum;
    }

    private Map<ProductDto, Integer> getProductsDtoFromCartsByUser(User user) {
        List<Cart> carts = orderDao.getCartsByUser(user);
        Map<ProductDto, Integer> products = new HashMap<>();
        for (Cart c: carts
        ) {
            ProductDto productDto = productService.convertProductToDto(c.getProduct());
            if (products.containsKey(productDto)){
                products.put(productDto, products.get(productDto) + c.getQuantity());
            }else {
                products.put(productDto, c.getQuantity());
            }
        }
        return products;
    }

    public OrderDto getDtoById(Long id, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        OrderDto orderDto = getDtoById(id);
        if (orderDto.getUserId().equals(user.getId()) || user.getRole().getName().equals("ROLE_MANAGER")) {
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

    public List<CustomerAddressDto> getAllCustomerAddresses(Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        return getUserAddressDtoList(user);
    }


    public void create(Principal principal, OrderDto orderDto, HttpServletRequest request) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        orderDto.setUserId(user.getId());
        Map<ProductDto, Integer> products = getProductsDtoFromCartsByUser(user);
        products = productService.checkAndUpdateOrderedProducts(products);
        orderDto.setFullCost(getFullCostOfProductDtoMap(products));
        orderDto.setOrderProducts(products);
        Order order = orderDao.create(convertDtoToOrder(orderDto));
        order.getOrderProducts().stream().forEach(o -> {
            orderDao.saveOrderProduct(o);
//            productService.updateAfterOrder(o);
        });
        request.getSession().setAttribute("cartDto", cartService.clearCart(user));
    }

    public List<OrderDto> findAllDtos(int pageSize) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order o: findAll(pageSize)
        ) {
            orderDtoList.add(convertOrderToDto(o));
        }
        return orderDtoList;
    }

    public OrderDto findOneDto(Long id) {
        return convertOrderToDto(findOne(id));
    }

    public List<PaymentStatus> getAllPaymentStatuses() {
        return orderDao.findAllPaymentStatuses();
    }

    public List<DeliveryStatus> getAllDeliveryStatuses() {
        return orderDao.findAllDeliveryStatuses();
    }

    public List<CustomerAddressDto> getAllCustomerAddressesByOrderId(Long id) {
        Order order = orderDao.findOne(id).orElse(null);
        User user = userService.find(order.getUser().getId());
        return getUserAddressDtoList(user);
    }

    public void update(Long id, OrderDto orderDto) {
        Order order = orderDao.findOne(id).get();
        if (orderDto.getAddressId() != null){
            order.setAddress(getAddressById(orderDto.getAddressId()));
        }
        if (orderDto.getDeliveryStatus() != null){
            order.setDeliveryStatus(getDeliveryStatusByName(orderDto.getDeliveryStatus()));
        }
        if (orderDto.getPaymentStatus() != null){
            order.setPaymentStatus(getPaymentStatusByName(orderDto.getPaymentStatus()));
        }
        update(order);
    }

    public StatisticDto getStatistic() {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setBestProducts(getBestProducts());
        statisticDto.setRevenue(getRevenue());
        statisticDto.setBestCustomers(getBestCustomers());
        return statisticDto;
    }

    private Map<UserAccountDto, Double> getBestCustomers() {
        List<User> users = userService.findAll(100);
        Map<UserAccountDto, Double> map = new LinkedHashMap<>();
        for (User u: users
        ) {
            Double sum = getRevenueForUser(u);
            if (sum != null && sum > 0.0) {
                map.put(userService.convertUserToUserAccountDto(u), sum);
            }
        }
        map.entrySet().stream()
                .sorted(Entry.comparingByValue()).limit(5)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
        return map;
    }

    private Double getRevenueForUser(User u) {
        double revenueForUser = 0.0;
        for (Order o: u.getOrders()
        ) {
            revenueForUser += orderDao.getRevenueForUserOrder(o);
        }
        return revenueForUser;
    }

    private Double getRevenue() {
        return orderDao.getRevenue();
    }

    public Map<ProductDto, Double> getBestProducts() {
////        List<Product> products = productService.findAll(100);
//        List<Product> products = new ArrayList<>();
//        for (Long id: orderDao.getBestProducts()
//        ) {
//            products.add(productService.findOne(id));
//        }
//
//        Map<ProductDto, Double> map = new LinkedHashMap<>();
//        for (Product p: products
//        ) {
//            Double sum = orderDao.getRevenueForProduct(p);
//            if (sum != null && sum > 0.0){
//                map.put(productService.convertProductToDto(p), sum);
//            }
//        }
////        map.entrySet().stream()
////                .filter(x -> x.getValue() > 0.0)
////                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
//        return map;
        Map<ProductDto, Double> map = new HashMap<>();
        for (Entry<Long, Double> e: orderDao.getBestProducts().entrySet()
        ) {

                map.put(productService.convertProductToDto(productService.findOne(e.getKey())), e.getValue());

        }
        return map;

    }
}
