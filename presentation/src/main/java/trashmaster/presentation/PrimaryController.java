package trashmaster.presentation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PrimaryController {
    
    @FXML private String name;
    @FXML private AnchorPane introPane, namePane;
    @FXML private TextField input;
    @FXML private Button confirmBtn, yes, no;
    @FXML private Label confirmName, confirmNameText, nameQuestion;
    @FXML private static UserGUI gui;
    
    @FXML
    private void goToTown() throws IOException {
        gui.goRoom("town_hall");
        App.setRoot("Townhall");
    }
    
    @FXML
    private void namePlayer() throws IOException {
        this.name = input.getText();
        System.out.println(name);
        confirmName.setText(name);
        
        nameQuestion.setVisible(false);
        input.setVisible(false);
        confirmBtn.setVisible(false);
        
        confirmName.setVisible(true);
        confirmNameText.setVisible(true);
        yes.setVisible(true);
        no.setVisible(true);
        
        yes.setDisable(false);
        no.setDisable(false);
    }
    
    @FXML
    private void nameConfirmNo() throws IOException {
         App.setRoot("primary");
    }
    
    @FXML 
    private void nameConfirmYes() throws IOException {
        gui = new UserGUI();
        gui.play(name);
        namePane.setVisible(false);
        introPane.setVisible(true);
        
    }
    
    @FXML 
    private void startGame() throws IOException {
        goToTown();
    }
    
    public static UserGUI getGUI() {
        return gui;
    }
    
}
