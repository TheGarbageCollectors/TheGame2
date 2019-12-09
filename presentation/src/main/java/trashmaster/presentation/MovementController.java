/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Daniel Tran
 */
public class MovementController {
    private UserGUI gui;
    @FXML private Button itemWheel, itemTrashbag, itemBottle, itemBox, itemBattery, itemCan, itemStraw, itemChipsbag;
    @FXML private ImageView imageBottle;
    @FXML private Button checkItem;
    @FXML private Button road, goTownHallBtn;
    @FXML private Button checkInventory;
    
    @FXML private Button recyclePlastic;
    @FXML private Button recycleMetal;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void goToTownHall() throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("town_hall");
        App.setRoot("TownHall");
    
    }
    
    @FXML
    private void goToRecycler() throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("recycler");
        App.setRoot("recycler");
    }
    
    @FXML
    private void goToRoad () throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("road");
        App.setRoot("Road");
        /*if (App.getRoot() == itemBottle.getScene()) {
            setVisibleForRoadItems();
        }*/
            
    }
    
    @FXML 
    private void getInvetory() throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.getInventory();
    }
    
    @FXML
    private void pickUpItems(ActionEvent event) throws IOException {
        gui.game.pickupItems(((Button)event.getSource()).getAccessibleText());
        ((Button)event.getSource()).setVisible(false);
    }
    
    @FXML
    public void setVisibleForRoadItems() throws IOException {
        checkItem.setVisible(false);
        this.gui = PrimaryController.getGUI();
        ArrayList<String> nameListForBtn = gui.game.getItemNamesInRoom();
        for(String itemNames : nameListForBtn)
        {
            switch(itemNames)
            {
                case "Plastikflaske":
                    itemBottle.setVisible(true);
                    imageBottle.setVisible(true);
                    break;
                case "Papkasse":
                    itemBox.setVisible(true);
                    break;
                case "Batteri":
                    itemBattery.setVisible(true);
                    break;
                case "Dåse":
                    itemCan.setVisible(true);
                    break;
                case "Sugerør":
                    itemStraw.setVisible(true);
                    break;
                case "Hjul": //Item from here and down are road-only items
                    itemWheel.setVisible(true);
                    break;
                case "Skraldepose":
                    itemTrashbag.setVisible(true);
                    break;
                case "Chipspose":
                    itemChipsbag.setVisible(true);
                    break;
            }
        }
        
    
    }
    
    @FXML
    private void recycleItemChoice(ActionEvent event) throws IOException {
        String buttonText;
        String itemName;
        
        buttonText = ((Button)event.getSource()).getText();
       
        System.out.println(buttonText);
        //recycleItems();
    }
    
    
    @FXML
    private void recycleItems() throws IOException {
        String buttonText;
        String itemName;
        
        buttonText = this.recyclePlastic.getText();
        
        //gui.recycleItems();
    }
    
    
}
