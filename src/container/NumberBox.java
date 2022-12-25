package container;

import java.util.ArrayList;

import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import exceptions.BoxCapacityException;
import exceptions.ContainerCapacityException;
import items.CountableItem;
import items.InterfaceItem;

public class NumberBox implements InterfaceItemBox {
    
    private String code;
    private int numberOfItems;
    private double volume;
    private String serialNumber;
    private final int costPerUnit = 2;
    private ArrayList<CountableItem> list;
    private double instantVolume;
    private int instantNumberOfItems;

    public NumberBox(String code, int numberOfItems, double volume, String serialNumber) {
        this.code = code;
        this.numberOfItems = numberOfItems;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.list = new ArrayList<CountableItem>();
        this.instantVolume = 0;
        this.instantNumberOfItems= 0;
    }

    
    public NumberBox() {
        this.code = "0";
        this.numberOfItems = 10;
        this.volume = 10;
        this.serialNumber = "0";
        this.instantVolume =0;
        this.instantNumberOfItems = 0;
    }

    public NumberBox(NumberBox box) {
        this.code = box.code;
        this.numberOfItems = box.numberOfItems;
        this.volume = box.volume;
        this.serialNumber = box.serialNumber;
        this.instantVolume = box.instantVolume;
        this.instantNumberOfItems = box.instantNumberOfItems;
    }

    public void add(InterfaceItem item) throws MismatchItemTypeException, BoxCapacityException, LoadedBoxException{
        misMatchChecker(item);
        capacityChecker((CountableItem) item);
        list.add((CountableItem) item);
        instantVolume += item.getVolume();
        instantNumberOfItems += 1;
        System.out.println("Item with serial number "+item.getSerialNumber()+" added to numberBox with serial number "+serialNumber+"volume:" +instantVolume+ "number of items:"+instantNumberOfItems);
        
    }
    private int totalNumberOfItems(){
        return list.size();
    }

    private void misMatchChecker(InterfaceItem item)throws MismatchItemTypeException{
         if (item.getCountability() == "Uncountable" ){
            throw new MismatchItemTypeException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox");
        }
    }

    private void capacityChecker(CountableItem item) throws BoxCapacityException {
        if(instantVolume+item.getVolume() >volume|| instantNumberOfItems+1 >numberOfItems){
            throw new BoxCapacityException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox with serial number "+serialNumber);
        }
    }


    @Override
    public String getCode() {
        return code;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    public double getCost(){
        return costPerUnit*volume;
    }

    public NumberBox clone() {
        return new NumberBox(this);
    }

    public double getRevenue(){
        double revenue = 0;
        for (CountableItem item : list){
            revenue += item.getPrice();
        }
        return revenue;
    }

    public String toString() {
        return "Code: " + code + " Number of Items: " + numberOfItems + " Volume: " + volume + " Serial Number: "
                + serialNumber+" Cost: " + getCost();
    }
    
}
