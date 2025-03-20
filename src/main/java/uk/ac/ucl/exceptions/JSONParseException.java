package uk.ac.ucl.exceptions;

public class JSONParseException extends Exception
{
    public JSONParseException(String message) { super(message); }
    public JSONParseException(String message, Throwable cause) { super(message, cause); }
}