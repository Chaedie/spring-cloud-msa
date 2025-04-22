package com.example.userservice.service;

import com.example.userservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "order-service")
@Service
public interface OrderService {
    @GetMapping("/order-service/{userId}/orders")
    ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId);
}

