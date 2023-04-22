package com.online.grocery.store.service;

import com.online.grocery.store.dto.ProductDto;
import com.online.grocery.store.model.Category;
import com.online.grocery.store.model.Product;
import com.online.grocery.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

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
}
