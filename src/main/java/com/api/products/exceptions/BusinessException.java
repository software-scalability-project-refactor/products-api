package com.api.products.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
