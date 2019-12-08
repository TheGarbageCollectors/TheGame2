package domain;

import trashmaster.presentation.Command;
import trashmaster.presentation.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
//import java.util.Scanner;
import javafx.scene.control.Button;

public class Game
{

    private Room currentRoom, recycler, upgradeStation;
    private Town town;
    private ArrayList<Item> itemsInRoom;
    private Player player1;

    

    public Game(String playerName)
    {
        createRooms();
        player1 = new Player(playerName);
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
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


    public String recycleItems(Command command)
    {
        var tempString = "";
        if (currentRoom instanceof Recycler)
        {
            if (!command.hasSecondWord())
            {
                tempString = ("Recycle what?");

            } else
            {
                ((Recycler) currentRoom).reycleItems(command, player1);
                tempString = ("Recycled " + command.getSecondWord());
            }

        } else
        {
            tempString = ("You are not at the recycler room");
        }
        return tempString;
    }


    public String upgradeItems(Command command)
    {
        var tempString = "";
        if (currentRoom instanceof UpgradeStation)
        {
            if (!command.hasSecondWord())
            {
                tempString = ("Upgrade what?");

            } else
            {
                ((UpgradeStation) currentRoom).upgradeObject(command, currentRoom, town, recycler, player1);
                tempString = ("Upgaded " + command.getSecondWord());

            }

        } else
        {
            tempString = ("You are not at the upgrade station");

        }
        return tempString;

    }


    public String goRoom(Command command)
    {
        var tempString = "";
        if (!command.hasSecondWord())
        {
            tempString = ("Go where?");
            return tempString;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            tempString = ("There is no door!");
        } else
        {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            if (nextRoom instanceof Lootable)
            {
                tempString += ("You see ");
                this.itemsInRoom = ((Lootable) nextRoom).getLoot();
                for (int i = 0; i < this.itemsInRoom.size(); i++)
                {
                    tempString += (this.itemsInRoom.get(i).getName());
                    if ((i + 1) != this.itemsInRoom.size())
                    {
                        tempString += (", ");
                    }
                }

            } else if (nextRoom instanceof Town)
            {
                tempString += ("The Town's happiness level: " + ((Town) nextRoom).getHappiness());
            } else if (nextRoom instanceof UpgradeStation)
            {
                ((UpgradeStation) upgradeStation).welcomeMessage(player1.getBackpackObj(), town, recycler);
            } else if (nextRoom instanceof Recycler)
            {
                tempString += ("You can recycle your trash here");
            }

        }
        return tempString;
    }


    public String pickupItems(Command command)
    {
        var tempString = "";
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
                        tempString = ("There is no " + command.getSecondWord() + " in this room");
                    }
                }
            } else
            {
                tempString = ("You are not in a room where you can pick anything up");
            }

        } else
        {
            tempString = ("Pick up what?");
        }
        return tempString;
    }


    public String getInventory(Command command)
    {
        var tempString = "";
        var tempString1 = "";
        tempString1 += ("Coins: " + player1.getMoney());

        ArrayList<Item> temp = player1.getBackpackObj().getItemsInBackpack();
        if (temp.size() == 0)
        {
            tempString = (" Your backpack is currently empty");
            return tempString1 + tempString;
        } else
        {

            for (int i = 0; i < temp.size(); i++)
            {
                tempString += temp.get(i).getName();
                if (i + 1 != temp.size())
                {
                    tempString += (", ");
                }
            }
            return tempString;
        }

    }


}
