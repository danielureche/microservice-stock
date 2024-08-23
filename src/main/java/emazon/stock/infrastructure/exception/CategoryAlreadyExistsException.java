package emazon.stock.infrastructure.exception;

import emazon.stock.infrastructure.ExceptionConstants;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {
        super(ExceptionConstants.CATEGORY_ALREADY_EXISTS);
    }
}
