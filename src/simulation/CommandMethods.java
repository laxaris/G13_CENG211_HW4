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
import exceptions.MoreThanCapacityException;
import exceptions.SameSerialNumberException;
import exceptions.ThoseShippedException;
import items.CountableItem;
import items.InterfaceItem;
import items.UncountableItem;

public class CommandMethods {
    private static HashMap<String,Container> containerMap = new HashMap<String,Container>();
    private static HashMap<String,InterfaceItemBox> boxMap = new HashMap<String,InterfaceItemBox>();
    private static HashMap<String,InterfaceItem> itemMap = new HashMap<String,InterfaceItem>();
    
    private static ArrayList<String> serialNumberList = new ArrayList<String>();
    
    public static void produce(ArrayList<String> command) throws SameSerialNumberException {
        serialNumberExceptionChecker(command);
        switch(command.get(1)){
            case "B1":
                NumberBox numberBox = new NumberBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                boxMap.put(numberBox.getSerialNumber(),numberBox);
                System.out.println(numberBox.toString());
                break;
            case "B2":
                MassBox massBox = new MassBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                boxMap.put(massBox.getSerialNumber(), massBox);
                System.out.println(massBox.toString());
                break;
            case "C1":
                Container container = new Container(command.get(1), Integer.parseInt(command.get(2)), command.get(3));
                containerMap.put(container.getSerialNumber(),container);
                System.out.println(container.toString());
                break;
            default:
            switch (enums.Items.searchItem(command.get(1)).getCountability()) {
                case "Countable":
                    CountableItem countableItem = new CountableItem(command.get(1),Double.parseDouble(command.get(0)), command.get(3));
                    itemMap.put(countableItem.getSerialNumber(),countableItem);
                    System.out.println(countableItem.toString());
                    break;
                case "Uncountable":
                    UncountableItem uncountableItem = new UncountableItem(command.get(1), Double.parseDouble(command.get(2)),Double.parseDouble(command.get(3)), command.get(4));
                    itemMap.put(uncountableItem.getSerialNumber(),uncountableItem);
                    System.out.println(uncountableItem.toString());
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

    public static void load(ArrayList<String> command) throws LoadedBoxException, ItemPlacedDirectlyException, MismatchItemTypeException, MoreThanCapacityException, LoadedItemException {
        String serialNumberOfLoader = command.get(2);
        String serialNumberOfLoadedItem = command.get(1);
        if(containerMap.containsKey(serialNumberOfLoader)){
            if(boxMap.containsKey(serialNumberOfLoadedItem)){
                containerMap.get(serialNumberOfLoader).add(boxMap.remove(serialNumberOfLoadedItem));

            }else if(itemMap.containsKey(serialNumberOfLoadedItem)){
                containerMap.get(serialNumberOfLoader).add(itemMap.remove(serialNumberOfLoadedItem));
            }
        }

        else if(boxMap.containsKey(serialNumberOfLoader)){
            if(itemMap.containsKey(serialNumberOfLoadedItem)){
                boxMap.get(serialNumberOfLoader).add(itemMap.remove(serialNumberOfLoadedItem));
                
                
            }
            else{
                throw new LoadedItemException("The item with serial number "+serialNumberOfLoadedItem+"is already loaded to a box ");
            }
        }
        else{
            throw new LoadedBoxException(serialNumberOfLoadedItem+" cannot be loaded to "+serialNumberOfLoader+" (EX: 8 loaded box)");
        }


    }


    private static void itemToContainerChecker(){

    }
    
    
}
