package domain.exceptions;

public class ThoseShippedException extends Exception {
    
    public ThoseShippedException() {
        super("Those Shipped Exception");
    }
    
    public ThoseShippedException(String message) {
        super(message);
    }
}