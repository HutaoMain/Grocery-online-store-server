package com.online.grocery.store.service;

import com.online.grocery.store.model.Favorite;
import com.online.grocery.store.model.Product;
import com.online.grocery.store.repository.FavoriteRepository;
import com.online.grocery.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Favorite> getFavoriteByEmail(String email) {
        return favoriteRepository.findByEmail(email);
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public List<Product> getSpecificFavProductByEmail(String email) {
        List<Favorite> favorite = favoriteRepository.findByEmail(email);
        List<Product> productList = new ArrayList<>();

        for (Favorite w : favorite) {
            Optional<Product> product = productRepository.findById(w.getProductId());
            product.ifPresent(productList::add);
        }

        return productList;
    }

    @Transactional
    public Favorite deleteFavoriteList(String id) {
        favoriteRepository.deleteByProductId(id);
        return null;
    }
}
