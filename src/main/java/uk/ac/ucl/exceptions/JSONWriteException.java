package uk.ac.ucl.exceptions;

public class JSONWriteException extends Exception
{
    public JSONWriteException(String message) {
        super(message);
    }
    public JSONWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}