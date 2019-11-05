/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.HashMap; 

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

    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(String dir, String name, HashMap<String, Integer> materialMap)
    {
        super(dir);
        this.name = name;
        //Sets the level of the recylers 2 upgrades to 0 when first initilized
        this.level = 0;
        //assign the hashmap over the materials values to a variable in the recycler class
        this.materialValues = materialMap;
        
        Object[] tempString = materialMap.keySet().toArray();
        
        //Loops though the game materials and set the the sorting ability to false, because we cant sort anything yet
        for (int i = 0; i < materialMap.size(); i++)
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
        int tempMaterialValue = 0; 
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
        int tempTotalValue = 0; 
        //Looping though all the materials in the item by getting the length of the array that holds the strings with materials
        //using the loop to call the method that returns the string based on the index you pass as argument
        for (int i = 0; i < item.getMaterialListLength(); i++)
        {
            tempTotalValue += materialValueAfterSort(item.getMaterial(i));
        }
        tempTotalValue = (int) (tempTotalValue * this.recyclingProcent[this.level]);
        //rememeber to remove the item from the inventorylist
        return tempTotalValue;
    }

}
