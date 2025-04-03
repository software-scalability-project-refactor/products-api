package com.api.products.application.controller;


import com.api.products.application.ProductDTO;
import com.api.products.application.service.ProductService;
import com.api.products.domain.Product;
import com.api.products.exceptions.BusinessException;
import com.api.products.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.create(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID productId) {
        try {
            Product product = productService.get(productId);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductDTO productDTO) {
        try {
            productService.update(productId, productDTO);
            return ResponseEntity.ok().body(productDTO);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID productId) {
        try {
            productService.delete(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}