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
public final class GameItems
{
    
        private ArrayList<Item> items = new ArrayList<>();
        Item plasticBottle = new Item("Plastik Flaske", new String[]{"Plastik"});
        Item cardBoard = new Item("Papkasse", new String[]{"Pap"});
        Item battery = new Item("Batteri", new String[]{"Metal"});
        Item concrete = new Item("Beton", new String[]{"Beton"});
    
    private GameItems();
        
    private void makeLootlists(String name){
        
        switch(name){
                case "Vej":
                    items.add(plasticBottle
                    items.add(cardBoard);
                    break;
                case "Strand":
                    items.add(plasticBottle);
                     break;
                case "Skov:
                       items.add(plasticBottle);
                       break;
                case "Forladt by
                       items.add(concrete);
                       break;
        }
    }
        
        public ArrayList getLootList(String name)
        {
            makeLootlists(name);
            return this.items;
        }
    
}
