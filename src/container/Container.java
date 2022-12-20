package container;

public class Container {
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
