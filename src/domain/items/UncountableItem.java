package domain.items;

public class UncountableItem extends Item {

    
    private double mass;
    public static final String countability = "Uncountable";
  



    public UncountableItem(String code, double mass, double volume, String serialNumber) {
        super(code, volume, serialNumber);   
        this.mass = mass;
    }

   

    public UncountableItem(){
        super();
        this.mass = 0;
    }
    
    public UncountableItem(UncountableItem item){
        super(item); 
        this.mass = item.getMass();
    }

    public double getMass() {
    	return mass;
    }

    public UncountableItem clone(){
        return new UncountableItem(this);
    }

    public String getCountability(){
        return countability;
    }
    
    public String toString(){
        return "Name: "+ getName()+" Code: " + getCode() + " Volume: " + getVolume() + " Serial Number: " + getSerialNumber()+" Cost:" +getCost()+" Price:"+getPrice();
    }
}
