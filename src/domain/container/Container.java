package domain.container;

import java.util.ArrayList;

import domain.exceptions.ItemPlacedDirectlyException;
import domain.items.Item;
import domain.exceptions.ContainerCapacityException;


public class Container <T extends ItemBox<Item>> {
    private ArrayList<T> list;
    private String code;
    private double volume;
    private double instantVolume;
    private String serialNumber;
    private final int costPerUnit = 1;

    public Container(String code, double volume, String serialNumber) {
        this.code = code;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.list = new ArrayList<T>();
        this.instantVolume = 0;
    }

    public Container() {
        this.code = "";
        this.volume = 10;
        this.serialNumber = "";
        this.list = new ArrayList<T>();
        this.instantVolume = 0;
    }

    @SuppressWarnings("unchecked")
	public Container(Container<T> container) {
        this.code = container.code;
        this.volume = container.volume;
        this.serialNumber = container.serialNumber;
        this.list = new ArrayList<>();
        this.instantVolume = container.instantVolume;
        for(T itemBox : (ArrayList<T>)container.list){
            list.add((T) itemBox.clone());
        }
    }

   
	public void add(T itemBox) throws ContainerCapacityException{
        if(instantVolume+itemBox.getVolume()<=this.volume){
            list.add((T) itemBox);
        System.out.println("Item box with serial number " 
        + itemBox.getSerialNumber() 
        + " added to the container with serial number " 
        + serialNumber);
        instantVolume += itemBox.getVolume();
        }

        else{
            throw new ContainerCapacityException("The box with serial number " + itemBox.getSerialNumber() + " can not be added to the container with serial number " + serialNumber + " because the container is full") ;
        }
        
    }

    public void add(Item item)throws ItemPlacedDirectlyException{
        throw new ItemPlacedDirectlyException("Item with serial number " + item.getSerialNumber() + " can not be placed directly into the container");
    }


    public String getCode() {
        return code;
    }

    public double getCost(){
        return costPerUnit * volume;
    }

    public double getVolume() {
        return volume;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Container<T> clone() {
        return new Container<T>(this);
    }

    public double getRevenue(){
        double revenue = 0;
        for(T itemBox : list){
            revenue += itemBox.getRevenue();
        }
        return revenue;
    }

    public String toString() {
        return "Code: " + code + " Volume: " + volume + " Serial Number: " + serialNumber +" Cost: " + getCost();
    }


}
