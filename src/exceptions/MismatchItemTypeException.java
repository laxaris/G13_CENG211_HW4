package exceptions;

public class MismatchItemTypeException extends Exception{

     public MismatchItemTypeException(String message){
        super(message);
    }

    public MismatchItemTypeException(){
        super("EXCEPTION: Item type mismatch.");
    }
}