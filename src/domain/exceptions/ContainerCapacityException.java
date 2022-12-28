package domain.exceptions;

public class ContainerCapacityException extends Exception {
    
    public ContainerCapacityException(String message) {
        super(message);
    }

    public ContainerCapacityException() {
        super("Exception: More than capacity");
    }
}