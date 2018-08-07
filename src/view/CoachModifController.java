package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class CoachModifController {

    @FXML
    private AnchorPane coachModif;

    @FXML
    private Button addCoach;
    
    @FXML
    private Button modCoach;
    
    @FXML
    private Button removeCoachButton;


    @FXML
    void goToAddCoach(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addCoach");
    }
    
    @FXML
    void goToModifyCoach(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyCoach");
    }
    @FXML
    void goToRemoveCoach(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeCoach");
    }
}
