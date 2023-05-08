package com.online.grocery.store.controller;

import com.online.grocery.store.dto.ProductDto;
import com.online.grocery.store.model.Category;
import com.online.grocery.store.model.Product;
import com.online.grocery.store.repository.CategoryRepository;
import com.online.grocery.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDTO) {
        Optional<Category> optionalCategories = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategories.isPresent()) {
            return ResponseEntity.ok("category does not exist");
        } else {
            productService.createProduct(productDTO, optionalCategories.get());
            return ResponseEntity.ok("success created product");
        }
    }

    @GetMapping("/withCategory/{category}")
    List<Product> getProductWithSpecificCategory(@PathVariable Category category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/list")
    List<Product> getAllProducts(@RequestParam(name = "category", required = false) Category category) {
        if (category != null) {
            return productService.getProductsByCategory(category);
        } else {
            return productService.getAllProducts();
        }
    }

    @GetMapping("/bestProducts")
    List<Product> getBest4Products() {
        return productService.getTopSoldProducts();
    }

    @GetMapping("/favoriteByEmail/{email}")
    ResponseEntity<List<Optional<Product>>> getProductsByFavoriteFindByEmail(@PathVariable String email) {
        List<Optional<Product>> productListFavoriteFindByEmail = productService.getProductsByFavoriteFindByEmail(email);
        return ResponseEntity.ok(productListFavoriteFindByEmail);
    }

    @GetMapping("/specificProduct/{id}")
    ResponseEntity<Optional<Product>> getProductById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        return ResponseEntity.ok(optionalProduct);
    }

    @DeleteMapping("/delete/{productId}")
    private String deleteProductById(@PathVariable("productId") String productId) {
        productService.deleteProductById(productId);
        return "product deleted";
    }
}
