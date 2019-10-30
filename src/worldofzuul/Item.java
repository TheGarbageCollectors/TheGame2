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
public class Item
{
    private String name;
    private int id;
    private ArrayList<String> materialList = new ArrayList<String>();

    public Item(String name, int id, ArrayList<String> materialList)
    {
        this.name = name;
        this.id = id;
        this.materialList = materialList;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public ArrayList<String> getMaterialList()
    {
        return materialList;
    }
    
    public int getMaterialListLength(){
        return this.materialList.size();
    }
    
}
