package com.codekerki.order.controller;

import com.codekerki.order.model.OrderDto;
import com.codekerki.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired OrderService handler;

    @GetMapping("/all")
    public List<OrderDto> getOrders(@RequestHeader Long userId) {
        return handler.getOrders(userId);
    }

    @GetMapping()
    public OrderDto getOrder(@RequestParam(name = "id") Long id, @RequestHeader Long userId) {
        return handler.getOrder(id, userId);
    }

    @PostMapping("/add")
    public boolean addOrder(@Validated @RequestBody OrderDto orderDto, @RequestHeader Long userId) {
        return handler.addOrder(orderDto, userId);
    }

    @PatchMapping
    public OrderDto updateOrder(@Validated @RequestBody OrderDto orderDto, @RequestHeader Long userId) {
        return handler.updateOrder(orderDto, userId);
    }

    @DeleteMapping
    public boolean removeOrder(@RequestParam(name = "id") Long id, @RequestHeader Long userId) {
        return handler.removeOrder(id, userId);
    }

}
