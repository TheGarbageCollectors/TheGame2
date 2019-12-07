package trashmaster.presentation;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {
    
    @FXML private String name;
    @FXML private TextField input;
    @FXML private Button confirmBtn;
    @FXML private Button yes;
    @FXML private Button no;
    @FXML private Label confirmName;
    @FXML private Label confirmNameText;
    @FXML private Label nameQuestion;
    @FXML private UserGUI gui;
    
    
    /*public PrimaryController(UserGUI gui) {
        this.gui = gui;
    }*/
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("Townhall");
    }
    
    @FXML
    private void systemPrint() {
        System.out.println("Hello Bitches");
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
        
        
        //System.out.println(input2);
    }
    
    @FXML
    private void nameConfirmNo() throws IOException {
         App.setRoot("primary");
    }
    
    @FXML 
    private void nameConfirmYes() throws IOException {
        //gui.play(name);
        switchToSecondary();
    }
    
    
}
