package com.online.grocery.store.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String id;

    private String productName;

    private String imageUrl;

    private String description;

    private Double price;

    private Integer quantity;

    private String categoryId;

    private Integer sold;
}
