package container;

import java.util.ArrayList;

import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import exceptions.MoreThanCapacityException;
import items.CountableItem;
import items.InterfaceItem;

public class NumberBox implements InterfaceItemBox {
    
    private String code;
    private int numberOfItems;
    private int volume;
    private String serialNumber;
    private final int costPerUnit = 2;
    private ArrayList<CountableItem> list;
    private boolean loaded;

    public NumberBox(String code, int numberOfItems, int volume, String serialNumber) {
        this.code = code;
        this.numberOfItems = numberOfItems;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.list = new ArrayList<CountableItem>();
        this.loaded = false;
    }

    
    public NumberBox() {
        this.code = "";
        this.numberOfItems = 0;
        this.volume = 0;
        this.serialNumber = "";
        this.loaded = false;
    }

    public NumberBox(NumberBox box) {
        this.code = box.code;
        this.numberOfItems = box.numberOfItems;
        this.volume = box.volume;
        this.serialNumber = box.serialNumber;
         this.loaded = box.getLoaded();
    }

    public void add(InterfaceItem item) throws MismatchItemTypeException, MoreThanCapacityException, LoadedBoxException{
        misMatchChecker(item);
        capacityChecker((CountableItem) item);
        addItemToLoadedBoxCheck();
        list.add((CountableItem) item);
        System.out.println(serialNumber+" added item "+item.getSerialNumber());
        
    }

    private int totalVolume(){
        int volume = 0;
        for(InterfaceItem item : list){
            volume += item.getVolume();
        }
        return volume;
    }

    private int totalNumberOfItems(){
        return list.size();
    }

    private void misMatchChecker(InterfaceItem item)throws MismatchItemTypeException{
         if (item.getCountability() == "Uncountable" ){
            throw new MismatchItemTypeException("Item with serial number "+item.getSerialNumber()+" cannot be added to massBox");
        }
    }

    private void capacityChecker(CountableItem item) throws MoreThanCapacityException {
        if(totalVolume()+item.getVolume() > this.volume || totalNumberOfItems()+1 > this.numberOfItems){
            throw new MoreThanCapacityException("MassBox capacity is full");
        }
    }

    private void addItemToLoadedBoxCheck() throws LoadedBoxException{
        if(loaded == true){
            throw new LoadedBoxException("This box is already placed in the container");
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
    public int getVolume() {
        return volume;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    public int getCost(){
        return costPerUnit*volume;
    }

    public NumberBox clone() {
        return new NumberBox(this);
    }

    public boolean getLoaded(){
        return loaded;
    }

    public void setLoaded(boolean loaded){
        this.loaded = loaded;
    }

    public String toString() {
        return "Code: " + code + " Number of Items: " + numberOfItems + " Volume: " + volume + " Serial Number: "
                + serialNumber+" Cost: " + getCost();
    }
    
}
