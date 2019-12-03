/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.HashMap;

/**
 *
 * @author GamerQuvang
 */
public class Town extends Room implements Upgradeable
{
    private String name;
    private int Happiness; 
    private int level;

    public Town(String dir, String name)
    {
        super(dir);
        this.name = name;
        this.Happiness = 0;
        this.level = 1;
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
    
    public void increaseHappiness(int amount){
        this.Happiness += amount;
    }

    public int getHappiness() {
        return Happiness;
    }
    
}
