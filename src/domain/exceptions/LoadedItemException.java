package domain.exceptions;

public class LoadedItemException extends Exception {
    
    public LoadedItemException(String message) {
        super(message);
    }

    public LoadedItemException() {
        super("Item is already loaded");
    }
}