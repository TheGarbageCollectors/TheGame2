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
    private int itemSortingLevel;
    private boolean[] canSortMaterial2 = {};
    private int value;
    private int materialValue;
    private final int baseValue = 1;
    public HashMap<String, Boolean> canSortMaterial = new HashMap<>();
    
    Recycler(){
        this.sortingDegreeLevel = 0;
        this.itemSortingLevel = 0;
        for(int material = 0; material < Game.materials.length; material++){
            this.canSortMaterial.put(Game.materials[i], false);
        }
        
    }

    public void setSortingDegreeLevel(int sortingDegreeLevel)
    {
        this.sortingDegreeLevel = sortingDegreeLevel;
    }

    public void setItemSortingLevel(int itemSortingLevel)
    {
        this.itemSortingLevel = itemSortingLevel;
        switch(itemSortingLevel){
            case 0: 
            {
                
            }
        }
    }
    
    public void valueCalculator(){
        
        //value will be calcualted based on the value of each material. 
        
        //First for loop gets the players inventorylist and loops though all the items. 
        for(int i = 0; i < Inventory.getinventoryListSize(); i++){
            //Vi får en dør
            //2nd for loops loops though the levels of sorting that has been unlocked and checks if the item has any of those materials
            for(int materialNumber = 0; materialNumber < Item.getMaterialListLength() ; materialNumber++){
               //vi kan se at d'ren har 3 materials s[ loopet vil k're 3 gange
               // f;rste er metal
                for(int j = 0; j < itemSortingLevel ; j++){
                    //Vi
                    if(Item.getMaterial(materialNumber).Material.getName() == itemSortingArray[j]{
                        //If it has then it will use the value that the material have
                        materialValue = Item.getMaterial(materialNumber).getValue();
                    }else{
                        //Else it will use the basevalue since the materials are just burnt. 
                        materialValue = baseValue;
                    }
                    value += materialValue;

                }
            }
            value = materialValue * sortingDegreeLevel;
            Inventory.removeItem(i);
            Player.addMoney(value);
        }
       
    }
    public int getSortingDegreeLevel()
    {
        return sortingDegreeLevel;
    }

    public int getItemSortingLevel()
    {
        return itemSortingLevel;
    }
    
}
