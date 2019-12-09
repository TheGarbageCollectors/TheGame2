package trashmaster.presentation;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

//This is the controller for TownHall
public class SecondaryController {
    private UserGUI gui;
    @FXML private Button road;
    
  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    public void goToRoad () throws IOException {
        this.gui = PrimaryController.getGUI();
        gui.goRoom("road");
        App.setRoot("road");
        
        
        
        
        
    }
}