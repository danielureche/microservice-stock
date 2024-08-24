package emazon.stock.configuration.exception;

import emazon.stock.configuration.ExceptionConstants;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {
        super(ExceptionConstants.CATEGORY_ALREADY_EXISTS);
    }
}
