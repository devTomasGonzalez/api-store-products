package com.falabella.products.api.exceptions;

public class SkuExistException extends RuntimeException {

    public SkuExistException(String message) {
        super(message);
    }

}
