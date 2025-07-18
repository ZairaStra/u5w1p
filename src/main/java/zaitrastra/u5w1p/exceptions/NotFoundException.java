package zaitrastra.u5w1p.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super("Element not found - " + message);
    }
}
