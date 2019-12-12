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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class UpgradeController {
    private IGame gui;
    @FXML private Button upgradeOption, townHallUpgrade, recyclerUpgrade, backpackUpgrade;
    @FXML private ImageView bubbleOption;
    @FXML private Label upgradeOptiontekst, boughtUpgradeLabel, upgradeResultLabel, notEnoughMoney, clickOnMe;
    @FXML private Button goTownHall;
    @FXML private VBox optionVBox;
    
    @FXML private VBox inventory;
    @FXML private ArrayList<Item> inventoryList = new ArrayList<>();
    @FXML private ObservableList<Text> textList = FXCollections.observableArrayList();
    @FXML private HBox coins; 
    
    public void initialize() throws IOException {
        this.gui = PrimaryController.getGUI();
        showInventory();
        showCoins();
    }
    
    @FXML
    private void goToTownHall() throws IOException {
        gui.goRoom("town_hall");
        App.setRoot("TownHall");
    
    }
    
    @FXML
    private void upgradeObject(ActionEvent event) throws IOException {
        String accesText = ((Button)event.getSource()).getAccessibleText(); //takes the accessible text from the buttom that uses this method
        switch(accesText) {
            case "townhall":
                int townHallPrice = gui.getUpgradePrice("townhall"); //gets the price of townhall upgrade from the array in UpgradeStation class
                int townHallLevel = (gui.getTownLevel())+1; //gets the number for the next level
                townHallUpgrade.setText("Rådhus lv." + townHallLevel + ".\n" + "[" + townHallPrice + "]"); //updates the text on the townHallUpgrade buttom
                if (townHallPrice > gui.getPlayerMoney()) {
                    System.out.println(townHallPrice + " " + gui.getPlayerMoney());
                    notEnoughMoneyBubble();
                } else {
                    gui.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
                    purchasedUpgradeBubble(accesText);
                        if (gui.getHappiness() == 100) {
                            App.setRoot("VictoryScene");
                            System.out.println("Et eller andet");
                    }
                }

                break;
               
            case "recycler":  
                int recyclerPrice = gui.getUpgradePrice("recycler"); //gets the price of recycler upgrade from the array in UpgradeStation class
                int recycleLevel = (gui.getRecyclerLevel())+1; //gets the number for the next level
                recyclerUpgrade.setText("Genbrugsstation lv." + recycleLevel + ".\n" + "[" + recyclerPrice + "]"); //updates the text on the recyclerUpgrade buttom
                if (recyclerPrice > gui.getPlayerMoney()) {
                    System.out.println(recyclerPrice + " " + gui.getPlayerMoney());
                    notEnoughMoneyBubble();
                } else {
                    gui.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
                    purchasedUpgradeBubble(accesText);
                }
                break;
            case "backpack":
                int backpackPrice = gui.getUpgradePrice("backpack"); //gets the price of backpack upgrade from the array in UpgradeStation class
                int backpackLevel = (gui.getBackpackLevel())+1; //gets the number for the next level
                backpackUpgrade.setText("Rygsæk lv." + backpackLevel + ".\n" + "[" + backpackPrice + "]"); //updates the text on the backpackUpgrade buttom
                if (backpackPrice > gui.getPlayerMoney()) {
                    System.out.println(backpackPrice + " " + gui.getPlayerMoney());
                    notEnoughMoneyBubble();
                } else {
                    gui.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
                    purchasedUpgradeBubble(accesText); //method for enabling purchase text
                }
                break;
         }
        
    }
    
    @FXML
    private void upgradeOptions() throws IOException {;
        upgradeOptiontekst.setVisible(false);
        boughtUpgradeLabel.setVisible(false);
        upgradeResultLabel.setVisible(false);
        clickOnMe.setVisible(false);
        notEnoughMoney.setVisible(false);
        
        optionVBox.setVisible(true);
        
        int townHallPrice = gui.getUpgradePrice("townhall"); //gets the price of townhall upgrade from the array in UpgradeStation class
        int townHallLevel = (gui.getTownLevel())+1; //gets the number for the next level
        if(townHallLevel > 10) { //checks whether or not this have hit max level 
            townHallUpgrade.setText("Rådhus lv.max."); //sets the text on the townHallUpgrade buttom
            townHallUpgrade.setDisable(true);
        } else {
        townHallUpgrade.setText("Rådhus lv." + townHallLevel + ".\n" + "[" + townHallPrice + "]"); //sets the text on the townHallUpgrade buttom
        }
        
        int recyclerPrice = gui.getUpgradePrice("recycler"); //gets the price of recycler upgrade from the array in UpgradeStation class
        int recycleLevel = (gui.getRecyclerLevel())+1; //gets the number for the next level
        if(recycleLevel > 3) { //checks whether or not this have hit max level
            recyclerUpgrade.setText("Genbrugsstation lv.max");
            recyclerUpgrade.setDisable(true);
        } else {
        recyclerUpgrade.setText("Genbrugsstation lv." + recycleLevel + ".\n" + "[" + recyclerPrice + "]"); //sets the text on the recyclerUpgrade buttom
        }
        
        int backpackPrice = gui.getUpgradePrice("backpack"); //gets the price of backpack upgrade from the array in UpgradeStation class
        int backpackLevel = (gui.getBackpackLevel())+1; //gets the number for the next level
        if(backpackLevel > 3) { //checks whether or not this have hit max level
            backpackUpgrade.setText("Rygsæk lv.max");
            backpackUpgrade.setDisable(true);
        } else {
        backpackUpgrade.setText("Rygsæk lv." + backpackLevel + ".\n" + "[" + backpackPrice + "]"); //sets the text on the backpackUpgrade buttom
        }
    
    }
    
    private void notEnoughMoneyBubble() { //method for enabling the "ikke nok penge" label
        optionVBox.setVisible(false);
        notEnoughMoney.setVisible(true);  
    }
    
    private void purchasedUpgradeBubble(String upgradeTekst) throws IOException { //method for setting label text after a upgrade has been purchased
        optionVBox.setVisible(false);
        boughtUpgradeLabel.setVisible(true);
        switch(upgradeTekst) {
            case "townhall":
                upgradeResultLabel.setText("Rådhuset er nu level: " + gui.getTownLevel() + "\n Glæde øget med 10 \nByens glæde er nu: " + gui.getHappiness());
                break;
            case "recycler":
                upgradeResultLabel.setText("Genbrugsstation er nu level: " + gui.getRecyclerLevel() + "\nDu har nu flere muligheder for affaldssortering!");
                break;
            case "backpack":
                upgradeResultLabel.setText("Rygsæk er nu level: " + gui.getBackpackLevel() + "\nDu kan nu holde " + gui.getMaxBackpackSize() + " ting!");
                break;     
        }
        coins.getChildren().clear();
        showCoins();
        upgradeResultLabel.setVisible(true);
        
    }
    
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
   
}
