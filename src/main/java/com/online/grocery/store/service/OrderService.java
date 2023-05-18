package com.online.grocery.store.service;

import com.online.grocery.store.dto.OrderDto;
import com.online.grocery.store.dto.ProductQuantityDto;
import com.online.grocery.store.model.Order;
import com.online.grocery.store.model.Product;
import com.online.grocery.store.repository.OrderRepository;
import com.online.grocery.store.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }


    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setStatus(orderDto.getStatus());
        order.setEmail(orderDto.getEmail());
        order.setUserFullName(orderDto.getUserFullName());
        order.setOrderList(orderDto.getOrderList());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setOrderDate(orderDto.getOrderDate());

        List<ProductQuantityDto> productQuantities = orderDto.getProducts();
        subtractProductsFromInventory(productQuantities);
        updateProductSold(productQuantities);

        orderRepository.save(order);
    }

    private void subtractProductsFromInventory(List<ProductQuantityDto> productQuantities) {
        for (ProductQuantityDto pq : productQuantities) {
            Optional<Product> productOptional = productRepository.findById(pq.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setQuantity(product.getQuantity() - pq.getQuantity());
                productRepository.save(product);
            } else {
                log.error("error in subtractProductsFromInventory");
            }
        }
    }

    public void updateProductSold(List<ProductQuantityDto> productQuantities) {
        for (ProductQuantityDto pq : productQuantities) {
            Optional<Product> productOptional = productRepository.findById(pq.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setSold(product.getSold() + pq.getQuantity());
                productRepository.save(product);
            } else {
                log.error("error in updateProductSold");
            }
        }
    }

    public List<Order> getOrderByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    public void uploadReceipt(String orderId, Order getOrder) {
        Order setOrder = orderRepository.findById(orderId).orElse(null);
        if (setOrder != null) {
            setOrder.setReceipt(getOrder.getReceipt());
            orderRepository.save(setOrder);
        }
    }

    public void updateOrderStatus(String id, Order order) {
        Order setOrder = orderRepository.findById(id).orElse(null);
        if (setOrder != null) {
            setOrder.setStatus(order.getStatus());
            orderRepository.save(setOrder);
        }
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public double getTotalPriceByStatus() {
        String status = "Delivered";
        List<Order> orders = orderRepository.findByStatus(status);
        return orders.stream().mapToDouble(Order::getTotalPrice).sum();
    }

    public List<Map<String, Object>> getTotalSalesPerMonth() {
        List<Order> deliveredOrders = orderRepository.findByStatus("Delivered");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM");
        return deliveredOrders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().format(formatter),
                        Collectors.summingDouble(Order::getTotalPrice)
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("orderDate", entry.getKey());
                    result.put("totalPrice", entry.getValue());
                    return result;
                })
                .collect(Collectors.toList());
    }
}
