package exceptions;

public class LoadedBoxException extends Exception {
    
    public LoadedBoxException() {
        super("The box is loaded");
    }

    public LoadedBoxException(String message) {
        super(message);
    }
}