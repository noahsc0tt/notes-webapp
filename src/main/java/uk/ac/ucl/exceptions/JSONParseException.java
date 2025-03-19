package uk.ac.ucl.exceptions;

public class JSONParseException extends RuntimeException {
    public JSONParseException(String message) {
        super(message);
    }
    
    public JSONParseException(String message, Throwable cause) {
        super(message, cause);
    }
}