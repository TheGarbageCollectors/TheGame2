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
    private boolean[] canSortMaterial2 = {};
    private int value;
    private int materialValue;
    private final int baseValue = 1;
    static public HashMap<String, Boolean> canSortMaterial = new HashMap<>();
    
    Recycler(){
        this.sortingDegreeLevel = 0;
        this.itemSortingLevel = 0;
        for(int i = 0; i < Game.materials.length; i++){
            this.canSortMaterial.put(Game.materials[i], false);
        }
        
    }

    public void setSortingDegreeLevel(int sortingDegreeLevel)
    {
        this.sortingDegreeLevel = sortingDegreeLevel;
    }

    public void setItemSortingLevel(int level)
    {
        this.materialSortingLevel = level;
        //Uses the sorting level to loop though the material lists and sets the value to be true, if the material is unlocked. 
        for(int i = 0; i < materialSortingLevel; i++){
            this.canSortMaterial.replace(Game.materials[i], true);
        }
    }
    
    public int materialValueAfterSort(String material){
        if(this.canSortMaterial.get(material)){
            materialValue = Game.materialMap.get(material);
        }else{
            materialValue = baseValue;
        }
        return materialValue;
    }
    
    public void valueCalculator(Item item){
        
        for(int i = 0; i < item.getMaterialListLength; i++){
            value += materialValueAfterSort(item.getMaterial(i));
        }
   
        value = materialValue * sortingDegreeLevel;
        Inventory.removeItem(item);
        System.out.print(value);
    }
       
}  
