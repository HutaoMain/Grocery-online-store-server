package com.online.grocery.store.repository;

import com.online.grocery.store.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByEmail(String email);

    List<Order> findByStatus(String status);
}
