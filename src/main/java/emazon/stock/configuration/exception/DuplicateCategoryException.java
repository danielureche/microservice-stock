package emazon.stock.configuration.exception;

import emazon.stock.configuration.ExceptionConstants;

public class DuplicateCategoryException extends RuntimeException{

    public DuplicateCategoryException(){
        super(ExceptionConstants.DUPLICATE_CATEGORY);
    }
}
