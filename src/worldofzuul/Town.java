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
public class Town extends Room implements Upgradeable
{
    private String name;
    private int populationCount; 
    private int level;

    public Town(String dir, String name)
    {
        super(dir);
        this.name = name;
        this.populationCount = 1;
        this.level = 0;
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
    
    public void updatePopulationCount(int amount){
        this.populationCount = amount;
    }
    
    public double makeMoney(){
        //Will get called in game so it makes money everytime the game loop. 
        return 0.1 * populationCount * level;
    }
}
