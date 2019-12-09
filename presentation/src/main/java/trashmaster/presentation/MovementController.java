/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Daniel Tran
 */
public class MovementController {
    private UserGUI gui;
    @FXML private Button itemWheel, itemTrashbag, itemBottle, itemBox, itemBattery, itemCan, itemStraw, itemChipsbag;
    @FXML private Button road, goTownHallBtn;
    
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
    private void goToRoad () throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("road");
        App.setRoot("road");
        
    }
    
    @FXML
    private void setVisibleForRoadItems() throws IOException {
        this.gui = PrimaryController.getGUI();
        ArrayList<String> nameListForBtn = gui.game.getItemNamesInRoom();
        for(String itemNames : nameListForBtn)
        {
            switch(itemNames)
            {
                case "bottle":
                    itemBottle.setVisible(true);
                    break;
                case "box":
                    itemBox.setVisible(true);
                    break;
                case "battery":
                    itemBattery.setVisible(true);
                    break;
                case "can":
                    itemCan.setVisible(true);
                    break;
                case "straw":
                    itemStraw.setVisible(true);
                    break;
                case "wheel": //Item from here and down are road-only items
                    itemWheel.setVisible(true);
                    break;
                case "trash":
                    itemTrashbag.setVisible(true);
                    break;
                case "bag":
                    itemChipsbag.setVisible(true);
                    break;
            }
        }  
        
    
    } 
}
