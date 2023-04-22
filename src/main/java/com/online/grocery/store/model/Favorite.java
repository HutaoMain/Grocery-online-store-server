package com.online.grocery.store.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "favorite")
public class Favorite {

    @Id
    private String id;

    private String productId;

    private String email;

}
