
package trashmaster.presentation;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class VictorySceneController {
        private UserGUI gui;
    @FXML private Label playerlabel;
        
    @FXML    
    public void initialize() {
        this.gui = PrimaryController.getGUI();
        setPlayerLabel();
    }
    @FXML
    private void setPlayerLabel() {
        playerlabel.setText(gui.game.getPlayerName());
        }
    @FXML
    private void restartGame() throws IOException {
         App.setRoot("primary");
    }
    @FXML
    private void quitGame() throws IOException {
        Platform.exit();
    }
}

