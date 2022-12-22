package items;

public class UncountableItem implements InterfaceItem {

    private String code;
    private double mass;
    private double volume;
    private String serialNumber;
    private int costPerUnit;
    private int pricePerUnit;
    private String name;
    private final String countability = "Uncountable";
    private boolean boxed;

    public UncountableItem(String code, double mass, double volume, String serialNumber) {
        this.code = code;
        this.mass = mass;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.costPerUnit = enums.Items.searchItem(code).getCost();
        this.pricePerUnit = enums.Items.searchItem(code).getPrice();
        this.name = enums.Items.searchItem(code).getName();
        this.boxed = false;
    }
    public UncountableItem(){
        this.code = "";
        this.mass = 0;
        this.volume = 0;
        this.serialNumber = "";
        this.costPerUnit = 0;
        this.pricePerUnit = 0;
        this.name= "";
        this.boxed = false;
    }
    public UncountableItem(UncountableItem item){
        this.code = item.code;
        this.mass = item.mass;
        this.volume = item.volume;
        this.serialNumber = item.serialNumber;
        this.costPerUnit = item.costPerUnit;
        this.pricePerUnit = item.pricePerUnit;
        this.name = item.name;
        this.boxed = item.getBoxed();
    }

    public String getName(){
        return name;
    }

    public void setBoxed(boolean boxed){
        this.boxed = boxed;
    }

    public boolean getBoxed(){
        return boxed; 
    }

    public String getCountability() {
        return countability;
    }

    public String getCode() {
        return code;
    }

    public double getMass() {
        return mass;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    
    public double getVolume() {
        return volume;
    }

    public double getCost() {
        return costPerUnit*mass;
    }

    public double getPrice() {
        return pricePerUnit*volume;
    }

    public UncountableItem clone(){
        return new UncountableItem(this);
    }

    public String toString(){
        return "Name:" +name+" Code: " + code + " Mass: " + mass + " Volume: " + volume + " Serial Number: " + serialNumber+" Cost:" +getCost()+" Price:"+getPrice(); 
    }
    
}
