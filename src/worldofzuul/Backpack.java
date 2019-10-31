/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;
import java.util.ArrayList;
/**
 *
 * @author GamerQuvang
 */
class Backpack implements Upgradeable
{
    private String name = "Backpack";
    private int level;
    private ArrayList<Item> inventoryList = new ArrayList<Item>();

    public Backpack()
    {
        
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
    }
    
    public void addItem(Item item){
        this.inventoryList.add(item);
    }
    public void removeItem(Item item){
        this.inventoryList.remove(item);
    }
    public void useItem(){
        //Write code here
    }

    public int getinventoryListSize (){
        return this.inventoryList.size();
    }
}