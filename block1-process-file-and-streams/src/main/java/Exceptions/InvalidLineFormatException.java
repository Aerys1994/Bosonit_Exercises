package Exceptions;

public class InvalidLineFormatException extends Exception{

    private final String line;

    public static final String MISSING_DELIMITER = "Missing the proper delimiter";

    public static final String MISSING_NAME = "Name is mandatory";

    public InvalidLineFormatException(String line, String errorType) {
        super("Invalid format line: " + line + ". Cause: " + errorType);
        this.line = line;
    }

    public String getLine() { return line; }
}
