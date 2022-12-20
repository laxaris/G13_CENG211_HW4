package enums;
import java.util.ArrayList;

public class EnumList {
    private static final ArrayList<Containers> containersList = getEnumContainers();
    private static final ArrayList<Items> itemsList = getEnumItems();

    private static ArrayList<Containers> getEnumContainers(){
        ArrayList<Containers> containersList = new ArrayList<Containers>();
        for(Containers container : Containers.values()){
            containersList.add(container);
        }
        return containersList;
    }

    private static ArrayList<Items> getEnumItems(){
        ArrayList<Items> itemsList = new ArrayList<Items>();
        for(Items item : Items.values()){
            itemsList.add(item);
        }
        return itemsList;
    }

    public static ArrayList<Containers> getContainersList(){
        return containersList;
    }

    public static ArrayList<Items> getItemsList(){
        return itemsList;
    }
}
