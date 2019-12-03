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
    private ArrayList<String> a1 = new ArrayList<>(Arrays.asList("metal", "plastic", "trash"));
    private ArrayList<String> a2 = new ArrayList<>(Arrays.asList("metal", "plastic", "trash", "paper", "concrete"));
    private ArrayList<String> a3 = new ArrayList<>(Arrays.asList("metal", "plastic", "trash", "paper", "concrete", "battery", "hazardous"));
    private double[] recyclingProcent =
    {
        0.1, 0.3, 0.6, 1.0
    };
    private int hp = 100;

    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(String dir, String name)
    {
        super(dir);
        makeMaterialeMap();

        this.name = name;
        this.level = 1;

        //Makeing an arraylist with arraylists that stores all the containers we can put stuff in
        materialesSortLevels.add(a1);
        materialesSortLevels.add(a2);
        materialesSortLevels.add(a3);
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

    //Setting the level is used in the upgradestation class to change the level of the recycler
    private void makeMaterialeMap()
    {
        materialValues.put("trash", 5);
        materialValues.put("paper", 10);
        materialValues.put("beton", 20);
        materialValues.put("metal", 30);
        materialValues.put("plastic", 40);
    }

    public void reycleItems(Command command, Player player1)
    {

        Scanner reader = new Scanner(System.in);
        String itemToBeRecycled = command.getSecondWord().toLowerCase();
        System.out.print("How do you wish to recycle your item? ");

        //prints out the containers that the user can recycle with. 
        printOutMaterials((level - 1), materialesSortLevels);

        String input = reader.nextLine().toLowerCase();
        String[] itemMaterial;

        var itemsInBag = player1.getBackpackObj().getItemsInBackpack();
        for (int i = 0; i < itemsInBag.size(); i++)
        {
            if (itemsInBag.get(i).getName().equalsIgnoreCase(itemToBeRecycled))
            {
                //Looper igennem backpack for at finde det item object som skal recycles
                itemMaterial = itemsInBag.get(i).getMaterialList();
                for (int j = 0; j < itemMaterial.length; j++)
                {
                    if (!materialesSortLevels.get(level).contains(itemMaterial[j]))
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
                    }
                }
            } else
            {
                System.out.println("This item is not in your backpack");
            }
        }
    }

    private void recycleItem(Item item, Player player1)
    {
        player1.addMoney(valueCalculator(item.getName()));
        System.out.println("Money added");
        removeItemWhenRecycled(item, player1);
        System.out.println("Item removed");
    }

    private void recycleItem(String container, Item item, Player player1)
    {
        valueCalculator(container);
        removeItemWhenRecycled(item, player1);
    }

    private int valueCalculator(String itemName)
    {
        int tempTotalValue;
        tempTotalValue = materialValues.get(itemName);
        tempTotalValue = (int) (tempTotalValue * this.recyclingProcent[this.level]);
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
}
