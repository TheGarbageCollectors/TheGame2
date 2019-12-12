/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import trashmaster.presentation.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author GamerQuvang
 */
public class Recycler extends Room implements Upgradeable
{

    private final String name;
    private int level;
    private HashMap<String, Integer> materialValues = new HashMap<>();
    private ArrayList<ArrayList<String>> materialesSortLevels = new ArrayList<>();
    private ArrayList<String> restaffaldArray = new ArrayList<>(Arrays.asList
        (
                "Plastikflaske", "Papkasse", "Batteri", "Dåse", "Sugerør", "Hjul", "Skraldepose", "Chipspose", "Badebold", "Juicekarton", "Tyggegummipapir", "Cykel"
                , "Toiletpapir", "Lighter", "Dør", "Mursten", "Rør"
        ));
    private ArrayList<String> plastikArray = new ArrayList<>(Arrays.asList("Plastikflaske", "Sugerør", "Chipspose", "Badebold"));
    private ArrayList<String> metalArray = new ArrayList<>(Arrays.asList("Hjul", "Cykel", "Rør", "Dåse", "Dør"));
    private ArrayList<String> betonArray = new ArrayList<>(Arrays.asList("Mursten"));
    private ArrayList<String> papirArray = new ArrayList<>(Arrays.asList("Papkasse", "Juicekarton", "Tyggegummipapir", "Toiletpapir"));
    private ArrayList<String> batteriArray = new ArrayList<>(Arrays.asList("Batteri"));
    private ArrayList<String> farligtaffaldArray = new ArrayList<>(Arrays.asList("Lighter"));
    
    private double[] recyclingProcent =
    {
        0.1, 0.3, 0.6, 1.0
    };
    private int hp = 3;

    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(String dir, String name)
    {
        super(dir);
        makeMaterialeMap();

        this.name = name;
        this.level = 1;

    }

    //getting the level is used in the upgradestation class to know what level the recycler already is
    @Override
    public int getLevel()
    {
        return this.level;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void upgradeLevel()
    {
        this.level++;
    }
    
    public int getHP() {
        return this.hp;
    }

    //Setting the level is used in the upgradestation class to change the level of the recycler
    private void makeMaterialeMap()
    {
        materialValues.put("trash", 5);
        materialValues.put("paper", 10);
        materialValues.put("beton", 20);
        materialValues.put("metal", 30);
        materialValues.put("plastic", 40);
    }

    public boolean recycleItems(String itemName, Player player1, String containerText, int inventoryIndex) {
        if (player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex).getName().contains(itemName)) {
            switch (containerText) {
                case "Restaffald":
                    if(restaffaldArray.contains(itemName)) {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                    }
                    
                    break;
                case "Metal": 
                    if(metalArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                case "Plastik":
                    if(plastikArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                       
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                        
                    }

                    break;
                case "Beton": 
                    if(betonArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                       
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                       
                        loseHP();
                    }

                    break;
                case "Papir":
                    if(papirArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                case "Batterier":
                    if(batteriArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                case "Farligt affald":
                    if(farligtaffaldArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                        }
        }
        return false;
    }

    private void recycleItem(Item item, Player player1, String containerText)
    {
        player1.addMoney(valueCalculator(containerText));
        removeItemWhenRecycled(item, player1);
    }

    /*
    private void recycleItem(String container, Item item, Player player1)
    {
        valueCalculator(container);
        removeItemWhenRecycled(item, player1);
    }*/

    private int valueCalculator(String containerText)
    {
        int tempTotalValue = 0;
        switch (containerText) {
            case "Restaffald":
                tempTotalValue = 0;
                break;
            case "Metal":
                tempTotalValue = 30 * this.level;
                break;
            case "Plastik":
                tempTotalValue = 20 * this.level; 
                break;
            case "Beton":
                tempTotalValue = 15 * this.level;
                break;
            case "Papir":
                tempTotalValue = 10 * this.level;
                break;
            case "Batterier":
                tempTotalValue = 30 * this.level;
                break;
            case "Farligt affald":
                tempTotalValue = 20 * this.level;
                break;
        }
        System.out.println("Money Add: " + tempTotalValue);
        return tempTotalValue;
    }

    private void removeItemWhenRecycled(Item item, Player player1)
    {
        player1.getBackpackObj().removeItem(item);
    }

    private void printOutMaterials(int level, ArrayList<ArrayList<String>> materialsThatCanSortes)
    {
        System.out.println("");
        for (String i : materialsThatCanSortes.get(level))
        {
            System.out.print(i + " ");
        }
    }
    
    private void loseHP() {
        this.hp -= 1;
    }
    
    /*
    String itemToBeRecycled = name;
        System.out.print("How do you wish to recycle your item? ");

        //prints out the containers that the user can recycle with. 
        printOutMaterials((level - 1), materialesSortLevels);

        String input = buttonText;
        String[] itemMaterial;

        var itemsInBag = player1.getBackpackObj().getItemsInBackpack();
        for (int i = 0; i < itemsInBag.size(); i++)
        {
            System.out.println(itemsInBag.get(i).getName());
            System.out.println(itemToBeRecycled);
            if (itemsInBag.get(i).getName().equals(itemToBeRecycled))
            {
                //Looper igennem backpack for at finde det item object som skal recycles
                itemMaterial = itemsInBag.get(i).getMaterialList();
                for (int j = 0; j < itemMaterial.length; j++)
                {
                    System.out.println(this.level);
                    if (materialesSortLevels.get(level).contains(itemMaterial[j]))
                    {
                        //looper igennem materiallisten på det object som skal recycles.
                        if (input.equalsIgnoreCase(itemMaterial[j]) && materialesSortLevels.get(level).contains(input))
                        {
                            recycleItem(itemsInBag.get(i), player1);
                            System.out.println("This item does belong here points awarded (:");

                        } else if (i == (itemMaterial.length - 1))
                        {
                            //if this is the last loop and input hasnt matched anything else, this is the default
                            removeItemWhenRecycled(itemsInBag.get(i), player1);

                            //Method for removing recycler HP
                            System.out.println("This item doesn't belong here, it has been wasted");
                        }
                    } else if (input.equalsIgnoreCase("trash") && i == (itemMaterial.length - 1))
                    {
                        //if its the last run of the loop and it hasnt matched anything else then we check if the user wrote trash. 
                        recycleItem("trash", itemsInBag.get(i), player1);
                    } else { 
                    }
                }
            } else
            {
                System.out.println("This item is not in your backpack");
            }
 }*/
}
