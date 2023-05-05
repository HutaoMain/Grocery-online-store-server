package com.online.grocery.store.controller;

import com.online.grocery.store.dto.OrderDto;
import com.online.grocery.store.model.Order;
import com.online.grocery.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.ok("successfully ordered!");
    }

    @GetMapping("/userEmail/{email}")
    ResponseEntity<List<Order>> getOrderByEmail(@PathVariable String email) {
        List<Order> orderList = orderService.getOrderByEmail(email);
        return ResponseEntity.ok(orderList);
    }

    @PutMapping(value = "/uploadReceipt/{id}")
    public ResponseEntity<String> uploadReceipt(@PathVariable String id, @RequestBody Order order) {
        orderService.uploadReceipt(id, order);
        return ResponseEntity.ok("successfully upload receipt!");
    }

}
