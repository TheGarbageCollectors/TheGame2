package trashmaster.presentation;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {
    @FXML private Button road;
    @FXML private UserGUI gui;
  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    @FXML
    private void goToRoad () throws IOException {
        App.setRoot("road");
        
    }
}