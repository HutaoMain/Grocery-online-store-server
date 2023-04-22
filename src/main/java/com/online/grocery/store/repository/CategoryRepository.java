package com.online.grocery.store.repository;

import com.online.grocery.store.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
