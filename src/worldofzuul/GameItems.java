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
import java.util.HashMap;

public class GameItems
{

    private ArrayList<Item> items = new ArrayList<>();
    static private HashMap<String, Integer> materialMap = new HashMap<>();
    private final String[] materials =
    {
        "paper", "metal", "beton", "plastic"
    };
    
    public GameItems()
    {
        materialMap.put("paper", 10);
        materialMap.put("beton", 20);
        materialMap.put("metal", 30);
        materialMap.put("plastic", 40);
    }

    public static HashMap<String, Integer> getMaterialMap()
    {
        return materialMap;
    }
    

    private void makeLootlists(String name)
    {
        //Using clear to remove everything from the arraylist if there is anything. 

        //initializes/makes a lot of Items
        Item plasticBottle = new Item("bottle", new String[]
        {
            "plastic"
        });
        Item cardBoard = new Item("box", new String[]
        {
            "paper"
        });
        Item battery = new Item("battery", new String[]
        {
            "metal"
        });
        Item can = new Item("can", new String[]
        {
            "metal"
        });
        Item staw = new Item("straw", new String[]
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
            case "Road":
                //makes Items specific for this class
                Item wheel = new Item("wheel", new String[]
                {
                    "metal", "plastic"
                });
                Item trashbag = new Item("trash", new String[]
                {
                    "paper", "plastic", "metal"
                });
                Item Chipsbag = new Item("bag", new String[]
                {
                    "plastic"
                });

                //adds the Items to the List
                items.add(wheel);
                items.add(trashbag);
                items.add(Chipsbag);
                break;
            case "Beach":
                //makes Items specific for this class
                Item beachball = new Item("beachball", new String[]
                {
                    "plastic"
                });
                Item juicecarton = new Item("carton", new String[]
                {
                    "paper"
                });
                Item Gumpaper = new Item("gumpaper", new String[]
                {
                    "plastic"
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
                    "metal", "plastic"
                });
                Item toiletpaper = new Item("toiletpaper", new String[]
                {
                    "paper"
                });
                Item lighter = new Item("lighter", new String[]
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
                Item doorFrame = new Item("door", new String[]
                {
                    "metal", "beton"
                });
                Item bricks = new Item("bricks", new String[]
                {
                    "concrete"
                });
                Item pipes = new Item("pipes", new String[]
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
