package emazon.stock.configuration.exception;

public class InvalidPageIndexException extends RuntimeException {
    public InvalidPageIndexException(String message) {
        super(message);
    }
}