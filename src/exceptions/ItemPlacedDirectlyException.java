package exceptions;

public class ItemPlacedDirectlyException extends Exception {
    
    public ItemPlacedDirectlyException(String message) {
        super(message);
    }

    public ItemPlacedDirectlyException() {
        super("Exception: Item connot placed directly on the container.");
    }
}