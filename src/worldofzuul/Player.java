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

    private String name;
    private int money;

    Player()
    {
        this.money = 0;

        this.name = namePlayer();
    }

    private String namePlayer()
    {
        String name;
        String checkName;
        Scanner scan = new Scanner(System.in);
        do
        {
            System.out.println("What is your name?");

            //input name
            name = scan.nextLine();

            //output
            System.out.println("So your name is: " + name + "? Yes or No");

            //confirmeing name
            checkName = scan.nextLine();

            if (checkName.equals("Yes"))
            {
                System.out.println("Okay nice to meet you " + name);
                return name;
            }
        } while (checkName.equals("No"));

    }
}
