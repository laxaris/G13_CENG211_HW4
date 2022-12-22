package container;

import java.util.ArrayList;

import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import exceptions.MoreThanCapacityException;
import items.InterfaceItem;
import items.UncountableItem;


public class MassBox implements InterfaceItemBox {

    private ArrayList<UncountableItem> list;
    private String code;
    private int mass;
    private int volume;
    private String serialNumber;
    private final int costPerUnit = 3;
    private boolean loaded;

    public MassBox(String code, int mass, int volume, String serialNumber) {
        this.code = code;
        this.mass = mass;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.list = new ArrayList<UncountableItem>();
        this.loaded = false;
    }
    public MassBox(){
        this.code = "";
        this.mass = 0;
        this.volume = 0;
        this.serialNumber = "";
        this.loaded = false;
    }
    public MassBox(MassBox box){
        this.code = box.code;
        this.mass = box.mass;
        this.volume = box.volume;
        this.serialNumber = box.serialNumber;
        this.loaded = box.loaded;
    }


    @Override
    public void add(InterfaceItem item) throws MismatchItemTypeException,MoreThanCapacityException, LoadedBoxException{
        misMatchChecker(item);
        capacityChecker((UncountableItem)item);
        addItemToLoadedBoxCheck();
        list.add((UncountableItem) item);
        System.out.println(serialNumber+" added item "+item.getSerialNumber());
    }

    private int totalVolume(){
        int volume = 0;
        for(InterfaceItem item : list){
            volume += item.getVolume();
        }
        return volume;
    }

     private double totalMass(){
        double mass= 0;
        for(UncountableItem item : list){
            mass += item.getMass();
        }
        return mass;
    }

    private void capacityChecker(UncountableItem item)throws MoreThanCapacityException{
        if(totalVolume()+item.getVolume() > this.volume || totalMass()+ item.getMass() > mass){
            throw new MoreThanCapacityException("MassBox capacity is full");
        }
    }

    private void addItemToLoadedBoxCheck() throws LoadedBoxException{
        if(loaded == true){
            throw new LoadedBoxException("This box is already placed in the container");
        }
    }


    private void misMatchChecker(InterfaceItem item)throws MismatchItemTypeException{
         if (item.getCountability() == "Countable" ){
            throw new MismatchItemTypeException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox");
        }
    }
    
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    public int getMass() {
        return mass;
    }

    public MassBox clone(){
        return new MassBox(this);
    }
    
    public String toString(){
        return "Code: " + code + " Mass: " + mass + " Volume: " + volume + " Serial Number: " + serialNumber+" Cost: " + getCost();
    }
    private int getCost() {
        return costPerUnit*volume;
    }

    public boolean getLoaded(){
        return loaded;
    }

    public void setLoaded(boolean loaded){
        this.loaded = loaded;
    }
    
    
}
