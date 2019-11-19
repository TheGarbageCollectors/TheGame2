package worldofzuul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Game
{

    private Parser parser;
    private Room currentRoom, recycler, upgradeStation;
    private Town town;
    private ArrayList<Item> itemsInRoom;

    private Player player1 = new Player();

    public Game()
    {
        createRooms();
        parser = new Parser();

    }

    private void createRooms()
    {
        Room abandonedVillage = new Lootable("in an abandoned villiage", "abandoned village");
        Room road = new Lootable("on a long road", "road");
        Room beach = new Lootable("on a beach", "beach");
        Room forrest = new Lootable("in a forrest", "forrest");

        recycler = new Recycler("at the recycler", "recycler");
        town = new Town("by the Town Hall", "town_hall");
        upgradeStation = new UpgradeStation("in the upgradestation", "upgrade station");

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

        if (null != commandWord)
        {
            switch (commandWord)
            {
                case HELP:
                    printHelp();
                    break;
                case GO:
                    goRoom(command);
                    break;
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                case PICKUP:
                    pickupItems(command);
                    break;
                case UPGRADE:
                    upgradeItems(command);
                    break;
                case RECYCLE:
                    recycleItems(command);
                    break;
                case INVENTORY:
                    openInventory(command);
                    break;
                default:
                    break;
            }
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
        
        if (currentRoom instanceof Recycler)
        {
            if (!command.hasSecondWord())
        {
            System.out.println("Recycle what?");
            return;
        }
            ((Recycler) currentRoom).reycleItem(command, recycler, player1);
            
                    
        } else 
        {
            System.out.println("You are not at the recycler room");
        }
    }

    private void upgradeItems(Command command)
    {
        
        if (currentRoom instanceof UpgradeStation)
        {
            if (!command.hasSecondWord())
            {
            System.out.println("Upgrade what?");
            return;
            }  
            ((UpgradeStation) currentRoom).upgradeObject(command, currentRoom, town, recycler, player1);
        } else 
        {
            System.out.println("You are not at the upgrade station");
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
                this.itemsInRoom = ((Lootable) nextRoom).getLoot();
                for (int i = 0; i < this.itemsInRoom.size(); i++)
                {
                    System.out.print(this.itemsInRoom.get(i).getName());
                    if ((i + 1) != this.itemsInRoom.size())
                    {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } else if (nextRoom instanceof Town)
            {
                System.out.println("The Town's happiness level: " + ((Town) nextRoom).getHappiness());
            } else if (nextRoom instanceof UpgradeStation)
            {
                ((UpgradeStation) upgradeStation).welcomeMessage(player1.getBackpackObj(), town, recycler);
            } else if (nextRoom instanceof Recycler)
            {
                System.out.println("You can recycle your trash here");
            }

        }
    }

    private void pickupItems(Command command)
    {
        if (command.hasSecondWord())
        {
            //checking if the player is in fact in a room that can be looted by using the lootable class and instanceof 
            if (currentRoom instanceof Lootable)
            {
                //Looping though all the items in room.
                for (int i = 0; i < this.itemsInRoom.size(); i++)
                {
                    //Checking that the item the player wants to pickup is in the room. 
                    if (this.itemsInRoom.get(i).getName().equalsIgnoreCase(command.getSecondWord()))
                    {
                        //Adds the item obj to the backpack
                        player1.getBackpackObj().addItem(this.itemsInRoom.get(i));
                        break; // breaks out of the loop if it finds a mathcing item
                    } else if (i == (this.itemsInRoom.size()) - 1)
                    {
                        //If there is no macthing item on the last run of the loop, then say there is no match
                        System.out.println("There is no " + command.getSecondWord() + " in this room");
                    }
                }
            } else
            {
                System.out.println("You are not in a room where you can pick anything up");
            }

        } else
        {
            System.out.println("Pick up what?");
        }
    }

    private void openInventory(Command command)
    {
        System.out.println("Coins: " + player1.getMoney());
        ArrayList<Item> temp = player1.getBackpackObj().getItemsInBackpack();
        if (temp.size() == 0)
        {
            System.out.println("Your backpack is currently empty");
        } else
        {
            System.out.print("Your inventory contains: ");
        }
        for (int i = 0; i < temp.size(); i++)
        {
            System.out.print(temp.get(i).getName());
            if (i + 1 != temp.size())
            {
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
