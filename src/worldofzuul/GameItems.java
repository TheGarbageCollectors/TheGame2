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
public class GameItems
{
    Item plasticBottle = new Item("Plastik Flaske", new String[]{"Plastik"});
        Item cardBoard = new Item("Papkasse", new String[]{"Pap"});
        Item battery = new Item("Batteri", new String[]{"Metal"});
        Item concrete = new Item("Beton", new String[]{"Beton"});
        
        if (room.getName()=="Vej")
        {   
                items.add(plasticBottle);
                items.add(cardBoard);
        } else if (room.getName()=="Strand") {
            items.add(plasticBottle);
        } else if (room.getName()=="Skov") {
            items.add(plasticBottle);
        } else if (room.getName()=="Forladt by") {
            items.add(concrete);
        } else {
            throw new UnsupportedOperationException("The room with name: " + 
                    room.getName() + " has not been implemented");
        }
    }
        
        public ArrayList getLootList(String name)
        {
            return this.items;
        }
    
}
