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
public class Recycler
{

    private int recyclerLevel;
    private int materialSortingLevel;
    private int[] sortingDegreeArray =
    {
        10, 30, 60, 100
    };
    private final int baseValue = 1;
    private int maxRecyclerLevel;
    private String[] materials;
    private HashMap<Integer, Integer> sortingProcent = new HashMap<>();
    private HashMap<String, Boolean> canSortMaterial = new HashMap<>();
    private HashMap<String, Integer> materialValues = new HashMap<>();

    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(HashMap<String, Integer> materialMap, String[] materials, int[] sortingProcent)
    {
        //Sets the level of the recylers 2 upgrades to 0 when first initilized
        this.recyclerLevel = 0;
        this.materialSortingLevel = 0;
        //assign the hashmap over the materials values to a variable in the recycler class
        this.materialValues = materialMap;
        //Loops though the game materials and set the the sorting ability to false, because we cant sort anything yet
        for (int i = 0; i < materials.length; i++)
        {
            this.canSortMaterial.put(materials[i], false);
        }
        for (int i = 0; i < sortingProcent.length; i++)
        {
            this.sortingProcent.put(i, sortingProcent[i]);
            maxRecyclerLevel = i; 
        }

    }

    //getting the level is used in the upgradestation class to know what level the recycler already is
    public int getRecyclerLevel()
    {
        return this.recyclerLevel;
    }

    //getting the level is used in the upgradestation class to know what level the recycler already is
    public int getMaterialSortingLevel()
    {
        return this.materialSortingLevel;
    }

    //Setting the level is used in the upgradestation class to change the level of the recycler
    public void setRecyclerLevel(int recyclerLevel)
    {
        this.recyclerLevel = recyclerLevel;
    }

    public int getMaxRecyclerLevel()
    {
        return maxRecyclerLevel;
    }
    

    public void setItemSortingLevel(int level)
    {
        this.materialSortingLevel = level;
        //Uses the sorting level to loop though the material lists and sets the value to be true, if the material is unlocked. 
        for (int i = 0; i < materialSortingLevel; i++)
        {
            this.canSortMaterial.replace(materials[i], true);
        }
    }

    public int materialValueAfterSort(String material)
    {
        //The statement looks up the value for the material thats passed in as the argument in the method.
        //The value in the hashmap will be true if the recycler is high enough level that it can sort the material
        //Else it will just use the basevalue
        int tempMaterialValue = 0; 
        if (this.canSortMaterial.get(material))
        {
            tempMaterialValue = materialValues.get(material);
        } else
        {
            tempMaterialValue = baseValue;
        }
        return tempMaterialValue;
    }

    public void valueCalculator(Item item)
    {
        int tempTotalValue = 0; 
        //Looping though all the materials in the item by getting the length of the array that holds the strings with materials
        //using the loop to call the method that returns the string based on the index you pass as argument
        for (int i = 0; i < item.getMaterialListLength(); i++)
        {
            tempTotalValue += materialValueAfterSort(item.getMaterial(i));
        }
        tempTotalValue = tempTotalValue * recyclerLevel;
        //rememeber to remove the item from the inventorylist
        System.out.print(tempTotalValue);
    }

}
