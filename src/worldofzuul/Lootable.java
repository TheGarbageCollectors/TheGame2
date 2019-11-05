/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author GamerQuvang
 */
public class Lootable extends Room
{
    private String name;
    private ArrayList<Item> itemsInThisRoom;
    private final int maxNumberOfLoot = 3;
    private ArrayList<Item> randomItemList;
    
    Lootable (String dir, String name){
        super(dir);
        GameItems gameitems = new GameItems();
        this.name = name;
        this.itemsInThisRoom = gameitems.getLootList(name);
    }
    
     public void spawnLoot() {
        randomItemList = new ArrayList<Item>() ;
        int numOfItems =((int)(Math.random() * this.maxNumberOfLoot)+1); //Makes random int variable to decide the amount of Items in the finalList(max 3)
       
        for (int i = 0; i < numOfItems ; i++) { //for-loop to move Items from fullItemList to finalList
            Random rNum = new Random();

            this.randomItemList.add(this.itemsInThisRoom.get(rNum.nextInt(itemsInThisRoom.size()))); //adds the removed item to the finalList
            
        }
     }
    public ArrayList<Item> getLoot(){
        spawnLoot();
        return this.randomItemList;
    }

    public ArrayList<Item> getItemsInThisRoom()
    {
        return itemsInThisRoom;
    }
    
}
