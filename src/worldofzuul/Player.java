/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.Scanner;
/**
 *
 * @author magnu
 */
public class Player {

    static void namePlayer(){
        Scanner playerName = new Scanner(System.in);
        Scanner check = new Scanner(System.in);
        
        System.out.println("What is your name?");
        
        //input name
        String name = playerName.nextLine();
        
        //output
        System.out.println("So your name is: " + name + "? Yes or No");
        
        //confirmeing name
        String checkName = check.nextLine();
        
        if(checkName == "yes"){
            System.out.println("Okay nice to meet you " + name);
            
        }
        else{
            if(checkName == "no"){
                namePlayer();
                        
            }
       
            
        }
    }    
       
}
