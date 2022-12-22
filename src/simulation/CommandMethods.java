package simulation;

import java.util.ArrayList;
import java.util.HashMap;

import container.Container;
import container.InterfaceItemBox;
import container.MassBox;
import container.NumberBox;
import exceptions.ItemPlacedDirectlyException;
import exceptions.LoadedBoxException;
import exceptions.MismatchItemTypeException;
import exceptions.SameSerialNumberException;
import exceptions.ThoseShippedException;
import items.CountableItem;
import items.InterfaceItem;
import items.UncountableItem;

public class CommandMethods {
    private static HashMap<String,Container> containerMap = new HashMap<String,Container>();
    private static HashMap<String,NumberBox> numberBoxMap = new HashMap<String,NumberBox>();
    private static HashMap<String,MassBox> massBoxMap = new HashMap<String,MassBox>();
    private static HashMap<String,CountableItem> countableItemMap = new HashMap<String,CountableItem>();
    private static HashMap<String,UncountableItem> uncountableItemMap = new HashMap<String,UncountableItem>();
    private static ArrayList<String> serialNumberList = new ArrayList<String>();
    
    public static void produce(ArrayList<String> command) throws SameSerialNumberException {
        serialNumberExceptionChecker(command);
        switch(command.get(1)){
            case "B1":
                NumberBox numberBox = new NumberBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                numberBoxMap.put(numberBox.getSerialNumber(),numberBox);
                System.out.println(numberBox.toString());
                break;
            case "B2":
                MassBox massBox = new MassBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                massBoxMap.put(massBox.getSerialNumber(), massBox);
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
                    countableItemMap.put(countableItem.getSerialNumber(),countableItem);
                    System.out.println(countableItem.toString());
                    break;
                case "Uncountable":
                    UncountableItem uncountableItem = new UncountableItem(command.get(1), Double.parseDouble(command.get(2)),Double.parseDouble(command.get(3)), command.get(4));
                    uncountableItemMap.put(uncountableItem.getSerialNumber(),uncountableItem);
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

    public static void load(ArrayList<String> command) {
        if(containerMap.containsKey(command.get(2))){
            if(numberBoxMap.containsKey(command.get(1))){
                try {
                    containerMap.get(command.get(2)).add(numberBoxMap.get(command.get(1)));
                } catch (LoadedBoxException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(massBoxMap.containsKey(command.get(1))){
                try {
                    containerMap.get(command.get(2)).add(massBoxMap.get(command.get(1)));
                } catch (LoadedBoxException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(countableItemMap.containsKey(command.get(1))){
                try {
                    containerMap.get(command.get(2)).add(countableItemMap.get(command.get(1)));
                } catch (ItemPlacedDirectlyException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(uncountableItemMap.containsKey(command.get(1))){
                try {
                    containerMap.get(command.get(2)).add(uncountableItemMap.get(command.get(1)));
                } catch (ItemPlacedDirectlyException e) {
                    System.out.println(e.getMessage());
                }
            }
            
        }


        
    }


    private static void itemToContainerChecker(){

    }
    
    
}
