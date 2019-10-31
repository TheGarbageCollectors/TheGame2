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
public class Town implements Upgradeable
{
    private String name = "Town";
    private int populationCount; 
    private int townLevel;

    public Town(String name)
    {
        this.name = name;
        this.populationCount = 1;
        this.townLevel = 0;
    }
    
    @Override
    public int getLevel()
    {
        return this.townLevel;
    }
    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public void upgradeLevel(){
        this.townLevel++;
    }
    
    public void updatePopulationCount(int amount){
        this.populationCount = amount;
    }
    
    public double makeMoney(){
        //Will get called in game so it makes money everytime the game loop. 
        return 0.1 * populationCount * townLevel;
    }
}
