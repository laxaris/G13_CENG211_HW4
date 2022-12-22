package exceptions;

public class SameSerialNumberException extends Exception{
    public SameSerialNumberException(String message){
        super(message);
    }
    public SameSerialNumberException(){
        super("Item cannot be produced (EX: 1 existing serial number)");
    }

}
