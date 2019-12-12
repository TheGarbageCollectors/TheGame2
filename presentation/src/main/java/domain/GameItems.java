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
        Item plasticBottle = new Item("Plastikflaske");
        Item cardBoard = new Item("Papkasse");
        Item battery = new Item("Batteri");
        Item can = new Item("Dåse");
        Item staw = new Item("Sugerør");

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
                Item wheel = new Item("Hjul");
                Item trashbag = new Item("Skraldepose");
                Item Chipsbag = new Item("Chipspose");

                //adds the Items to the List
                items.add(wheel);
                items.add(trashbag);
                items.add(Chipsbag);
                break;
            case "beach":
                //makes Items specific for this class
                Item beachball = new Item("Badebold");
                Item juicecarton = new Item("Juicekarton");
                Item Gumpaper = new Item("Tyggegummipapir");

                //adds the Items to the List
                items.add(beachball);
                items.add(juicecarton);
                items.add(Gumpaper);
                break;
            case "forrest":
                //makes Items specific for this class
                Item bicycle = new Item("Cykel");
                Item toiletpaper = new Item("Toiletpapir");
                Item lighter = new Item("Lighter");

                //adds the Items to the List
                items.add(bicycle);
                items.add(toiletpaper);
                items.add(lighter);
                break;
            case "abandoned_village":
                //makes Items specific for this class
                Item doorFrame = new Item("Dør");
                Item bricks = new Item("Mursten");
                Item pipes = new Item("Rør");

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
