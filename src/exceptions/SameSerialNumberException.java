package exceptions;

public class SameSerialNumberException extends Exception{
    public SameSerialNumberException(String message){
        super(message);
    }
    public SameSerialNumberException(){
        super("Serial number must be unique, therefore this serial number is invalid!");
    }

}
