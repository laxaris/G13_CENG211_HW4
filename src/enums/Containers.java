package enums;
public enum Containers{
    NumberBox(2),MassBox(3),Container(1);

    private int cost;

    Containers(int cost){
        this.cost = cost;
    }
    
    public int getCost(){
        return cost;
    }
}
