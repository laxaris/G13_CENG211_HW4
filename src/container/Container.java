package container;

import java.util.ArrayList;

import exceptions.ItemPlacedDirectlyException;
import exceptions.LoadedBoxException;
import exceptions.ThoseShippedException;
import items.InterfaceItem;

public class Container {
    private ArrayList<InterfaceItemBox> list;
    private String code;
    private int volume;
    private String serialNumber;
    private final int costPerUnit = 1;

    public Container(String code, int volume, String serialNumber) {
        this.code = code;
        this.volume = volume;
        this.serialNumber = serialNumber;
    }

    public Container() {
        this.code = "";
        this.volume = 0;
        this.serialNumber = "";
    }

    public Container(Container container) {
        this.code = container.code;
        this.volume = container.volume;
        this.serialNumber = container.serialNumber;
    }

    public void add(InterfaceItemBox itemBox)throws LoadedBoxException{
        list.add(itemBox);
    }

    public void add(InterfaceItem item)throws ItemPlacedDirectlyException{
        throw new ItemPlacedDirectlyException("Item with serial number " + item.getSerialNumber() + " can not be placed directly into the container");
    }


    public String getCode() {
        return code;
    }

    public int getCost(){
        return costPerUnit * volume;
    }

    public int getVolume() {
        return volume;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Container clone() {
        return new Container(this);
    }

    public String toString() {
        return "Code: " + code + " Volume: " + volume + " Serial Number: " + serialNumber +" Cost: " + getCost();
    }


}
