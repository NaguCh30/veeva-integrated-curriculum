package exceptions;

public class InvalidCompanyNameException extends Exception {
    public InvalidCompanyNameException(String message) {
        super(message);
    }
}
