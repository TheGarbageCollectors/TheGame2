/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author benja
 */
public interface IGame {
    public String getCurrentRoomName();
    public int getTownLevel();
    public int getRecyclerLevel();
    public Player getPlayer();
    public int getHP();
    public int getBackpackLevel();
    public int getCoins();
    public boolean recycleItems(String name, String buttonText, int inventoryIndex);
    public String upgradeItems(String thingToUpgrade);
    public String goRoom(String room);
    public String pickUpItems(String itemName);
    public ArrayList<String> getItemNamesInRoom();
    public int getMaxBackpackSize();
    public boolean getIsBackpackFull();
    public int getUpgradePrice(String thingToUpgrade);
    public int getHappiness();
    public int getPlayerMoney();
    public String getInventory();
    public String getPlayerName();
    
}
