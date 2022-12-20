package container;

public class MassBox implements InterfaceItemBox {
    private String code;
    private int mass;
    private int volume;
    private String serialNumber;
    private final int costPerUnit = 3;

    public MassBox(String code, int mass, int volume, String serialNumber) {
        this.code = code;
        this.mass = mass;
        this.volume = volume;
        this.serialNumber = serialNumber;
    }
    public MassBox(){
        this.code = "";
        this.mass = 0;
        this.volume = 0;
        this.serialNumber = "";
    }
    public MassBox(MassBox box){
        this.code = box.code;
        this.mass = box.mass;
        this.volume = box.volume;
        this.serialNumber = box.serialNumber;
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
    
}
