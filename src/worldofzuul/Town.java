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
public class Town
{
    private String name;
    private int populationCount; 
    private int townLevel;
    private HashMap<Integer, Integer> townLevelPrice = new HashMap<>();

    public Town(String name)
    {
        this.name = name;
        this.populationCount = 1;
        this.townLevel = 0;
        setUpgradePrice();
    }
    private void setUpgradePrice(){
        townLevelPrice.put(0, 0);
        townLevelPrice.put(1, 100);
        townLevelPrice.put(2, 1000);
        townLevelPrice.put(3, 10000);
        townLevelPrice.put(4, 1000000);
    }
    public int getUpgradePrice(){
        return townLevelPrice.get(townLevel);
    }
    
    public void updateLevel(int level){
        this.townLevel = level;
    }
    public void updatePopulationCount(int amount){
        this.populationCount = amount;
    }
    
    public double makeMoney(){
        //Will get called in game so it makes money everytime the game loop. 
        return 0.1 * populationCount * townLevel;
    }
}
