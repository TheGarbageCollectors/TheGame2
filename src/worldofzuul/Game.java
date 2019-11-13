package worldofzuul;

import java.util.ArrayList;
import java.util.HashMap;

public class Game
{

    private Parser parser;
    private Room currentRoom, town, recycler, upgradeStation;

    private Player player1 = new Player();

    public Game()
    {
        createRooms();
        parser = new Parser();

    }

    private void createRooms()
    {
        Room abandonedVillage = new Lootable("in an abandoned villiage", "Abandoned Village");
        Room road = new Lootable("on a long road", "Road");
        Room beach = new Lootable("on a beach", "Beach");
        Room forrest = new Lootable("in a forrest", "Forrest");
        
        recycler = new Recycler("at the recycler", "Recycler");
        town = new Town("by the Town Hall", "Town Hall");
        upgradeStation = new UpgradeStation("an upgradestation", "Upgrade Station");

        town.setExit("Road", road);
        town.setExit("Recycler", recycler);
        town.setExit("Upgradestation", upgradeStation);

        road.setExit("Beach", beach);
        road.setExit("Forrest", forrest);
        road.setExit("Abandoned_Village", abandonedVillage);
        road.setExit("Town_Hall", town);

        recycler.setExit("Town_Hall", town);

        beach.setExit("Road", road);

        upgradeStation.setExit("Town_Hall", town);

        forrest.setExit("Road", road);

        abandonedVillage.setExit("Road", road);
        //road.setExit("road", road);

        currentRoom = town;
    }

    public void play()
    {
        printWelcome();

        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
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

    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN)
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP)
        {
            printHelp();
        } else if (commandWord == CommandWord.GO)
        {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT)
        {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.PICKUP)
        {
            pickupItems(command);
        } else if (commandWord == CommandWord.UPGRADE)
        {
            upgradeItems(command);
        } else if (commandWord == CommandWord.RECYCLE)
        {
            recycleItems(command);
        }
        else if (commandWord == CommandWord.INVENTORY)
        {
            openInventory(command);
        }

        return wantToQuit;
    }

    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Town.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();

    }
    private void recycleItems(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Recycle what?");
            return;
        }
        if (currentRoom instanceof Recycler)
        {
            String itemToBeRecycled = command.getSecondWord();
            var itemsInBag = player1.getBackpackObj().getItemsInBackpack();
            for (int i = 0; i < itemsInBag.size(); i++)
            {
                if (itemToBeRecycled.equals(itemsInBag.get(i).getName()))
                {
                    var money = ((Recycler) currentRoom).valueCalculator(itemsInBag.get(i));
                    player1.addMoney(money);
                }
            }
        }

    }

    private void upgradeItems(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Upgrade what?");
            return;
        }
        if (currentRoom instanceof UpgradeStation)
        {
            String thingToUpgrade = command.getSecondWord();
            switch (thingToUpgrade)
            {
                case "town":
                    ((UpgradeStation)upgradeStation).buyUpgrade(town, player1);
                    System.out.println("Town Hall level is now: " + ((Upgradeable)town).getLevel());
                    break;
                case "recycler":
                    ((UpgradeStation)upgradeStation).buyUpgrade(recycler, player1);
                    System.out.println("Recycler level is now: " + ((Upgradeable)recycler).getLevel());
                    break;
                case "backpack":
                    ((UpgradeStation)upgradeStation).buyUpgrade(player1.getBackpackObj(), player1);
                    System.out.println("Backpack level is now: " + ((Upgradeable)player1.getBackpackObj()).getLevel());
                    break;
            }
            
        }

    }

    private void goRoom(Command command)
    {
        if (!command.hasSecondWord())
        {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println("There is no door!");
        } else
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if (nextRoom instanceof Lootable)
            {
                System.out.print("You see ");
                var items = ((Lootable) nextRoom).getLoot();
                for (int i = 0; i < items.size(); i++)
                {
                    System.out.print(items.get(i).getName());
                    if ((i + 1) != items.size())
                    {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } 
            else if (nextRoom instanceof Town)
            {
                System.out.println("The Town's happiness level: " + ((Town) nextRoom).getPopulationCount());
            } 
            else if (nextRoom instanceof UpgradeStation)
            {
                ((UpgradeStation) upgradeStation).welcomeMessage(player1.getBackpackObj(), town, recycler);
            } 
            else if (nextRoom instanceof Recycler)
            {
                System.out.println("You can recycle your stuff here");
            }

        }
    }

    private void pickupItems(Command command)
    {
        if (command.hasSecondWord())
        {
            GameItems gameitems = new GameItems();
            var items = gameitems.getLootList(((Lootable) currentRoom).getName());
            for (int i = 0; i < items.size(); i++)
            {
                //System.out.print(items.get(i).getName().equals(command.getSecondWord()));
                if (items.get(i).getName().equals(command.getSecondWord()))
                {
                    player1.getBackpackObj().addItem(items.get(i));
                }
            }

        } else
        {
            System.out.println("Pick up what?");
        }
    }
    
    private void openInventory(Command command) {
        ArrayList<Item> temp = player1.getBackpackObj().getItemsInBackpack();
        System.out.print("Your inventory contains: ");
        for (int i = 0; i < temp.size() ; i++) {
            System.out.print(temp.get(i).getName());
            if (i+1 != temp.size()) {
                System.out.print(", ");
            }
        }
        System.out.println("");
    }

    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        } else
        {
            return true;
        }
    }
}
