package com.online.grocery.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "product")
public class Product {

    @Id
    private String id;

    private String productName;

    private String imageUrl;

    private String description;

    private Double price;

    private Integer quantity;

    private Category category;

    private Integer sold = 0;
}
