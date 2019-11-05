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
import java.util.ArrayList;

public final class GameItems {

    private ArrayList<Item> items = new ArrayList<>();
    private Item plasticBottle;
    private Item doorFrame;
    private Item cardBoard;
    private Item battery;
    private Item concrete;
    private Item can;
    private Item laptop;

    private GameItems() {
        plasticBottle = new Item("PlastikFlaske", new String[]{"Plastik", "Beton"});
        doorFrame = new Item("DoorFrame", new String[]{"Metal", "Beton"});
        cardBoard = new Item("Papkasse", new String[]{"Pap"});
        battery = new Item("Batteri", new String[]{"Metal"});
        concrete = new Item("Beton", new String[]{"Beton"});
        can = new Item("Can", new String[]{"Metal"});
        laptop = new Item("Laptop", new String[]{"Metal", "Plastik"});
    }

    private void makeItems() {

    }

    private void makeLootlists(String name) {
        //Using clear to remove everything from the arraylist if there is anything. 
        items.clear();

        items.add(can);
        items.add(laptop);
        switch (name) {
            case "Vej":
                items.add(plasticBottle);
                items.add(cardBoard);
                break;
            case "Strand":
                items.add(plasticBottle);
                break;
            case "Skov":
                items.add(plasticBottle);
                break;
            case "Forladt by":
                items.add(concrete);
                items.add(doorFrame);
                break;
        }
    }

    public ArrayList getLootList(String name) {
        makeLootlists(name);
        return this.items;
    }

}
