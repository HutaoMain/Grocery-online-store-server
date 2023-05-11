package com.online.grocery.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "order")
public class Order {

    @Id
    private String id;

    private String email;

    private String userFullName;

    private Double totalPrice;

    private String orderList;

    private String status;

//

    private String street;

    private String barangay;

    private String postalCode;

    private String municipality;

    private String city;

    private Integer contactNumber;

//

    private String paymentMethod;

    private String receipt;
}
