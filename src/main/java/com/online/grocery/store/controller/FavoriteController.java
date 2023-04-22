package com.online.grocery.store.controller;

import com.online.grocery.store.model.Favorite;
import com.online.grocery.store.model.Product;
import com.online.grocery.store.service.FavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/{email}")
    ResponseEntity<List<Favorite>> getFavoriteByEmail(@PathVariable String email) {
        List<Favorite> favoriteList = favoriteService.getFavoriteByEmail(email);
        return ResponseEntity.ok(favoriteList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFavorite(@RequestBody Favorite favorite) {
        Favorite createFavorite = favoriteService.addFavorite(favorite);
        if (createFavorite != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createFavorite);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error creating wishlist");
        }
    }

    @GetMapping("/product/{email}")
    public ResponseEntity<List<Product>> getSpecificFavProductByEmail(@PathVariable String email) {
        List<Product> favoriteServiceSpecificFavProductByEmail = favoriteService.getSpecificFavProductByEmail(email);
        return ResponseEntity.ok(favoriteServiceSpecificFavProductByEmail);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Favorite> deleteWishlist(@PathVariable String id) {
        Favorite deleteFavoriteList = favoriteService.deleteFavoriteList(id);
        return ResponseEntity.ok(deleteFavoriteList);
    }
}
