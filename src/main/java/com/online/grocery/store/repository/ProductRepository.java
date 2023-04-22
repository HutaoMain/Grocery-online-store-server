package com.online.grocery.store.repository;

import com.online.grocery.store.model.Category;
import com.online.grocery.store.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategory(Category category);
}
