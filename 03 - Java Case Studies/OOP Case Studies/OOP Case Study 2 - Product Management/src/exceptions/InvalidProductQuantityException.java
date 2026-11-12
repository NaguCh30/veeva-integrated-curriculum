package exceptions;

public class InvalidProductQuantityException extends Exception{
    public InvalidProductQuantityException(String message) {
        super(message);
    }
}
