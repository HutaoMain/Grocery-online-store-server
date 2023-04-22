package com.online.grocery.store.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class OrderDto {

    @Id
    private String id;

    private String email;

    private String userFullName;

    private Double totalPrice;

    private String orderJsonList;

    private String status;

    private String street;

    private String barangay;

    private String postalCode;

    private String municipality;

    private String city;

    private Integer contactNumber;

    private List<ProductQuantityDto> products;
}