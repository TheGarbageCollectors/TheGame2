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
    private int sortingDegreeLevel;
    private int[] sortingDegreeArray = {10, 30, 60, 100};
    private int materialSortingLevel;
    private int totalValue;
    private int materialValue;
    private final int baseValue = 1;
    private String[] materials;
    private HashMap<String, Boolean> canSortMaterial = new HashMap<>();
    private HashMap<String, Integer> materialValues = new HashMap<>();
    
    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(HashMap<String, Integer> materialMap, String[] materials){
        //Sets the level of the recylers 2 upgrades to 0 when first initilized
        this.sortingDegreeLevel = 0;
        this.materialSortingLevel = 0;
        //assign the hashmap over the materials values to a variable in the recycler class
        this.materialValues = materialMap;
        //Loops though the game materials and set the the sorting ability to false, because we cant sort anything yet
        for(int i = 0; i < materials.length; i++){
            this.canSortMaterial.put(materials[i], false);
        }
        
    }
    //getting the level is used in the upgradestation class to know what level the recycler already is
    public int getSortingDegreeLevel()
    {
        return sortingDegreeLevel;
    }
    //getting the level is used in the upgradestation class to know what level the recycler already is
    public int getMaterialSortingLevel()
    {
        return materialSortingLevel;
    }
    //Setting the level is used in the upgradestation class to change the level of the recycler
    public void setSortingDegreeLevel(int sortingDegreeLevel)
    {
        this.sortingDegreeLevel = sortingDegreeLevel;
    }

    public void setItemSortingLevel(int level)
    {
        this.materialSortingLevel = level;
        //Uses the sorting level to loop though the material lists and sets the value to be true, if the material is unlocked. 
        for(int i = 0; i < materialSortingLevel; i++){
            this.canSortMaterial.replace(materials[i], true);
        }
    }
    
    public int materialValueAfterSort(String material){
        //The statement looks up the value for the material thats passed in as the argument in the method.
        //The value in the hashmap will be true if the recycler is high enough level that it can sort the material
        //Else it will just use the basevalue
        if(this.canSortMaterial.get(material)){
            materialValue = materialValues.get(material);
        }else{
            materialValue = baseValue;
        }
        return materialValue;
    }
    
    public void valueCalculator(Item item){
        //Looping though all the materials in the item by getting the length of the array that holds the strings with materials
        //using the loop to call the method that returns the string based on the index you pass as argument
        for(int i = 0; item.getMaterialListLength() >= i; i++){
            totalValue += materialValueAfterSort(item.getMaterial(i));
        }
        totalValue = materialValue * sortingDegreeLevel;
        //rememeber to remove the item from the inventorylist
        System.out.print(totalValue);
    }
       
}  
