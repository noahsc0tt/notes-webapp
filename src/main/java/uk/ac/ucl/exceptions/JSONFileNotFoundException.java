package uk.ac.ucl.exceptions;

public class JSONFileNotFoundException extends Exception
{
    public JSONFileNotFoundException(String message) { super(message); }
    public JSONFileNotFoundException(String message, Throwable cause) { super(message, cause); }
}