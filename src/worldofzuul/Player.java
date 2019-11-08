/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.Scanner;

/**
 *
 * @author magnu
 */
public class Player
{

    private final String name;
    private int money;
    private Backpack backpack = new Backpack(); 

    Player()
    {
        this.money = 500;
        
        this.name = namePlayer();
    }

    private String namePlayer()
    {
        String inputName;
        String checkName;
        Scanner scan = new Scanner(System.in);
        do
        {
            System.out.println("What is your name?");

            //input name
            inputName = scan.nextLine();

            //output
            System.out.println("So your name is: " + inputName + "? yes or no");

            //confirmeing name
            checkName = scan.nextLine();

            if (checkName.equals("yes"))
            {
                System.out.println("Okay nice to meet you " + inputName);
                return name;
            }
        } while (checkName.equals("no"));

        return name;

    }

    public boolean enoughMoney(int amount)
    {
        int moneyLeft = this.money - amount;
        if (moneyLeft > 0)
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
        System.out.println("You bought something. " + amount + "removed from your balance." );
        System.out.println("You now have " + (money - amount) + " left");
        return this.money -= amount;
    }

    public int getMoney()
    {
        return this.money;
    }
    public void addMoney(int amount){
        System.out.println("You got money. " + amount + " added to your balance" );
        System.out.println("You now have " + (money + amount) + " on your balance");
        this.money += amount;
    }
}
