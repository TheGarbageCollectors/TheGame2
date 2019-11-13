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
        "Paper", "Metal", "Beton", "Plastic"
    };
    
    public GameItems()
    {
        materialMap.put("Paper", 10);
        materialMap.put("Beton", 20);
        materialMap.put("Metal", 30);
        materialMap.put("Plastic", 40);
    }

    public static HashMap<String, Integer> getMaterialMap()
    {
        return materialMap;
    }
    

    private void makeLootlists(String name)
    {
        //Using clear to remove everything from the arraylist if there is anything. 

        //initializes/makes a lot of Items
        Item plasticBottle = new Item("Bottle", new String[]
        {
            "Plastic"
        });
        Item cardBoard = new Item("Box", new String[]
        {
            "Paper"
        });
        Item battery = new Item("Battery", new String[]
        {
            "Metal"
        });
        Item can = new Item("Can", new String[]
        {
            "Metal"
        });
        Item staw = new Item("Straw", new String[]
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
                Item wheel = new Item("Wheel", new String[]
                {
                    "Metal", "Plastic"
                });
                Item trashbag = new Item("Trash", new String[]
                {
                    "Paper", "Plastic", "Metal"
                });
                Item Chipsbag = new Item("Bag", new String[]
                {
                    "Plastic"
                });

                //adds the Items to the List
                items.add(wheel);
                items.add(trashbag);
                items.add(Chipsbag);
                break;
            case "Beach":
                //makes Items specific for this class
                Item beachball = new Item("Beachball", new String[]
                {
                    "Plastic"
                });
                Item juicecarton = new Item("Carton", new String[]
                {
                    "Paper"
                });
                Item Gumpaper = new Item("GumPaper", new String[]
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
                Item bicycle = new Item("rusty bicycle", new String[]
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
            case "Abandoned Village":
                //makes Items specific for this class
                Item doorFrame = new Item("Door", new String[]
                {
                    "Metal", "Beton"
                });
                Item bricks = new Item("Bricks", new String[]
                {
                    "Concrete"
                });
                Item pipes = new Item("Pipes", new String[]
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

    public ArrayList<Item> getLootList(String name)
    {
        makeLootlists(name);
        return this.items;
    }

}
