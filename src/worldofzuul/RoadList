package worldofzuul;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Daniel Tran
 */
public class RoadList extends CommonItems {
    private ArrayList<Item> commonList;
    private ArrayList<Item> fullItemList;
    private ArrayList<Item> finalList;
    
    private void makeFullList() {
      makeCommonList(); //makes the commonList from the CommonItems class
        commonList = getList(); //gets the list from the CommonItems class and sets it as a atribute in this class
        
        //makes Items specific for this class
        Item wheel = new Item("Car wheel", new String[]{"Metal", "Rubber"});
        Item trashbag = new Item("Bag of Trash", new String[]{"Paper", "Plastic", "Metal", "Rubber"});
        Item Chipsbag = new Item("Empty bag of Chips", new String[]{"Plastic"});
    
        //adds the Items to the List
       commonList.add(wheel);
       commonList.add(trashbag);
       commonList.add(Chipsbag);
       
       fullItemList = commonList; //after adding all the Items, sets the commonList as the fullItemList
    }
    
     public void makeFinalList() {
        makeFullList(); //makes a list with all the Items
        finalList = new ArrayList<Item>();
        int numOfItems =((int)(Math.random() * 4)+1); //Makes random int variable to decide the amount of Items in the finalList(max 4)
       
        for (int i = 0; i < numOfItems ; i++) { //for-loop to move Items from fullItemList to finalList
            Random rNum = new Random();
            Item tempItem = fullItemList.remove(rNum.nextInt(fullItemList.size())); //Makes variable to store the removed Item temporarily.
            //(Removed by the index number, which was randomly generated depending on the size of the array)
            
            finalList.add(tempItem); //adds the removed item to the finalList
            
        }
     }
    
     public ArrayList<Item> getRoadList(){
         return this.finalList;
     }
}
