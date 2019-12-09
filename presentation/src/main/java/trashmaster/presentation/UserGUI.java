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
    //private Parser parser;

    public void play(String playerName)
    {
        
        //parser = new Parser();
        game = new Game(playerName);
        //printWelcome();
        //boolean finished = false;
        
        //Command command = parser.getCommand("go", "town_hall");
        //finished = processCommand(command);
        
        //System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Garbage Collectors!");
        System.out.println("This is a game about collecting different sorts of garbage and learning how to sort it properly.");
        System.out.println("The goal of the game is to raise your populations happiness-level");
        System.out.println("The local area is littered with garbage");
        System.out.println("It's your job as the mayor to make the town happy, by keeping the streets clean");
        System.out.println("Type " + " if you need help.");
        System.out.println();
        System.out.println(game.getCurrentRoom().getLongDescription());
    }

    /*
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN)
        {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (null != commandWord)
        {
            switch (commandWord)
            {
                case HELP:
                    printHelp();
                    break;
                //case GO:
                //    goRoom(command);
                //    break;
                case QUIT:
                    wantToQuit = quit(command);
                    break;
                case PICKUP:
                    pickupItems(command);
                    break;
                case UPGRADE:
                    upgradeItems(command);
                    break;
                case RECYCLE:
                    recycleItems(command);
                    break;
                case INVENTORY:
                    getInventory(command);
                    break;
                default:
                    break;
            }
        }

        return wantToQuit;
    } */

    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Town.");
        System.out.println();
        System.out.println("Your command words are:");
        //parser.showCommands();

    }

    private boolean quit(Command command)
    {
        if (command.hasSecondWord())
        {
            System.out.println("Quit what?");
            return false;
        } else
        {
            return true;
        }
    }

    
    public void getInventory()
    {
        System.out.println(game.getInventory());
    }

    public void goRoom(String room)
    {
        System.out.println(game.goRoom(room));
    }

    private void pickupItems(String itemName)
    {
        System.out.println(game.pickupItems(itemName));
    }

    private void upgradeItems(Command command)
    {
        System.out.println(game.upgradeItems(command));
    }

    public void recycleItems(String name, String buttonText)
    {
        System.out.println(game.recycleItems(name, buttonText));
    }

}
