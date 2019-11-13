package worldofzuul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

    private Parser parser;
    private Room currentRoom, town, recycler, upgradeStation;
    private ArrayList<Item> itemsInRoom; 

    private Player player1 = new Player();

    public Game() {
        createRooms();
        parser = new Parser();

    }

 private void createRooms() {
        Room abandonedVillage = new Lootable("in an abandoned villiage", "abandoned village");
        Room road = new Lootable("on a long road", "road");
        Room beach = new Lootable("on a beach", "beach");
        Room forrest = new Lootable("in a forrest", "forrest");

        recycler = new Recycler("at the recycler", "recycler");
        town = new Town("by the Town Hall", "town_hall");
        upgradeStation = new UpgradeStation("an upgradestation", "upgrade station");

        town.setExit("road", road);
        town.setExit("recycler", recycler);
        town.setExit("upgradestation", upgradeStation);

        road.setExit("beach", beach);
        road.setExit("forrest", forrest);
        road.setExit("abandoned_village", abandonedVillage);
        road.setExit("town_hall", town);

        recycler.setExit("town_hall", town);

        beach.setExit("road", road);

        upgradeStation.setExit("town_hall", town);

        forrest.setExit("road", road);

        abandonedVillage.setExit("road", road);
        //road.setExit("road", road);

        currentRoom = town;
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the Garbage Collectors!");
        System.out.println("This is a game about collecting different sorts of garbage and learning how to sort it properly.");
        System.out.println("The goal of the game is to raise your populations happiness-level");
        System.out.println("The local area is littered with garbage");
        System.out.println("It's your job as the mayor to make the town happy, by keeping the streets clean");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.PICKUP) {
            pickupItems(command);
        } else if (commandWord == CommandWord.UPGRADE) {
            upgradeItems(command);
        } else if (commandWord == CommandWord.RECYCLE) {
            recycleItems(command);
        } else if (commandWord == CommandWord.INVENTORY) {
            openInventory(command);
        }

        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Town.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();

    }

   private void recycleItems(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Recycle what?");
            return;
        }
        if (currentRoom instanceof Recycler) {
            int recyclerLevel = ((Upgradeable) recycler).getLevel();
            Scanner reader = new Scanner(System.in);
            String itemToBeRecycled = command.getSecondWord();
            System.out.print("How do you wish to recycle your item? ");
            switch (recyclerLevel) {
                case 1: 
                    System.out.println("Metal, Plastic, Trash");
                    break;
                case 2:
                    System.out.println("Metal, Plastic, Trash, Paper, Concrete");
                    break;
                case 3:
                    System.out.println("Metal, Plastic, Trash, Paper, Concrete, Battery, Hazardous");
                    break;
            }
                    String input = reader.nextLine();
                    if (input.equalsIgnoreCase("Metal")) {
                        switch (itemToBeRecycled) {
                            case "Can":
                            case "Wheel":
                            case "Bicycle":
                            case "Pipes":
                            case "Door":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else if (input.equalsIgnoreCase("Plastic")) {
                                switch (itemToBeRecycled) {
                            case "Bottle":
                            case "Straw":
                            case "Bag":
                            case "Beachball":
                            case "Gumpaper":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else if (input.equalsIgnoreCase("Garbage")) {
                                switch (itemToBeRecycled) {
                            case "Trash":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else if (input.equalsIgnoreCase("Paper") && recyclerLevel > 1) {
                                switch (itemToBeRecycled) {
                            case "Box":
                            case "Carton":
                            case "Toiletpaper":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else if (input.equalsIgnoreCase("Concrete") && recyclerLevel > 1) {
                                switch (itemToBeRecycled) {
                            case "Bricks":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else if (input.equalsIgnoreCase("Battery") && recyclerLevel > 2) {
                                switch (itemToBeRecycled) {
                            case "Battery":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else if (input.equalsIgnoreCase("Hazardous") && recyclerLevel > 2) {
                                switch (itemToBeRecycled) {
                            case "Lighter":
                                 for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
                                
                                if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName())) 
                                    {
                                        var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                                        player1.addMoney(money);
                                        
                                    }
                                 }
                                removeItemWhenRecycled(command);
                                System.out.println("This item does belong here points awarded (:");
                                break;
                            default:
                                removeItemWhenRecycled(command);
                                //Method for removing recycler HP
                                System.out.println("This item doesn't belong here, it has been wasted");
                                break;
                        } 
                    } else {
                        System.out.println("This container does not exist. ");
                    }
        }
    }

    private void upgradeItems(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Upgrade what?");
            return;
        }
        if (currentRoom instanceof UpgradeStation) {
            String thingToUpgrade = command.getSecondWord();
            switch (thingToUpgrade) {
                case "town":
                    ((UpgradeStation) upgradeStation).buyUpgrade(town, player1);
                    System.out.println("Town Hall level is now: " + ((Upgradeable) town).getLevel());
                    break;
                case "recycler":
                    ((UpgradeStation) upgradeStation).buyUpgrade(recycler, player1);
                    System.out.println("Recycler level is now: " + ((Upgradeable) recycler).getLevel());
                    break;
                case "backpack":
                    ((UpgradeStation) upgradeStation).buyUpgrade(player1.getBackpackObj(), player1);
                    System.out.println("Backpack level is now: " + ((Upgradeable) player1.getBackpackObj()).getLevel());
                    System.out.println("Your inventory size is now: " + player1.getBackpackObj().getMaxSize());
                    break;
            }

        }else{
            System.out.println("You are not at upgrade station");
            }

    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if (nextRoom instanceof Lootable) {
                System.out.print("You see ");
                this.itemsInRoom = ((Lootable) nextRoom).getLoot();
                for (int i = 0; i < this.itemsInRoom.size(); i++) {
                    System.out.print(this.itemsInRoom.get(i).getName());
                    if ((i + 1) != this.itemsInRoom.size()) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } else if (nextRoom instanceof Town) {
                System.out.println("The Town's happiness level: " + ((Town) nextRoom).getPopulationCount());
            } else if (nextRoom instanceof UpgradeStation) {
                ((UpgradeStation) upgradeStation).welcomeMessage(player1.getBackpackObj(), town, recycler);
            } else if (nextRoom instanceof Recycler) {
                System.out.println("You can recycle your stuff here");
            }

        }
    }

    private void pickupItems(Command command) {
        if (command.hasSecondWord()) {
            if (currentRoom instanceof Lootable) {
                for (int i = 0; i < this.itemsInRoom.size(); i++) {
                    //System.out.print(items.get(i).getName().equals(command.getSecondWord()));
                    if (this.itemsInRoom.get(i).getName().equalsIgnoreCase(command.getSecondWord())) {
                        player1.getBackpackObj().addItem(this.itemsInRoom.get(i));
                        break; // breaks out of the loop if it finds a mathcing item
                    }else if(i == (this.itemsInRoom.size())-1){
                        //If there is no macthing item on the last run of the loop, then say there is no match
                        System.out.println("There is no " + command.getSecondWord());   
                    }
                }
            }else{
                System.out.println("You are not in a room where you can pick anything up");
            }

        } else {
            System.out.println("Pick up what?");
        }
    }

    private void openInventory(Command command) {
        ArrayList<Item> temp = player1.getBackpackObj().getItemsInBackpack();
        System.out.print("Your inventory contains: ");
        for (int i = 0; i < temp.size(); i++) {
            System.out.print(temp.get(i).getName());
            if (i + 1 != temp.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
    
    public void removeItemWhenRecycled(Command command) 
    {
        ArrayList<Item> temp = player1.getBackpackObj().getItemsInBackpack();
        for( Item e : temp) 
        {
            System.out.print(e.getName() + " ");
        }
                                
        
        for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++) {
            
            if (player1.getBackpackObj().getItemsInBackpack().get(i).getName().equals(command.getSecondWord())) {
                player1.getBackpackObj().removeItem(player1.getBackpackObj().getItemsInBackpack().get(i));
                i = player1.getBackpackObj().getItemsInBackpack().size()+1;
            } else {
                System.out.println("This item is not in your backpack");
            }
        }
        System.out.println();
        for( Item e : temp) 
        {
            System.out.print(e.getName() + " ");
        }
        System.out.println(" ");
    }
}
