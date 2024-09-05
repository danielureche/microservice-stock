package emazon.stock.configuration.exception;

import emazon.stock.ports.application.utils.ArticleValidationConstants;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super(ArticleValidationConstants.CATEGORY_NOT_FOUND);
    }
}
