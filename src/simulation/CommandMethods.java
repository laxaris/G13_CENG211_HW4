package simulation;

import java.util.ArrayList;
import java.util.HashMap;

import container.Container;
import container.InterfaceItemBox;
import container.MassBox;
import container.NumberBox;
import exceptions.ItemPlacedDirectlyException;
import exceptions.LoadedBoxException;
import exceptions.LoadedItemException;
import exceptions.MismatchItemTypeException;
import exceptions.BoxCapacityException;
import exceptions.ContainerCapacityException;
import exceptions.SameSerialNumberException;
import items.CountableItem;
import items.InterfaceItem;
import items.UncountableItem;

public class CommandMethods {
    private static HashMap<String,Container> containerMap = new HashMap<String,Container>();
    private static HashMap<String,InterfaceItemBox> boxMap = new HashMap<String,InterfaceItemBox>();
    private static HashMap<String,InterfaceItem> itemMap = new HashMap<String,InterfaceItem>();
    private static ArrayList<String> serialNumberList = new ArrayList<String>();
    private static double revenue = 0;
    
    public static void produce(ArrayList<String> command) throws SameSerialNumberException {
        serialNumberExceptionChecker(command);
        switch(command.get(1)){
            case "B1":
                NumberBox numberBox = new NumberBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                boxMap.put(numberBox.getSerialNumber(),numberBox);
                revenue -= numberBox.getCost();
                System.out.println(numberBox.toString()+" Revenue: "+revenue+"");
                break;
            case "B2":
                MassBox massBox = new MassBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                boxMap.put(massBox.getSerialNumber(), massBox);
                revenue -= massBox.getCost();
                System.out.println(massBox.toString()+" Revenue: "+revenue+"");
                break;
            case "C1":
                Container container = new Container(command.get(1), Integer.parseInt(command.get(2)), command.get(3));
                containerMap.put(container.getSerialNumber(),container);
                revenue -= container.getCost();
                System.out.println(container.toString()+" Revenue: "+revenue+"");
                break;
            default:
            switch (enums.Items.searchItem(command.get(1)).getCountability()) {
                case "Countable":
                    CountableItem countableItem = new CountableItem(command.get(1),Double.parseDouble(command.get(2)), command.get(3));
                    itemMap.put(countableItem.getSerialNumber(),countableItem);
                    revenue -= countableItem.getCost();
                    System.out.println(countableItem.toString()+" Revenue: "+revenue+"");
                    break;
                case "Uncountable":
                    UncountableItem uncountableItem = new UncountableItem(command.get(1), Double.parseDouble(command.get(2)),Double.parseDouble(command.get(3)), command.get(4));
                    itemMap.put(uncountableItem.getSerialNumber(),uncountableItem);
                    revenue -= uncountableItem.getCost();
                    System.out.println(uncountableItem.toString()+" Revenue: "+revenue+"");
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
                break;
        }
        
    }

   

    private static void serialNumberExceptionChecker(ArrayList<String> command) throws SameSerialNumberException{
        if(serialNumberList.contains(command.get(command.size()-1))){
            throw new SameSerialNumberException("Item with the serial number "+ command.get(command.size()-1)+" cannot be produced (EX: 1 existing serial number");
        }
        else{
            serialNumberList.add(command.get(command.size()-1));}
    }

    public static void load(ArrayList<String> command) throws LoadedBoxException, ItemPlacedDirectlyException, MismatchItemTypeException, ContainerCapacityException, LoadedItemException, BoxCapacityException {
        String serialNumberOfLoader = command.get(2);
        String serialNumberOfLoadedItem = command.get(1);
        if(containerMap.containsKey(serialNumberOfLoader)){
            if(boxMap.containsKey(serialNumberOfLoadedItem)){
                containerMap.get(serialNumberOfLoader).add(boxMap.get(serialNumberOfLoadedItem));
                boxMap.remove(serialNumberOfLoadedItem);
            }else if(itemMap.containsKey(serialNumberOfLoadedItem)){
                containerMap.get(serialNumberOfLoader).add(itemMap.get(serialNumberOfLoadedItem));
                itemMap.remove(serialNumberOfLoadedItem);
            }
        }

        else if(boxMap.containsKey(serialNumberOfLoader)){
            if(itemMap.containsKey(serialNumberOfLoadedItem)){
                boxMap.get(serialNumberOfLoader).add(itemMap.get(serialNumberOfLoadedItem));
                itemMap.remove(serialNumberOfLoadedItem);
            }
            else{
                throw new LoadedItemException("The item with serial number "+serialNumberOfLoadedItem+"is already loaded to a box ");
            }
        }
        else{
            throw new LoadedBoxException(serialNumberOfLoadedItem+" cannot be loaded to "+serialNumberOfLoader+" (EX: 8 loaded box)");
        }


    }



    public static void ship(ArrayList<String> command) {
        String serialNumberOfShippedItem = command.get(1);
        if(containerMap.containsKey(serialNumberOfShippedItem)){
            revenue += containerMap.get(serialNumberOfShippedItem).getRevenue();
            System.out.print("Container "+serialNumberOfShippedItem+" is shipped. \t" + "Revenue: "+revenue+"");
            containerMap.remove(serialNumberOfShippedItem);
        }
        else{
            //To do : throw exception
        }
 }



    public static void revenue(ArrayList<String> command) {
        if(command.get(1).equals("1")){
            double unearedRevenue = 0;
            for(InterfaceItem item : itemMap.values()){
                unearedRevenue += item.getPrice();
            }
            System.out.println("\nUnearned revenue: "+unearedRevenue+"\n");
        }
        else if(command.get(1).equals("2")){
            System.out.println("Total revenue: " + revenue+"\n");
        }
    }
    
}
