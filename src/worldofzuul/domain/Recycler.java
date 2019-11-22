/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.domain;

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
    private final int baseValue = 1;
    private int level;
    private HashMap<String, Boolean> canSortMaterial = new HashMap<>();
    private HashMap<String, Integer> materialValues = new HashMap<>();
    private double[] recyclingProcent ={0.1, 0.3, 0.6, 1.0};
    private int hp = 100;

    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(String dir, String name)
    {
        super(dir);
        this.name = name;
        //Sets the level of the recylers 2 upgrades to 0 when first initilized
        this.level = 1;
        //assign the hashmap over the materials values to a variable in the recycler class
        this.materialValues = GameItems.getMaterialMap();
        
        Object[] tempString = materialValues.keySet().toArray();
        
        //Loops though the game materials and set the the sorting ability to false, because we cant sort anything yet
        for (int i = 0; i < materialValues.size(); i++)
        {
            this.canSortMaterial.put((String)tempString[i], false);
        }

    }
    

    //getting the level is used in the upgradestation class to know what level the recycler already is
    @Override
    public int getLevel()
    {
        return this.level;
    }
    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public void upgradeLevel(){
        this.level++;
    }
    
    //Setting the level is used in the upgradestation class to change the level of the recycler


    private double materialValueAfterSort(String material)
    {
        //The statement looks up the value for the material thats passed in as the argument in the method.
        //The value in the hashmap will be true if the recycler is high enough level that it can sort the material
        //Else it will just use the basevalue
        int tempMaterialValue; 
        if (this.canSortMaterial.get(material))
        {
            tempMaterialValue = this.materialValues.get(material);
        } else
        {
            tempMaterialValue = this.baseValue;
        }
        
        return tempMaterialValue;
    }

    public int valueCalculator(Item item)
    {
        int tempTotalValue = 100; 
        String[] tempStringArray = item.getMaterialList();
        //Looping though all the materials in the item by getting the length of the array that holds the strings with materials
        //using the loop to call the method that returns the string based on the index you pass as argument
        for (int i = 0; i < tempStringArray.length ; i++)
        {
            tempTotalValue += materialValueAfterSort(tempStringArray[i]);
        }
        tempTotalValue = (int) (tempTotalValue * this.recyclingProcent[this.level]);
        //rememeber to remove the item from the inventorylist
        return tempTotalValue;
    }
    
    public void reycleItem(Command command, Room recycler, Player player1) 
    {
        int recyclerLevel = ((Upgradeable) recycler).getLevel();
            Scanner reader = new Scanner(System.in);
            String itemToBeRecycled = command.getSecondWord().toLowerCase();
            System.out.print("How do you wish to recycle your item? ");
            //Makeing an arraylist with arraylists that stores all the containers we can put stuff in
            ArrayList<ArrayList<String>> materialsThatCanSortes = new ArrayList<>();
            ArrayList<String> a1 = new ArrayList<>(Arrays.asList("metal", "plastic", "trash"));
            ArrayList<String> a2 = new ArrayList<>(Arrays.asList("metal", "plastic", "trash", "paper", "concrete"));
            ArrayList<String> a3 = new ArrayList<>(Arrays.asList("metal", "plastic", "trash", "paper", "concrete", "battery", "hazardous"));

            materialsThatCanSortes.add(a1);
            materialsThatCanSortes.add(a2);
            materialsThatCanSortes.add(a3);

            //prints out the containers that the user can recycle with. 
            printOutMaterials((recyclerLevel-1), materialsThatCanSortes);

            String input = reader.nextLine().toLowerCase();
            String[] itemMaterial;
            var itemsInBag = player1.getBackpackObj().getItemsInBackpack();
            for (int i = 0; i < itemsInBag.size(); i++)
            {
                if (itemsInBag.get(i).getName().equalsIgnoreCase(itemToBeRecycled))
                {
                    //Looper igennem backpack for at finde det item object som skal recycles
                    itemMaterial = itemsInBag.get(i).getMaterialList();
                    for(int j = 0; j < itemMaterial.length; j++)
                    {

                        //looper igennem materiallisten på det object som skal recycles.
                        if (input.equalsIgnoreCase(itemMaterial[j]) && materialsThatCanSortes.get(recyclerLevel).contains(input))
                        {
                            recycleItem(itemToBeRecycled, command, player1, recycler);
                        } else if (input.equalsIgnoreCase("trash") && i == (itemMaterial.length - 1))
                        {
                            //if its the last run of the loop and it hasnt matched anything else then we check if the user wrote trash. 
                            recycleItem(itemToBeRecycled, command, player1, recycler);
                        } else if (i == (itemMaterial.length - 1))
                        {
                            //if this is the last loop and input hasnt matched anything else, this is the default
                            removeItemWhenRecycled(command, player1);
                            //Method for removing recycler HP
                            System.out.println("This item doesn't belong here, it has been wasted");

                        }
                    }
                }
            }
    }
    private void recycleItem(String itemToBeRecycled, Command command, Player player1, Room currentRoom)
    {
        // looping though the backpack to check if the item that the player wants to recycle is in the backpack. 
        for(int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++)
        {
            if (itemToBeRecycled.equals(player1.getBackpackObj().getItemsInBackpack().get(i).getName()))
            {
                //Calculating the amount of money that the player gets for recycling the item. 
                var money = ((Recycler) currentRoom).valueCalculator(player1.getBackpackObj().getItemsInBackpack().get(i));
                player1.addMoney(money);
                break;
            }
        }
        removeItemWhenRecycled(command, player1);
        System.out.println("This item does belong here points awarded (:");
    }
   
    public void removeItemWhenRecycled(Command command, Player player1)
    {
        ArrayList<Item> temp = player1.getBackpackObj().getItemsInBackpack();
        for (Item e : temp)
        {
            System.out.print(e.getName() + " ");
        }

        for (int i = 0; i < player1.getBackpackObj().getItemsInBackpack().size(); i++)
        {

            if (player1.getBackpackObj().getItemsInBackpack().get(i).getName().equals(command.getSecondWord()))
            {
                player1.getBackpackObj().removeItem(player1.getBackpackObj().getItemsInBackpack().get(i));
                break;
            } else
            {
                System.out.println("This item is not in your backpack");
            }
        }
        System.out.println();
        for (Item e : temp)
        {
            System.out.print(e.getName() + " ");
        }
        System.out.println(" ");
    }
    
    private void printOutMaterials(int level, ArrayList<ArrayList<String>> materialsThatCanSortes)
    {
        System.out.println("");
        for(String i : materialsThatCanSortes.get(level))
        {
            System.out.print(i + " ");
        }
    }

}
