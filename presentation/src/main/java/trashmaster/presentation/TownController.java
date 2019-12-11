/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import domain.Item;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author benja
 */
public class TownController {
    private UserGUI gui;
    @FXML private Button goRoad, goToRecycler, goUpgradeStation;
    @FXML private Button checkInventory;
    @FXML private ImageView town1, town2, town3, town4, town5;
    @FXML private VBox inventory;
    @FXML private ArrayList<Item> inventoryList = new ArrayList<>();
    @FXML private ObservableList<Text> textList = FXCollections.observableArrayList();
    
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
                showInventory();
    }
    
   @FXML
    private void showInventory() throws IOException {
        this.inventoryList = gui.game.getPlayer().getBackpackObj().getItemsInBackpack();
        inventory.setVisible(true);
        int j = 0;
        if (textList.isEmpty()) { 
        for (Item i : inventoryList) {
            textList.add(new Text(i.getName()));
            textList.get(j).setFont(Font.font("SansSerif", 20));
            textList.get(j).setFill(Color.WHITE);
            inventory.getChildren().add(textList.get(j));
            
            //textList.get(j).setOnAction(recycleEvent);
            j++;
        }
        } else {
            
        }
        
        
        //recycleItems();
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
