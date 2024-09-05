package emazon.stock.configuration.exception;

import emazon.stock.ports.application.utils.BrandValidationConstants;

public class BrandAlreadyExistsException extends RuntimeException{
    public BrandAlreadyExistsException() {
        super(BrandValidationConstants.BRAND_ALREADY_EXISTS);
    }
}
