package trashmaster.presentation;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser 
{
    private CommandWords commands;
    

    public Parser() 
    {
        commands = new CommandWords();
        
    }

    public Command getCommand(String firstWord, String secondWord) 
    {
        String word1 = firstWord;
        String word2 = secondWord;

        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands()
    {
        commands.showAll();
    }
}
