package trashmaster.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


public class GameOverController implements Initializable {

    @FXML private StackPane root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    
}
