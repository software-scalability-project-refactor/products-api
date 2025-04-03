package com.api.products.application;


import lombok.Value;

import java.time.LocalDate;

@Value
public class ProductDTO {
    String category;
    String flavor;
    double price;
    double quantity;
    double weight;
    String imageURL;
    LocalDate expirationDate;
    boolean isReturnable;
    boolean isFixedPrice;
    String weightValue;
}
