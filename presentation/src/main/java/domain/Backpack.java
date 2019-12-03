/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.util.ArrayList;
/**
 *
 * @author GamerQuvang
 */
class Backpack implements Upgradeable
{
    private String name = "Backpack";
    private int level;
    private ArrayList<Item> inventoryList = new ArrayList<>();
    private int maxSize;

    public Backpack()
    {
        this.level = 1;
        this.maxSize = 2;
    }
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
        this.maxSize *= 2;
    }
    
        public int getMaxSize(){
        return this.maxSize;
    }
    
    public void addItem(Item item) {
        if (this.inventoryList.size() < this.maxSize) {
            this.inventoryList.add(item);
            System.out.println(" Added " + item.getName());
        } else {
            System.out.println("Your inventory is FULL! You cannot PICKUP anymore items.");
            System.out.println("Go to the recycler and recycle your items.");
        }
    }
    public void removeItem(Item item){
        this.inventoryList.remove(item);
    }
    public void useItem(){
        //Write code here
    }

    public ArrayList<Item> getItemsInBackpack (){
        return this.inventoryList;
    }
}
