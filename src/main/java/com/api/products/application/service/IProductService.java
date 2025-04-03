package com.api.products.application.service;

import com.api.products.application.ProductDTO;
import com.api.products.domain.Product;

import java.util.UUID;

public interface IProductService {
    Product create(ProductDTO product);
    Product get(UUID id);
    Product update(UUID productId, ProductDTO product);
    void delete(UUID id);
}
