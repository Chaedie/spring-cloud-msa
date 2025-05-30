package com.example.orderservice.service;


import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(String orderId);

    Iterable<OrderEntity> getAllOrdersByUserId(String userId);
}
