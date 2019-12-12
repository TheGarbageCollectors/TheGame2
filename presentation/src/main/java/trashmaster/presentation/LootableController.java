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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Daniel Tran
 */
public class LootableController {
    private UserGUI gui;
    //Add buttons for item here
    @FXML private Button itemWheel, itemTrashbag, itemBottle, itemBox, itemBattery, itemCan, itemStraw, itemChipsbag;
    @FXML private Button itemBeachBall, itemJuiceCarton, itemGumpaper, itemBike, itemToiletPaper, itemLighter, itemDoor, itemBrick, itemPipe;  
    //Add matching sprites to button here
    @FXML private ImageView imageWheel, imageTrashbag, imageBottle, imageBox, imageBattery, imageCan, imageStraw, imageChipsbag;
    @FXML private ImageView imageBeachBall, imageJuiceCarton, imageGumpaper, imageBike, imageToiletPaper, imageLighter, imageDoor, imageBrick, imagePipe;
    @FXML private Button checkItem;
    //Add movement buttons here
    @FXML private Button goRoad, goTownHall, goBeach, goForrest;
    @FXML private Button checkInventory;
    
    @FXML private ArrayList<ImageView> imageList = new ArrayList<>();
   
    @FXML private VBox inventory;
    @FXML private ArrayList<Item> inventoryList = new ArrayList<>();
    @FXML private ObservableList<Text> textList = FXCollections.observableArrayList();
    @FXML private HBox coins; 
    
    
    
    public void initialize() throws IOException {
        this.gui = PrimaryController.getGUI();
        setVisibleForItems();
        showInventory();
        showCoins();
    }
    
    @FXML
    private void goToTownHall() throws IOException {
        gui.goRoom("town_hall");
        App.setRoot("TownHall");
    
    }
    
    @FXML
    private void goToBeach () throws IOException {
        gui.goRoom("beach");
        App.setRoot("Beach");
    }
    
    @FXML
    private void goToForrest () throws IOException {
        gui.goRoom("forrest");
        App.setRoot("Forrest");
    }
    
    @FXML
    private void goToVillage() throws IOException {
        gui.goRoom("abandoned_village");
        App.setRoot("Village");
    }
    
    @FXML
    private void goToRoad () throws IOException {
        gui.goRoom("road");
        App.setRoot("Road");
    }
    
    @FXML
    private void pickUpItems(ActionEvent event) throws IOException {
        String accesText = ((Button)event.getSource()).getAccessibleText();
        if (!gui.game.getIsBackpackFull()) {
            gui.pickUpItems(accesText);
            textList.clear();
            inventory.getChildren().clear();
            showInventory();
            ((Button)event.getSource()).setVisible(false);
            for(ImageView iV : this.imageList) {
                if (accesText != null) {
                    if(accesText.equals(iV.getAccessibleText())) {
                        iV.setVisible(false);
                        System.out.println("???");
                    } else {
                        System.out.println("No matching imageview");
                    }
                } else {
                    System.out.println("Botton does not have accessible text");
                }
            }
        } else {
            gui.pickUpItems(accesText);
        }
    }
    
    @FXML 
    private void showCoins() throws IOException {
        String coins = "" + gui.game.getCoins();
        Text coinText = new Text(coins);
        coinText.setFont(Font.font("SansSerif", 20));
        coinText.setFill(Color.WHITE);
        this.coins.getChildren().add(coinText);
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
            j++;
        }
        }
    }
    
    @FXML
    public void setVisibleForItems() throws IOException {
        ArrayList<String> nameListForBtn = gui.game.getItemNamesInRoom();
        makeImageList();
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
                    imageBox.setVisible(true);
                    break;
                case "Batteri":
                    itemBattery.setVisible(true);
                    imageBattery.setVisible(true);
                    break;
                case "Dåse":
                    itemCan.setVisible(true);
                    imageCan.setVisible(true);
                    break;
                case "Sugerør":
                    itemStraw.setVisible(true);
                    imageStraw.setVisible(true);
                    break;
                case "Hjul":
                    itemWheel.setVisible(true);
                    imageWheel.setVisible(true);
                    break;
                case "Skraldepose":
                    itemTrashbag.setVisible(true);
                    imageTrashbag.setVisible(true);
                    break;
                case "Chipspose":
                    itemChipsbag.setVisible(true);
                    imageChipsbag.setVisible(true);
                    break;
                case "Badebold":
                    itemBeachBall.setVisible(true);
                    imageBeachBall.setVisible(true);
                    break;
                case "Juicekarton":
                    itemJuiceCarton.setVisible(true);
                    imageJuiceCarton.setVisible(true);
                    break;
                case "Tyggegummipapir":
                    itemGumpaper.setVisible(true);
                    imageGumpaper.setVisible(true);
                    break;
                case "Cykel":
                    itemBike.setVisible(true);
                    imageBike.setVisible(true);
                    break;
                case "Toiletpapir":
                    itemToiletPaper.setVisible(true);
                    imageToiletPaper.setVisible(true);
                    break;
                case "Lighter":
                    itemLighter.setVisible(true);
                    imageLighter.setVisible(true);
                    break;
                case "Dør":
                    itemDoor.setVisible(true);
                    imageDoor.setVisible(true);
                    break;
                case "Mursten":
                    itemBrick.setVisible(true);
                    imageBrick.setVisible(true);
                    break;
                case "Rør":
                    itemPipe.setVisible(true);
                    imagePipe.setVisible(true);
                    break;
            }
        }
    }
    
    @FXML
    public void makeImageList()throws IOException {
        this.imageList.add(imageBottle);
        this.imageList.add(imageBox);
        this.imageList.add(imageBattery);
        this.imageList.add(imageCan);
        this.imageList.add(imageStraw);
        
        String currentRoom = gui.game.getCurrentRoomName();
        switch(currentRoom) {
            case "road":
                this.imageList.add(imageWheel);
                this.imageList.add(imageTrashbag);
                this.imageList.add(imageChipsbag);
                break;
            case "beach":
                this.imageList.add(imageBeachBall);
                this.imageList.add(imageJuiceCarton);
                this.imageList.add(imageGumpaper);
                break;
            case "forrest":
                this.imageList.add(imageBike);
                this.imageList.add(imageToiletPaper);
                this.imageList.add(imageLighter);
                break;
            case "abandoned_village":
                this.imageList.add(imageDoor);
                this.imageList.add(imageBrick);
                this.imageList.add(imagePipe);
                break;
        }
    }
}
