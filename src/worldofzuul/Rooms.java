/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author GamerQuvang
 */
public abstract class Rooms
{

    public final String description;
    public final HashMap<String, Room> exits;
    
    public abstract void setExit(String direction, Room neighbor);

      public abstract String getShortDescription();

      public abstract String getLongDescription();

      private abstract String getExitString();

      public abstract Room getExit(String direction) ;
   
}
