package com.online.grocery.store.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategorySoldDto {
    private String categoryName;
    private Integer totalSold;
}
