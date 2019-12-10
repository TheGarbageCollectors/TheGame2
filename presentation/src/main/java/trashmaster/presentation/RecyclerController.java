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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author benja
 */
public class RecyclerController {
    private UserGUI gui;
    @FXML private Button recyclePlastic, recycleMetal, recycleGarbage, recycleConcrete, recyclePaper, recycleBattery, recycleHazardous;
    @FXML private ImageView recycle1, recycle2, recycle3;
    @FXML private Button goTownHall;
    @FXML private String buttonText;
    @FXML private String itemName;
    @FXML private VBox recycleItem;
    @FXML private TextArea myTA;
    @FXML private ArrayList<Item> inventoryList = new ArrayList<>();
    @FXML private ObservableList<Button> buttonList = FXCollections.observableArrayList();
    
    @FXML
    private void goToTownHall() throws IOException  {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("town_hall");
        App.setRoot("TownHall");
    
    }
    
    public void initialize() throws IOException {
        this.gui = PrimaryController.getGUI();
        int recyclerLvl = gui.game.getRecyclerLevel();
        switch(recyclerLvl)
        { 
            case 1:
                recycle1.setVisible(true);
                break;
            case 2:
                recycle2.setVisible(true);
                recycleConcrete.setVisible(true);
                recyclePaper.setVisible(true);
                break;
            case 3:
                recycle3.setVisible(true);
                recycleConcrete.setVisible(true);
                recyclePaper.setVisible(true);
                recycleBattery.setVisible(true);
                recycleHazardous.setVisible(true);
                break;
        }
                
    }
    
    @FXML
    private void recycleItemChoice(ActionEvent event) throws IOException {
        this.buttonText = ((Button)event.getSource()).getText();
        this.inventoryList = gui.game.getPlayer().getBackpackObj().getItemsInBackpack();
        recycleItem.setVisible(true);
        String buttonID = "btn";
        int j = 0;
        if (buttonList.isEmpty()) { 
        for (Item i : inventoryList) {
            buttonList.add(new Button(i.getName()));
            recycleItem.getChildren().add(buttonList.get(j));
            buttonList.get(j).setOnAction(recycleEvent);
            j++;
        }
        } else {
            
        }
        
        
        //recycleItems();
    }
    
    @FXML
    EventHandler<ActionEvent> recycleEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {
                itemName = ((Button)event.getSource()).getText();
                int inventoryIndex = buttonList.indexOf((Button)event.getSource());
                //System.out.println(itemName);
                gui.recycleItems(itemName, buttonText, inventoryIndex);
                recycleItem.setVisible(false);
                buttonList.clear();
                recycleItem.getChildren().clear();
                int hp = gui.game.getHP();
                if (hp <= 0) { 
                   
                }
            }
    };
    
    
}
