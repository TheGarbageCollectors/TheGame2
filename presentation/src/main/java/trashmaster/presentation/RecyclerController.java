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
import javafx.scene.image.ImageView;

/**
 *
 * @author benja
 */
public class RecyclerController {
    private UserGUI gui;
    @FXML private Button recyclePlastic;
    @FXML private Button recycleMetal;
    @FXML private ImageView recycle1, recycle2, recycle3;
    @FXML private Button goTownHall;
    
    @FXML
    private void goToTownHall() throws IOException {
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
                break;
            case 3:
                recycle3.setVisible(true);
                break;
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
