/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author GamerQuvang
 */
import java.util.ArrayList;

public class GameItems
{

    private ArrayList<Item> items = new ArrayList<>();

    public GameItems()
    {
    }

    private void makeLootlists(String name)
    {
        //Using clear to remove everything from the arraylist if there is anything. 

        //initializes/makes a lot of Items
        Item plasticBottle = new Item("Plastic bottle", new String[]
        {
            "Plastic", "Beton"
        });
        Item cardBoard = new Item("cardBoarde", new String[]
        {
            "Pap"
        });
        Item battery = new Item("battery", new String[]
        {
            "Metal"
        });
        Item can = new Item("Can", new String[]
        {
            "Metal"
        });
        Item staw = new Item("Staw", new String[]
        {
            "Plastic"
        });

        //adds the Items to the commonList Array
        items.add(plasticBottle);
        items.add(cardBoard);
        items.add(battery);
        items.add(can);
        items.add(staw);

        switch (name)
        {
            case "Road":
                //makes Items specific for this class
                Item wheel = new Item("Car wheel", new String[]
                {
                    "Metal", "Rubber"
                });
                Item trashbag = new Item("Bag of Trash", new String[]
                {
                    "Paper", "Plastic", "Metal", "Rubber"
                });
                Item Chipsbag = new Item("Empty bag of Chips", new String[]
                {
                    "Plastic"
                });

                //adds the Items to the List
                items.add(wheel);
                items.add(trashbag);
                items.add(Chipsbag);
                break;
            case "Beach":
                items.add(plasticBottle);
                //makes Items specific for this class
                Item beachball = new Item("inflatable Beachball", new String[]
                {
                    "Plastic"
                });
                Item juicecarton = new Item("Juicarton", new String[]
                {
                    "Paper"
                });
                Item Gumpaper = new Item("Gum paper", new String[]
                {
                    "Plastic"
                });

                //adds the Items to the List
                items.add(beachball);
                items.add(juicecarton);
                items.add(Gumpaper);
                break;
            case "Forrest":
                //makes Items specific for this class
                Item bicycle = new Item("bicycle", new String[]
                {
                    "Metal", "Plastic"
                });
                Item toiletpaper = new Item("toiletpaper", new String[]
                {
                    "Paper"
                });
                Item lighter = new Item("lighter", new String[]
                {
                    "Metal", "Plastic"
                });

                //adds the Items to the List
                items.add(bicycle);
                items.add(toiletpaper);
                items.add(lighter);
                break;
            case "AbandonedVilliage":
                //makes Items specific for this class
                Item doorFrame = new Item("DoorFrame", new String[]
                {
                    "Metal", "Beton"
                });
                Item bricks = new Item("Bricks", new String[]
                {
                    "Paper", "Plastic", "Metal", "Rubber"
                });
                Item pipes = new Item("Rusty Pipes", new String[]
                {
                    "Metal"
                });

                //adds the Items to the List
                items.add(doorFrame);
                items.add(bricks);
                items.add(pipes);
                break;
        }
    }

    public ArrayList getLootList(String name)
    {
        makeLootlists(name);
        return this.items;
    }

}
