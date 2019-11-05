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
public class Lootable
{
    private String name;
    private ArrayList<Item> items;
    private Item item;
    
    Lootable (Item item, String name){
        this.name = name;
        this.item = item;
        this.items = new ArrayList<Item>();  
    }
    
    private void spawnLoot(GameItems obj){
        obj.getLootList(name);
    }
    public ArrayList<Item> getLoot(GameItems obj){
        spawnLoot(obj);
        return items;
    }
}
