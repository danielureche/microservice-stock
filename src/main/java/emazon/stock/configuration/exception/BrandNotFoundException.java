package emazon.stock.configuration.exception;

import emazon.stock.ports.application.utils.ArticleValidationConstants;

public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException(){
        super(ArticleValidationConstants.BRAND_NOT_FOUND);
    }
}
