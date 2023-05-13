package com.online.grocery.store.repository;

import com.online.grocery.store.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByEmail(String email);

    List<Order> findByStatus(String status);

    @Query("SELECT MONTH(o.orderDate) AS month, SUM(o.totalPrice) AS totalPrice FROM Order o WHERE o.status IN :statuses GROUP BY MONTH(o.orderDate)")
    List<Object[]> findTotalPriceGroupedByMonth(@Param("statuses") List<String> statuses);

}
