/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;


import java.util.Scanner;

/**
 *
 * @author magnu
 */
public class Player
{

    private String name;
    private int money;
    private Backpack backpack = new Backpack(); 

    Player(String playerName)
    {
        this.money = 500;
        this.name = playerName;
    }

    /*private void namePlayer(String playerName)
    {
        String inputName;
        String checkName;
        Scanner scan = new Scanner(System.in);
        
        do
        {
            System.out.println("What is your name?");
            
            System.out.print("> ");
            //input name
            inputName = playerName;

            //output
            System.out.println("So your name is: " + inputName + "? yes or no");
            
            System.out.print("> ");
            //confirmeing name
            checkName = scan.nextLine();

            if (checkName.equals("yes"))
            {
                System.out.println("Okay nice to meet you " + inputName);
                this.name = inputName;
                return;
            }
        } while (name == null); 
    }*/

    public boolean enoughMoney(int amount)
    {
        int moneyLeft = this.money - amount;
        if (moneyLeft >= 0)
        {
            return true;
        } else
        {
            return false;
        }

    }
    
    public Backpack getBackpackObj (){
        return this.backpack; 
    }

    public int removeMoney(int amount)
    {
        System.out.println("Upgrade purchased. " + amount + " coins removed from your balance." );
        System.out.println("Total coins: " + (money - amount));
        return this.money -= amount;
    }

    public int getMoney()
    {
        return this.money;
    }
    public void addMoney(int amount){
        System.out.println(amount + " coins have been added to your balance" );
        System.out.println("Total coins: " + (money + amount));
        this.money += amount;
    }
}
