package emazon.stock.configuration.exception;

import emazon.stock.configuration.ExceptionConstants;

public class BrandAlreadyExistsException extends RuntimeException{
    public BrandAlreadyExistsException() {
        super(ExceptionConstants.BRAND_ALREADY_EXISTS);
    }
}
