package domain.container;

import java.util.ArrayList;

import domain.exceptions.LoadedBoxException;
import domain.exceptions.MismatchItemTypeException;
import domain.exceptions.BoxCapacityException;
import domain.items.Item;
import domain.items.UncountableItem;



public class MassBox<T extends UncountableItem> extends ItemBox<Item> {
    private double mass;
    private double instantMass;


    public MassBox(String code, double mass, double volume, String serialNumber) {
    	super(code, volume, serialNumber, 3,  new ArrayList<Item>());
        this.mass = mass;
        this.instantMass = 0;
    }
    
    public MassBox(){
    	super();
        this.mass = 10;
        this.instantMass=0;
        
    }
    public MassBox(MassBox<T> box){
    	super(box);
        this.mass = box.mass;
    }


    
	@SuppressWarnings("unchecked")
	@Override
    public void add(Item item) throws MismatchItemTypeException, BoxCapacityException, LoadedBoxException{
        misMatchChecker(item);
        capacityChecker(item);
        getList().add( item);
        setInstantVolume(getInstantVolume() + item.getVolume());
        instantMass += ((T)item).getMass();
        System.out.println("Item with serial number "+item.getSerialNumber()+" added to massBox with serial number "+getSerialNumber());
    }

    
    @SuppressWarnings("unchecked")
	private void capacityChecker(Item item)throws BoxCapacityException{
        if (getInstantVolume() + item.getVolume() > getVolume()|| instantMass + ((T)item).getMass() > mass){
            throw new BoxCapacityException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox with serial number "+getSerialNumber()+" because it is full");
        }
    }

   
    private void misMatchChecker(Item item)throws MismatchItemTypeException{
         if (item.getCountability() == "Countable" ){
            throw new MismatchItemTypeException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox");
        }
    }
    
    

   



    public double getMass() {
        return mass;
    }

    public MassBox<T> clone(){
        return new MassBox<>(this);
    }
    
   

    public String toString(){
        return "Code: " + getCode() + " Mass: " + mass + " Volume: " + getVolume() + " Serial Number: " + getSerialNumber()+" Cost: " + getCost();
    }
  
}
