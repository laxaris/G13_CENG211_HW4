package exceptions;

public class MoreThanCapacityException extends Exception {
    
    public MoreThanCapacityException(String message) {
        super(message);
    }

    public MoreThanCapacityException() {
        super("Exception: More than capacity");
    }
}