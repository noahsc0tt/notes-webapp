package uk.ac.ucl.exceptions;

public class MarkdownParseException extends RuntimeException {
    public MarkdownParseException(String message) {
        super(message);
    }
    
    public MarkdownParseException(String message, Throwable cause) {
        super(message, cause);
    }
}