package items;

public class CountableItem implements InterfaceItem {
    private String code;
    private double volume;
    private String serialNumber;
    private int costPerUnit;
    private int pricePerUnit;
    private String name;

    public CountableItem(String code, double volume, String serialNumber) {
        this.code = code;
        this.volume = volume;
        this.serialNumber = serialNumber;
        this.costPerUnit = enums.Items.searchItem(code).getCost();
        this.pricePerUnit = enums.Items.searchItem(code).getPrice();
        this.name = enums.Items.searchItem(code).getName();
    }
    public CountableItem(){
        this.code = "";
        this.volume = 0;
        this.serialNumber = "";
        this.costPerUnit = 0;
        this.pricePerUnit = 0;
        this.name = "";
    }
    public CountableItem(CountableItem item){
        this.code = item.code;
        this.volume = item.volume;
        this.serialNumber = item.serialNumber;
        this.costPerUnit = item.costPerUnit;
        this.pricePerUnit = item.pricePerUnit;
        this.name = item.name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getVolume() {
        return volume;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getCost() {
        return costPerUnit*volume;
    }

    public double getPrice() {
        return pricePerUnit*volume;
    }

    public CountableItem clone(){
        return new CountableItem(this);
    }
    
    public String toString(){
        return "Name: "+ name+" Code: " + code + " Volume: " + volume + " Serial Number: " + serialNumber+" Cost:" +getCost()+" Price:"+getPrice();
    }
}
