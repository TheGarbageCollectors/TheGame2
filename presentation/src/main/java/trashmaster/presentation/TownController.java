/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author benja
 */
public class TownController {
    private UserGUI gui;
    @FXML private Button goRoad, goToRecycler, goUpgradeStation;
    @FXML private Button checkInventory;
    @FXML private ImageView town1, town2, town3, town4, town5;
    
    public void initialize() throws IOException {
        this.gui = PrimaryController.getGUI();
        int townHall = gui.game.getTownLevel();
        switch(townHall)
        { 
            case 1:
            case 2:
                town1.setVisible(true);
                break;
            case 3:
            case 4:
                town2.setVisible(true);
                break;
            case 5:
            case 6:
                town3.setVisible(true);
                break;
            case 7:
            case 8:
                town4.setVisible(true);
                break;
            case 9:
            case 10:
                town5.setVisible(true);
                break;
        }
                
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
    private void goToRecycler() throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("recycler");
        App.setRoot("recycler");
    }
    
     @FXML
    private void goToUpgradeStation () throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("upgradestation");
        App.setRoot("UpgradeStation");
        /*if (App.getRoot() == itemBottle.getScene()) {
            setVisibleForRoadItems();
        }*/  
    }
    
}
