package com.online.grocery.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String email;

    private String name;

    private String imageUrl;

    private String userRole = "ROLE_USER";

    private String street;

    private String barangay;

    private String postalCode;

    private String municipality;

    private String city;

    private Integer contactNumber;

    List<Order> order;
}
