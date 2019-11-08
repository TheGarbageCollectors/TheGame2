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
        Room abandonedVillage = new Lootable("an abandoned villiage", "Abandoned Village");
        Room road = new Lootable("a long road", "Road");
        Room beach = new Lootable("a beach", "Beach");
        Room forrest = new Lootable("lots of trees", "Forrest");
        
        recycler = new Recycler("a recycler", "Recycler");
        town = new Town("the lovely town", "Town");
        upgradeStation = new UpgradeStation("an upgradestation", "Upgrade Station");

        town.setExit("road", road);
        town.setExit("recycler", recycler);
        town.setExit("upgradestation", upgradeStation);

        road.setExit("beach", beach);
        road.setExit("forrest", forrest);
        road.setExit("abandonedVillage", abandonedVillage);
        road.setExit("town", town);

        recycler.setExit("town", town);

        beach.setExit("road", road);

        upgradeStation.setExit("town", town);

        forrest.setExit("road", road);

        road.setExit("road", road);

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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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

        return wantToQuit;
    }

    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
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
                    System.out.println(((Recycler) currentRoom).valueCalculator(itemsInBag.get(i)));
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
                    break;
                case "recycler":
                    ((UpgradeStation)upgradeStation).buyUpgrade(recycler, player1);
                    break;
                case "backpack":
                    ((UpgradeStation)upgradeStation).buyUpgrade(player1.getBackpackObj(), player1);
                    break;
            }
            System.out.println(((Upgradeable)town).getLevel());
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
            } else if (nextRoom instanceof Town)
            {
                System.out.println("You have " + ((Town) nextRoom).getPopulationCount() + " slaves");
            } else if (nextRoom instanceof UpgradeStation)
            {
                System.out.println("You are in upgrade station");
            } else if (nextRoom instanceof Recycler)
            {
                System.out.print("You can recycle your stuff here");
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
                System.out.print(items.get(i).getName().equals(command.getSecondWord()));
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
