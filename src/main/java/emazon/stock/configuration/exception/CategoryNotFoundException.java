package emazon.stock.configuration.exception;

import emazon.stock.configuration.ExceptionConstants;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super(ExceptionConstants.CATEGORY_NOT_FOUND);
    }
}
