package container;
import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import items.InterfaceItem;
import exceptions.MoreThanCapacityException;

public interface InterfaceItemBox {
    public String getCode();
    public int getVolume();
    public String getSerialNumber();
    public void add(InterfaceItem item) throws MismatchItemTypeException, MoreThanCapacityException, LoadedBoxException;
    public InterfaceItemBox clone();
}
