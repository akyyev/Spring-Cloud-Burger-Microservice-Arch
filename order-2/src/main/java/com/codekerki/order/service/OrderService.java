package com.codekerki.order.service;

import com.codekerki.order.model.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Value("${spring.datasource.url}")
    private String url;

    @Autowired UserService userService;

    private final List<OrderDto> orders = new ArrayList<>();

    public OrderService() {
        orders.add(new OrderDto(123L, Arrays.asList("Burger", "Meal"), "Collected"));
        orders.add(new OrderDto(456L, Arrays.asList("Banana", "Fruit"), "Collected"));
        orders.add(new OrderDto(111L, Arrays.asList("Juice", "Drink"), "Pick-up"));
    }

    public List<OrderDto> getOrders(Long userId) {
        log.info("Database URL: {}", url);
        userService.isUserValid(userId);
        return orders;
    }

    public OrderDto getOrder(Long id, Long userId) {
        userService.isUserValid(userId);
        var order =  orders.stream().filter(o -> o.getId().equals(id)).findAny();
        return order.orElse(null);
    }

    public boolean addOrder(OrderDto orderDto, Long userId) {
        userService.isUserValid(userId);
        orderDto.setId(orders.size() + 100L);
        return orders.add(orderDto);
    }

    public OrderDto updateOrder(OrderDto orderDto, Long userId) {
        userService.isUserValid(userId);
        var order = orders.stream().filter(o -> o.getId().equals(orderDto.getId())).findAny();
        if(order.isPresent()) {
            order.get().setItems(orderDto.getItems());
            order.get().setStatus(orderDto.getStatus());
            return order.get();
        }
        return null;
    }

    public boolean removeOrder(Long id, Long userId) {
        userService.isUserValid(userId);
        var order = orders.stream().filter(o -> o.getId().equals(id)).findAny();
        order.ifPresent(orders::remove);
        return order.isPresent();
    }

}
