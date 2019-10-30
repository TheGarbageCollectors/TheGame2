/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author GamerQuvang
 */
public class Item
{
    private final String name;
    private int id;
    private String[] materialList;

    public Item()
    {
    }

    public Item(String name, int id, String[] materialList)
    {
        this.materialList = materialList;
        this.name = name;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public String getMaterial(int index)
    {
        return materialList[index];
    }
    
    public int getMaterialListLength(){
        return this.materialList.length;
    }
    
}
