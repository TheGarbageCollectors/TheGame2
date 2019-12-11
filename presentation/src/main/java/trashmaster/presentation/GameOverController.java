package trashmaster.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class GameOverController implements Initializable {
          private UserGUI gui;
    @FXML private StackPane root;
    @FXML private AnchorPane AnchorPane;
    @FXML private ImageView recycle1, recycle2, recycle3;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        makeFadeOut();
    }    
    
    @FXML
    private void restartGame() throws IOException {
         App.setRoot("primary");
    }
    
     @FXML
    private void quitGame() throws IOException {
        Platform.exit();
    }
    
    private void makeFadeOut() { //method for lame transition
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(5000));
        fadeTransition.setNode(AnchorPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    
}
