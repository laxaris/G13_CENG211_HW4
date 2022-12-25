package container;

import java.util.ArrayList;

import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import exceptions.BoxCapacityException;
import exceptions.ContainerCapacityException;
import items.InterfaceItem;
import items.UncountableItem;


public class MassBox implements InterfaceItemBox {

    private ArrayList<UncountableItem> list;
    private String code;
    private double mass;
    private double volume;
    private String serialNumber;
    private final int costPerUnit = 3;
    private double instantVolume;
    private double instantMass;

    public MassBox(String code, double mass, double volume, String serialNumber) {
        this.code = code;
        this.mass = mass;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.list = new ArrayList<UncountableItem>();
        this.instantVolume = 0;
        this.instantMass = 0;
    }
    
    public MassBox(){
        this.code = "";
        this.mass = 10;
        this.volume = 10;
        this.serialNumber = "";
        this.instantMass=0;
        this.instantVolume=0;
    }
    public MassBox(MassBox box){
        this.code = box.code;
        this.mass = box.mass;
        this.volume = box.volume;
        this.serialNumber = box.serialNumber;
    }


    @Override
    public void add(InterfaceItem item) throws MismatchItemTypeException,BoxCapacityException, LoadedBoxException{
        misMatchChecker(item);
        capacityChecker((UncountableItem)item);
        list.add((UncountableItem) item);
        instantVolume += item.getVolume();
        instantMass += ((UncountableItem)item).getMass();
        System.out.println("Item with serial number "+item.getSerialNumber()+" added to massBox with serial number "+serialNumber);
    }

    private void capacityChecker(UncountableItem item)throws BoxCapacityException{
        if (instantVolume + item.getVolume() > volume|| instantMass + item.getMass() > mass){
            throw new BoxCapacityException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox with serial number "+serialNumber+" because it is full");
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
    public double getVolume() {
        return volume;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    public double getMass() {
        return mass;
    }

    public MassBox clone(){
        return new MassBox(this);
    }
    
    public double getRevenue(){
        double revenue = 0;
        for (UncountableItem item : list){
            revenue += item.getPrice();
        }
        return revenue;
    }

    public String toString(){
        return "Code: " + code + " Mass: " + mass + " Volume: " + volume + " Serial Number: " + serialNumber+" Cost: " + getCost();
    }
    public double getCost() {
        return costPerUnit*volume;
    }
}
