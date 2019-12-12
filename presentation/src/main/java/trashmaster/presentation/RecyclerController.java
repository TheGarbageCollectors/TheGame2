/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import domain.IGame;
import domain.Item;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author benja
 */
public class RecyclerController {
    private IGame gui;
    @FXML private Button recyclePlastic, recycleMetal, recycleGarbage, recycleConcrete, recyclePaper, recycleBattery, recycleHazardous;
    @FXML private ImageView recycle1, recycle2, recycle3;
    @FXML private Button goTownHall;
    @FXML private String buttonText;
    @FXML private String itemName;
    @FXML private VBox recycleItem;
    @FXML private TextArea myTA;
    @FXML private ArrayList<Item> inventoryListForRecycling = new ArrayList<>();
    @FXML private ObservableList<Button> buttonList = FXCollections.observableArrayList();
    
    @FXML private VBox inventory;
    @FXML private ArrayList<Item> inventoryList = new ArrayList<>();
    @FXML private ObservableList<Text> textList = FXCollections.observableArrayList();
    @FXML private HBox coins; 
    
    @FXML private ImageView recycleWrong;
    @FXML private Text recycleWrongText;
    @FXML private ImageView recycleRight;
    @FXML private Text recycleRightText;
    
    @FXML private ImageView skur;
    @FXML private ImageView heart1, heart2, heart3;
    
    @FXML
    private void goToTownHall() throws IOException  {
        gui.goRoom("town_hall");
        App.setRoot("TownHall");
    
    }
    
    public void initialize() throws IOException {
        this.gui = PrimaryController.getGUI();
        int recyclerLvl = gui.getRecyclerLevel();
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
                skur.setVisible(true);
                break;
        }
        showInventory();
        showCoins();
        heartCheck();
                
    }
    
    @FXML
    private void recycleItemChoice(ActionEvent event) throws IOException {
        this.buttonText = ((Button)event.getSource()).getText();
        this.inventoryListForRecycling = gui.getPlayer().getBackpackObj().getItemsInBackpack();
        recycleItem.setVisible(true);
        int j = 0;
        if (buttonList.isEmpty()) { 
        for (Item i : inventoryListForRecycling) {
            buttonList.add(new Button(i.getName()));
            recycleItem.getChildren().add(buttonList.get(j));
            buttonList.get(j).setOnAction(recycleEvent);
            j++;
        }
        }
    }
    
    @FXML
    EventHandler<ActionEvent> recycleEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)  {
                itemName = ((Button)event.getSource()).getText();
                int inventoryIndex = buttonList.indexOf((Button)event.getSource());
                boolean recycledRight = gui.recycleItems(itemName, buttonText, inventoryIndex);
                recycleItem.setVisible(false);
                buttonList.clear();
                textList.clear();
                inventory.getChildren().clear();
                coins.getChildren().clear();
                try {
                    showCoins();
                    showInventory();
                    hideTextBubble();
                    if (recycledRight) {
                        recycleRight.setVisible(true);
                        recycleRightText.setVisible(true);
                    } else {
                        recycleWrong.setVisible(true);
                        recycleWrongText.setVisible(true);
                    }
                    }
                    catch(IOException e) {
                        System.out.println("Error");
                    }
                recycleItem.getChildren().clear();
                int hp = gui.getHP();
                heartCheck();
                    if (hp <= 0) {
                    heart1.setVisible(false);
                    System.out.println("Gamer Over");
                    try {
                    App.setRoot("GameOver");
                    }
                    catch(Exception e) {
                        System.out.println("Error");
                    }
                }
            }
    };
    
    @FXML
    private void showInventory() throws IOException {
        this.inventoryList = gui.getPlayer().getBackpackObj().getItemsInBackpack();
        inventory.setVisible(true);
        int j = 0;
        if (textList.isEmpty()) { 
        for (Item i : inventoryList) {
            textList.add(new Text(i.getName()));
            textList.get(j).setFont(Font.font("SansSerif", 20));
            textList.get(j).setFill(Color.WHITE);
            inventory.getChildren().add(textList.get(j));
            j++;
        }
        }
    }
    
    @FXML 
    private void showCoins() throws IOException {
        String coins = "" + gui.getCoins();
        Text coinText = new Text(coins);
        coinText.setFont(Font.font("SansSerif", 20));
        coinText.setFill(Color.WHITE);
        this.coins.getChildren().add(coinText);
    }
    
    @FXML
    private void hideTextBubble() throws IOException {
        recycleWrong.setVisible(false);
        recycleWrongText.setVisible(false);
        recycleRight.setVisible(false);
        recycleRightText.setVisible(false);
    }
    
    @FXML
    private void heartCheck() {
        int hp = gui.getHP();
        if (hp == 2) {
            heart3.setVisible(false);
        } else if (hp == 1) {
            heart3.setVisible(false);
            heart2.setVisible(false);
        } else if (hp <= 0) {
            heart3.setVisible(false);
            heart2.setVisible(false);
            heart1.setVisible(false);
        }
    }
   
}
