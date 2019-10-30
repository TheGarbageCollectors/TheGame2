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
class Inventory
{
    private ArrayList<Item> inventoryList = new ArrayList<Item>();

    public Inventory()
    {
        
    }
    public void addItem(Item item){
        this.inventoryList.add(item);
    }
    
    public int getinventoryListSize (){
        return this.inventoryList.size();
    }
}
