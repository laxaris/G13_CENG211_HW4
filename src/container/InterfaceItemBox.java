package container;
import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import items.InterfaceItem;
import exceptions.BoxCapacityException;

public interface InterfaceItemBox {
    public String getCode();
    public double getVolume();
    public String getSerialNumber();
    public void add(InterfaceItem item) throws MismatchItemTypeException, BoxCapacityException, LoadedBoxException;
    public InterfaceItemBox clone();
    public double getCost();
    public double getRevenue();
}
