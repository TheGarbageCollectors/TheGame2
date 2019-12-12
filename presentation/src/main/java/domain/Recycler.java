/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
/**
 *
 * @author GamerQuvang
 */
public class Recycler extends Room implements Upgradeable
{

    private final String name;
    private int level;
    private ArrayList<String> restaffaldArray = new ArrayList<>(Arrays.asList
        (
                "Plastikflaske", "Papkasse", "Batteri", "Dåse", "Sugerør", "Hjul", "Skraldepose", "Chipspose", "Badebold", "Juicekarton", "Tyggegummipapir", "Cykel"
                , "Toiletpapir", "Lighter", "Dør", "Mursten", "Rør"
        ));
    private ArrayList<String> plastikArray = new ArrayList<>(Arrays.asList("Plastikflaske", "Sugerør", "Chipspose", "Badebold"));
    private ArrayList<String> metalArray = new ArrayList<>(Arrays.asList("Hjul", "Cykel", "Rør", "Dåse", "Dør"));
    private ArrayList<String> betonArray = new ArrayList<>(Arrays.asList("Mursten"));
    private ArrayList<String> papirArray = new ArrayList<>(Arrays.asList("Papkasse", "Juicekarton", "Tyggegummipapir", "Toiletpapir"));
    private ArrayList<String> batteriArray = new ArrayList<>(Arrays.asList("Batteri"));
    private ArrayList<String> farligtaffaldArray = new ArrayList<>(Arrays.asList("Lighter"));
    private int hp = 3;

    //construtor gets a hashmap over the games materials and a String array med samme. 
    Recycler(String dir, String name)
    {
        super(dir);

        this.name = name;
        this.level = 1;

    }

    //getting the level is used in the upgradestation class to know what level the recycler already is
    @Override
    public int getLevel()
    {
        return this.level;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void upgradeLevel()
    {
        this.level++;
    }
    
    public int getHP() {
        return this.hp;
    }

    public boolean recycleItems(String itemName, Player player1, String containerText, int inventoryIndex) {
        if (player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex).getName().contains(itemName)) {
            switch (containerText) {
                case "Restaffald":
                    if(restaffaldArray.contains(itemName)) {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                    }
                    
                    break;
                case "Metal": 
                    if(metalArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                case "Plastik":
                    if(plastikArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                       
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                        
                    }

                    break;
                case "Beton": 
                    if(betonArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                       
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                       
                        loseHP();
                    }

                    break;
                case "Papir":
                    if(papirArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                case "Batterier":
                    if(batteriArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                case "Farligt affald":
                    if(farligtaffaldArray.contains(itemName)) {
                        recycleItem(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1, containerText);
                        
                        return true;
                    } else {
                        removeItemWhenRecycled(player1.getBackpackObj().getItemsInBackpack().get(inventoryIndex), player1);
                        
                        loseHP();
                    }

                    break;
                        }
        }
        return false;
    }

    private void recycleItem(Item item, Player player1, String containerText)
    {
        player1.addMoney(valueCalculator(containerText));
        removeItemWhenRecycled(item, player1);
    }

    private int valueCalculator(String containerText)
    {
        int tempTotalValue = 0;
        switch (containerText) {
            case "Restaffald":
                tempTotalValue = 0;
                break;
            case "Metal":
                tempTotalValue = 30 * this.level;
                break;
            case "Plastik":
                tempTotalValue = 20 * this.level; 
                break;
            case "Beton":
                tempTotalValue = 15 * this.level;
                break;
            case "Papir":
                tempTotalValue = 10 * this.level;
                break;
            case "Batterier":
                tempTotalValue = 30 * this.level;
                break;
            case "Farligt affald":
                tempTotalValue = 20 * this.level;
                break;
        }
        System.out.println("Money Add: " + tempTotalValue);
        return tempTotalValue;
    }

    private void removeItemWhenRecycled(Item item, Player player1)
    {
        player1.getBackpackObj().removeItem(item);
    }
    
    private void loseHP() {
        this.hp -= 1;
    }
}
