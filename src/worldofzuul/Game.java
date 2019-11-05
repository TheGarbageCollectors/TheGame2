package worldofzuul;

import java.util.ArrayList;
import java.util.HashMap;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private final String[] materials = {"Paper", "Metal", "Beton", "Plastic"};   
    private final int[] materialValues = {10, 20, 30, 40}; 
    static public HashMap<String, Integer> materialMap = new HashMap<>();

    public Game() 
    {
        createRooms();
        makeMaterials();
        parser = new Parser();
        
    }
    
    private void makeMaterials(){
        materialMap.put("Paper", 10);
        materialMap.put("Beton", 20);
        materialMap.put("Metal", 30);
        materialMap.put("Plastic", 40);
    }


    private void createRooms()
    {
        Room town, theatre, pub, lab, office;

        Room abandonedVillage = new Lootable("outside the main entrance of the university", "Abandoned Village");
        Room  upgradeStation = new UpgradeStation("in a lecture theatre", "Upgrade Station");
        Room recycler = new Recycler("in the campus pub", "Recycler", materialMap);
        Room Town = new Town("in a computing lab", "Town");
        Room road = new Lootable("in the computing admin office", "Road");
        Room beach = new Lootable("Yes","Beach");
        Room forrest = new Lootable("This is a forrest", "Forrest");
        
        Town.setExit("east", road);
        Town.setExit("south", beach);
        Town.setExit("west", upgradeStation);

        road.setExit("west", recycler);
        road.setExit("East", forrest);

        recycler.setExit("east", road);

        beach.setExit("north", Town);
        upgradeStation.setExit("east", Town);

        forrest.setExit("west", road);

        currentRoom = Town;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
