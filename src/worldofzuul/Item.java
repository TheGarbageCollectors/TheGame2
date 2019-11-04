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
    private final String[] materialList;
    
    public Item(String name, String[] materialList)
    {
        this.materialList = materialList;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public String getMaterial(int index)
    {
        return this.materialList[index];
    }
    
    public int getMaterialListLength(){
        return this.materialList.length;
    }
    
}
