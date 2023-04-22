package com.online.grocery.store.repository;

import com.online.grocery.store.model.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {

    List<Favorite> findByEmail(String email);

    void deleteByProductId(String productId);
}
