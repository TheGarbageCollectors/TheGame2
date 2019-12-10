/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashmaster.presentation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author benja
 */
public class UpgradeController {
    private UserGUI gui;
    @FXML private Button upgradeOption, townHallUpgrade, recyclerUpgrade, backpackUpgrade;
    @FXML private ImageView bubbleOption;
    @FXML private Label upgradeOptiontekst;
    @FXML private Button goTownHall;
    @FXML private VBox optionVBox;
    
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
        gui.game.upgradeItems(accesText); //upgrades a item depending on the buttom that used this method
        switch(accesText) {
            case "townhall":
                int townHallPrice = gui.game.getUpgradePrice("townhall"); //gets the price of townhall upgrade from the array in UpgradeStation class
                int townHallLevel = (gui.game.getTownLevel())+1; //gets the number for the next level
                townHallUpgrade.setText("Rådhus lv." + townHallLevel + ".\n" + "[" + townHallPrice + "]"); //sets the text on the townHallUpgrade buttom
                break;
            case "recycler":  
                int recyclerPrice = gui.game.getUpgradePrice("recycler"); //gets the price of recycler upgrade from the array in UpgradeStation class
                int recycleLevel = (gui.game.getRecyclerLevel())+1; //gets the number for the next level
                recyclerUpgrade.setText("Genbrugsstation lv." + recycleLevel + ".\n" + "[" + recyclerPrice + "]"); //sets the text on the recyclerUpgrade buttom
                break;
            case "backpack":
                int backpackPrice = gui.game.getUpgradePrice("backpack"); //gets the price of backpack upgrade from the array in UpgradeStation class
                int backpackLevel = (gui.game.getBackpackLevel())+1; //gets the number for the next level
                backpackUpgrade.setText("Rygsæk lv." + backpackLevel + ".\n" + "[" + backpackPrice + "]"); //sets the text on the backpackUpgrade buttom
                break;
         }
        
    }
    
    @FXML
    private void upgradeOptions() throws IOException {
        //String accesText = ((Button)event.getSource()).getAccessibleText();
        this.gui = PrimaryController.getGUI();
        upgradeOptiontekst.setVisible(false);
        optionVBox.setVisible(true);
        
        int townHallPrice = gui.game.getUpgradePrice("townhall"); //gets the price of townhall upgrade from the array in UpgradeStation class
        int townHallLevel = (gui.game.getTownLevel())+1; //gets the number for the next level
        townHallUpgrade.setText("Rådhus lv." + townHallLevel + ".\n" + "[" + townHallPrice + "]"); //sets the text on the townHallUpgrade buttom
        
        int recyclerPrice = gui.game.getUpgradePrice("recycler"); //gets the price of recycler upgrade from the array in UpgradeStation class
        int recycleLevel = (gui.game.getRecyclerLevel())+1; //gets the number for the next level
        recyclerUpgrade.setText("Genbrugsstation lv." + recycleLevel + ".\n" + "[" + recyclerPrice + "]"); //sets the text on the recyclerUpgrade buttom
        
        int backpackPrice = gui.game.getUpgradePrice("backpack"); //gets the price of backpack upgrade from the array in UpgradeStation class
        int backpackLevel = (gui.game.getBackpackLevel())+1; //gets the number for the next level
        backpackUpgrade.setText("Rygsæk lv." + backpackLevel + ".\n" + "[" + backpackPrice + "]"); //sets the text on the backpackUpgrade buttom
    
    }
    
}
