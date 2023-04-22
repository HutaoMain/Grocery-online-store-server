package com.online.grocery.store.repository;

import com.online.grocery.store.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
