package com.api.products.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", unique = true, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String category;
    private String flavor;
    @Column(nullable = false)
    @Min(0)
    private double price;
    @Column(nullable = false)
    @Min(0)
    private double quantity;
    @Column(nullable = false)
    @Min(0)
    private double weight;
    private String imageURL;
    private LocalDate expirationDate;
    private boolean isReturnable;
    private boolean isFixedPrice;
    private String weightValue;
}
