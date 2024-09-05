package emazon.stock.configuration.exception;

import emazon.stock.ports.application.utils.CategoryValidationConstants;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {
        super(CategoryValidationConstants.CATEGORY_ALREADY_EXISTS);
    }
}
