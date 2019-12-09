/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author GamerQuvang
 */
import java.util.ArrayList;
import java.util.HashMap;

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
        Item plasticBottle = new Item("Plastikflaske", new String[]
        {
            "plastic"
        });
        Item cardBoard = new Item("Papkasse", new String[]
        {
            "paper"
        });
        Item battery = new Item("Batteri", new String[]
        {
            "metal"
        });
        Item can = new Item("Dåse", new String[]
        {
            "metal"
        });
        Item staw = new Item("Sugerør", new String[]
        {
            "plastic"
        });

        //adds the Items to the commonList Array
        items.add(plasticBottle);
        items.add(cardBoard);
        items.add(battery);
        items.add(can);
        items.add(staw);

        switch (name)
        {
            case "road":
                //makes Items specific for this class
                Item wheel = new Item("Hjul", new String[]
                {
                    "metal", "plastic"
                });
                Item trashbag = new Item("Skraldepose", new String[]
                {
                    "paper", "plastic", "metal"
                });
                Item Chipsbag = new Item("Chipspose", new String[]
                {
                    "plastic"
                });

                //adds the Items to the List
                items.add(wheel);
                items.add(trashbag);
                items.add(Chipsbag);
                break;
            case "beach":
                //makes Items specific for this class
                Item beachball = new Item("Badebold", new String[]
                {
                    "plastic"
                });
                Item juicecarton = new Item("Juicekarton", new String[]
                {
                    "paper"
                });
                Item Gumpaper = new Item("Tyggegummipapir", new String[]
                {
                    "plastic"
                });

                //adds the Items to the List
                items.add(beachball);
                items.add(juicecarton);
                items.add(Gumpaper);
                break;
            case "forrest":
                //makes Items specific for this class
                Item bicycle = new Item("Cykel", new String[]
                {
                    "metal", "plastic"
                });
                Item toiletpaper = new Item("Toiletpapir", new String[]
                {
                    "paper"
                });
                Item lighter = new Item("Lighter", new String[]
                {
                    "metal", "plastic"
                });

                //adds the Items to the List
                items.add(bicycle);
                items.add(toiletpaper);
                items.add(lighter);
                break;
            case "Abandoned Village":
                //makes Items specific for this class
                Item doorFrame = new Item("Dør", new String[]
                {
                    "metal", "beton"
                });
                Item bricks = new Item("Mursten", new String[]
                {
                    "concrete"
                });
                Item pipes = new Item("Rør", new String[]
                {
                    "metal"
                });

                //adds the Items to the List
                items.add(doorFrame);
                items.add(bricks);
                items.add(pipes);
                break;
        }
    }

    public ArrayList<Item> getLootList(String name)
    {
        makeLootlists(name);
        return this.items;
    }

}
