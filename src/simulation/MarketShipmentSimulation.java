package simulation;

import java.util.ArrayList;
import java.util.Queue;

import container.*;
import items.CountableItem;
import items.UncountableItem;

public class MarketShipmentSimulation {
    public static void runApp(){
        Queue<ArrayList<String>> commands = fileIO.FileIO.commands;
        while(!commands.isEmpty()){
            ArrayList<String> command = commands.poll();
            switch(command.get(0)){
                case "1":
                    Produce(command);
                    break;
                case "2":
                    //ToDO
                    break;
                case "3":
                    //ToDO 
                    break;
                case "4":
                    //ToDO
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        } 
    }

    private static void Produce(ArrayList<String> command) {
        switch(command.get(1)){
            case "B1":
                NumberBox numberBox = new NumberBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                System.out.println(numberBox.toString());
                break;
            case "B2":
                MassBox massBox = new MassBox(command.get(1), Integer.parseInt(command.get(2)),Integer.parseInt(command.get(3)), command.get(4));
                System.out.println(massBox.toString());
                break;
            case "C1":
                Container container = new Container(command.get(1), Integer.parseInt(command.get(2)), command.get(3));
                System.out.println(container.toString());
                break;
            default:
            switch (enums.Items.searchItem(command.get(1)).getCountability()) {
                case "Countable":
                    CountableItem item = new CountableItem(command.get(1),Double.parseDouble(command.get(0)), command.get(3));
                    System.out.println(item.toString());
                    break;
                case "Uncountable":
                    UncountableItem item2 = new UncountableItem(command.get(1), Double.parseDouble(command.get(2)),Double.parseDouble(command.get(3)), command.get(4));
                    System.out.println(item2.toString());
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
                break;
        }
        
    }
}
