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

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }


    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setStatus(orderDto.getStatus());
        order.setEmail(orderDto.getEmail());
        order.setUserFullName(orderDto.getUserFullName());
        order.setOrderList(orderDto.getOrderList());
        order.setBarangay(orderDto.getBarangay());
        order.setMunicipality(orderDto.getMunicipality());
        order.setStreet(orderDto.getStreet());
        order.setContactNumber(orderDto.getContactNumber());
        order.setCity(orderDto.getCity());
        order.setPostalCode(orderDto.getPostalCode());

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
}
