package container;

public class NumberBox implements InterfaceItemBox {
    private String code;
    private int numberOfItems;
    private int volume;
    private String serialNumber;
    private final int costPerUnit = 2;

    public NumberBox(String code, int numberOfItems, int volume, String serialNumber) {
        this.code = code;
        this.numberOfItems = numberOfItems;
        this.volume = volume;
        this.serialNumber = serialNumber;
    }

    public NumberBox() {
        this.code = "";
        this.numberOfItems = 0;
        this.volume = 0;
        this.serialNumber = "";
    }

    public NumberBox(NumberBox box) {
        this.code = box.code;
        this.numberOfItems = box.numberOfItems;
        this.volume = box.volume;
        this.serialNumber = box.serialNumber;
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

    public String toString() {
        return "Code: " + code + " Number of Items: " + numberOfItems + " Volume: " + volume + " Serial Number: "
                + serialNumber+" Cost: " + getCost();
    }
    
}
