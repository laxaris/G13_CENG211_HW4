package exceptions;

public class BoxCapacityException extends Exception {
    public BoxCapacityException(String message) {
        super(message);
    }

    public BoxCapacityException() {
        super("Exception: More than capacity");
    }
}