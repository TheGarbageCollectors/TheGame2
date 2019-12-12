/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import domain.Game;

/**
 *
 * @author GamerQuvang
 */
public class UserGUI
{

    public Game game;

    public void play(String playerName)
    {
        game = new Game(playerName);
    }
    
    public int getTownLvl() {
        return game.getTownLevel();
    }
    
    public int iGetTownHappiness() {
        int happiness = game.getHappiness();
        return happiness;
    }
    
    public void getInventory()
    {
        System.out.println(game.getInventory());
    }

    public void goRoom(String room)
    {
        System.out.println(game.goRoom(room));
    }

    public void pickUpItems(String itemName)
    {
        System.out.println(game.pickupItems(itemName));
    }

    public void recycleItems(String name, String buttonText, int inventoryIndex)
    {
        System.out.println(game.recycleItems(name, buttonText, inventoryIndex));
    }

}
