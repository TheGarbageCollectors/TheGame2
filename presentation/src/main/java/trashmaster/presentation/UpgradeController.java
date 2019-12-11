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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author benja
 */
public class UpgradeController {
    private UserGUI gui;
    @FXML private Button upgradeOption, townHallUpgrade, recyclerUpgrade, backpackUpgrade;
    @FXML private ImageView bubbleOption;
    @FXML private Label upgradeOptiontekst, boughtUpgradeLabel, upgradeResultLabel, notEnoughMoney, clickOnMe;
    @FXML private Button goTownHall;
    @FXML private VBox optionVBox;
    
    @FXML private VBox inventory;
    @FXML private ArrayList<Item> inventoryList = new ArrayList<>();
    @FXML private ObservableList<Text> textList = FXCollections.observableArrayList();
    
    public void initialize() throws IOException {
        showInventory();
    }
    
    @FXML
    private void goToTownHall() throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("town_hall");
        App.setRoot("TownHall");
    
    }
    
    @FXML
    private void upgradeObject(ActionEvent event) throws IOException {
        this.gui = PrimaryController.getGUI();
        String accesText = ((Button)event.getSource()).getAccessibleText(); //takes the accessible text from the buttom that uses this method
        switch(accesText) {
            case "townhall":
                int townHallPrice = gui.game.getUpgradePrice("townhall"); //gets the price of townhall upgrade from the array in UpgradeStation class
                int townHallLevel = (gui.game.getTownLevel())+1; //gets the number for the next level
                townHallUpgrade.setText("Rådhus lv." + townHallLevel + ".\n" + "[" + townHallPrice + "]"); //updates the text on the townHallUpgrade buttom
                if (townHallPrice > gui.game.getPlayerMoney()) {
                    System.out.println(townHallPrice + " " + gui.game.getPlayerMoney());
                    notEnoughMoneyBubble();
                } else {
                    gui.game.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
                    purchasedUpgradeBubble(accesText);
                }
                break;
            case "recycler":  
                int recyclerPrice = gui.game.getUpgradePrice("recycler"); //gets the price of recycler upgrade from the array in UpgradeStation class
                int recycleLevel = (gui.game.getRecyclerLevel())+1; //gets the number for the next level
                recyclerUpgrade.setText("Genbrugsstation lv." + recycleLevel + ".\n" + "[" + recyclerPrice + "]"); //updates the text on the recyclerUpgrade buttom
                if (recyclerPrice > gui.game.getPlayerMoney()) {
                    System.out.println(recyclerPrice + " " + gui.game.getPlayerMoney());
                    notEnoughMoneyBubble();
                } else {
                    gui.game.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
                    purchasedUpgradeBubble(accesText);
                }
                break;
            case "backpack":
                int backpackPrice = gui.game.getUpgradePrice("backpack"); //gets the price of backpack upgrade from the array in UpgradeStation class
                int backpackLevel = (gui.game.getBackpackLevel())+1; //gets the number for the next level
                backpackUpgrade.setText("Rygsæk lv." + backpackLevel + ".\n" + "[" + backpackPrice + "]"); //updates the text on the backpackUpgrade buttom
                if (backpackPrice > gui.game.getPlayerMoney()) {
                    System.out.println(backpackPrice + " " + gui.game.getPlayerMoney());
                    notEnoughMoneyBubble();
                } else {
                    gui.game.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
                    purchasedUpgradeBubble(accesText); //method for enabling purchase text
                }
                break;
         }
        
    }
    
    @FXML
    private void upgradeOptions() throws IOException {
        this.gui = PrimaryController.getGUI();
        upgradeOptiontekst.setVisible(false);
        boughtUpgradeLabel.setVisible(false);
        upgradeResultLabel.setVisible(false);
        clickOnMe.setVisible(false);
        notEnoughMoney.setVisible(false);
        
        optionVBox.setVisible(true);
        
        int townHallPrice = gui.game.getUpgradePrice("townhall"); //gets the price of townhall upgrade from the array in UpgradeStation class
        int townHallLevel = (gui.game.getTownLevel())+1; //gets the number for the next level
        if(townHallLevel > 10) { //checks whether or not this have hit max level 
            townHallUpgrade.setText("Rådhus lv.max."); //sets the text on the townHallUpgrade buttom
            townHallUpgrade.setDisable(true);
        } else {
        townHallUpgrade.setText("Rådhus lv." + townHallLevel + ".\n" + "[" + townHallPrice + "]"); //sets the text on the townHallUpgrade buttom
        }
        
        int recyclerPrice = gui.game.getUpgradePrice("recycler"); //gets the price of recycler upgrade from the array in UpgradeStation class
        int recycleLevel = (gui.game.getRecyclerLevel())+1; //gets the number for the next level
        if(recycleLevel > 3) { //checks whether or not this have hit max level
            recyclerUpgrade.setText("Genbrugsstation lv.max");
            recyclerUpgrade.setDisable(true);
        } else {
        recyclerUpgrade.setText("Genbrugsstation lv." + recycleLevel + ".\n" + "[" + recyclerPrice + "]"); //sets the text on the recyclerUpgrade buttom
        }
        
        int backpackPrice = gui.game.getUpgradePrice("backpack"); //gets the price of backpack upgrade from the array in UpgradeStation class
        int backpackLevel = (gui.game.getBackpackLevel())+1; //gets the number for the next level
        if(backpackLevel > 3) { //checks whether or not this have hit max level
            backpackUpgrade.setText("Rygsæk lv.max");
            backpackUpgrade.setDisable(true);
        } else {
        backpackUpgrade.setText("Rygsæk lv." + backpackLevel + ".\n" + "[" + backpackPrice + "]"); //sets the text on the backpackUpgrade buttom
        }
    
    }
    
    private void notEnoughMoneyBubble() { //method for enabling the "ikke nok penge" label
        this.gui = PrimaryController.getGUI();
        optionVBox.setVisible(false);
        notEnoughMoney.setVisible(true);  
    }
    
    private void purchasedUpgradeBubble(String upgradeTekst) { //method for setting label text after a upgrade has been purchased
        this.gui = PrimaryController.getGUI();
        optionVBox.setVisible(false);
        boughtUpgradeLabel.setVisible(true);
        switch(upgradeTekst) {
            case "townhall":
                upgradeResultLabel.setText("Rådhuset er nu level: " + gui.game.getTownLevel() + "\n Glæde øget med 10 \nByens glæde er nu: " + gui.iGetTownHappiness());
                break;
            case "recycler":
                upgradeResultLabel.setText("Genbrugsstation er nu level: " + gui.game.getRecyclerLevel() + "\nDu har nu flere muligheder for affaldssortering!");
                break;
            case "backpack":
                upgradeResultLabel.setText("Rygsæk er nu level: " + gui.game.getBackpackLevel() + "\nDu kan nu holde " + gui.game.getMaxBackpackSize() + " ting!");
                break;     
        }
        upgradeResultLabel.setVisible(true);
        
    }
    
    @FXML
    private void showInventory() throws IOException {
        this.gui = PrimaryController.getGUI();
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

    }
   
}
