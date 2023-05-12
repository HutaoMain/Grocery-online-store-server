package com.online.grocery.store.repository;

import com.online.grocery.store.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);
}
