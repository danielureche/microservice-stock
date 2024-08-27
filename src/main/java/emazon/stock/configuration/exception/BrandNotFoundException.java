package emazon.stock.configuration.exception;

import emazon.stock.configuration.ExceptionConstants;

public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException(){
        super(ExceptionConstants.BRAND_NOT_FOUND);
    }
}
