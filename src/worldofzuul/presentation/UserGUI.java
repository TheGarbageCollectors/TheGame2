/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.presentation;

import worldofzuul.domain.Game;

/**
 *
 * @author GamerQuvang
 */
public class UserGUI
{

    private Game game;
    private Parser parser;

    public void play()
    {
        parser = new Parser();
        game = new Game();
        printWelcome();
        boolean finished = false;
        while (!finished)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Garbage Collectors!");
        System.out.println("This is a game about collecting different sorts of garbage and learning how to sort it properly.");
        System.out.println("The goal of the game is to raise your populations happiness-level");
        System.out.println("The local area is littered with garbage");
        System.out.println("It's your job as the mayor to make the town happy, by keeping the streets clean");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(game.getCurrentRoom().getLongDescription());
    }

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
                case GO:
                    goRoom(command);
                    break;
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
    }

    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Town.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();

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

    private void getInventory(Command command)
    {
        System.out.println(game.getInventory(command));
    }

    private void goRoom(Command command)
    {
        System.out.println(game.goRoom(command));
    }

    private void pickupItems(Command command)
    {
        System.out.println(game.pickupItems(command));
    }

    private void upgradeItems(Command command)
    {
        System.out.println(game.upgradeItems(command));
    }

    private void recycleItems(Command command)
    {
        System.out.println(game.recycleItems(command));
    }

}
