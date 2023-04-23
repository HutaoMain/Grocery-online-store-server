package com.online.grocery.store.service;

import com.online.grocery.store.dto.ProductDto;
import com.online.grocery.store.model.Category;
import com.online.grocery.store.model.Favorite;
import com.online.grocery.store.model.Product;
import com.online.grocery.store.repository.FavoriteRepository;
import com.online.grocery.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        productRepository.save(product);
    }

    public List<Product> getTopSoldProducts() {
        Sort sort = Sort.by(Sort.Direction.DESC, "sold"); // Sort by number of sales in descending order
        Pageable pageable = PageRequest.of(0, 4, sort); // Retrieve the first 4 records
        return productRepository.findAll(pageable).getContent();
    }

    public List<Optional<Product>> getProductsByFavoriteFindByEmail(String email){
        List<Favorite> favorites = favoriteRepository.findByEmail(email);
        List<Optional<Product>> favoriteProducts = new ArrayList<>();
        for (Favorite favorite : favorites) {
            Optional<Product> product = productRepository.findById(favorite.getProductId());
            if (product.isPresent()) {
                favoriteProducts.add(product);
            }
        }
        return favoriteProducts;
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }
}
