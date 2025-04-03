package com.api.products.application.service;

import com.api.products.application.ProductDTO;
import com.api.products.domain.Product;
import com.api.products.exceptions.BusinessException;
import com.api.products.exceptions.NotFoundException;
import com.api.products.infrastructure.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(ProductDTO product) {
        if (product == null) {
            throw new BusinessException("Product data must not be null.");
        }

        Product createdProduct = Product.builder()
                .category(product.getCategory())
                .flavor(product.getFlavor())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .weight(product.getWeight())
                .imageURL(product.getImageURL())
                .expirationDate(product.getExpirationDate())
                .isReturnable(product.isReturnable())
                .isFixedPrice(product.isFixedPrice())
                .weightValue(product.getWeightValue())
                .build();

        validateProduct(createdProduct);

        return productRepository.save(createdProduct);
    }

    @Override
    public Product get(UUID id) {
        if (id == null) {
            throw new BusinessException("Product ID must not be null.");
        }

        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found."));
    }

    @Override
    public Product update(UUID productId, ProductDTO product) {
        if (productId == null) {
            throw new BusinessException("Product ID must not be null.");
        }
        if (product == null) {
            throw new BusinessException("Product data must not be null.");
        }

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found."));

        existingProduct.setCategory(product.getCategory());
        existingProduct.setFlavor(product.getFlavor());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setWeight(product.getWeight());
        existingProduct.setImageURL(product.getImageURL());
        existingProduct.setExpirationDate(product.getExpirationDate());
        existingProduct.setReturnable(product.isReturnable());
        existingProduct.setFixedPrice(product.isFixedPrice());
        existingProduct.setWeightValue(product.getWeightValue());

        validateProduct(existingProduct);

        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            throw new BusinessException("Product ID must not be null.");
        }
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with id " + id + " not found.");
        }
        productRepository.deleteById(id);
    }

    private void validateProduct(Product product) {
        if(product.getCategory() == null){
            throw new BusinessException("Unable to create the product. Empty category.");
        }

        double MAX_VALUE = 100000;
        if(product.getPrice() <= 0 || product.getPrice() >= MAX_VALUE){
            throw new BusinessException("Unable to create the product. Price out of range.");
        }

        if(product.getQuantity() <= 0 || product.getQuantity() >= MAX_VALUE){
            throw new BusinessException("Unable to create the product. Quantity out of range.");
        }

        if(product.getWeight() <= 0 || product.getWeight() >= MAX_VALUE){
            throw new BusinessException("Unable to create the product. Weight out of range.");
        }
    }
}
