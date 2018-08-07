package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class teamMenuController {

    @FXML
    private AnchorPane teamMenu;

    @FXML
    private Button addTeam;

    @FXML
    private Button removeTeamButton;

    @FXML
    private Button modTeam;

    @FXML
    void goToAddTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addTeam");
    }

    @FXML
    void goToModifyTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyTeam");
    }

    @FXML
    void goToRemoveTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeTeam");
    }

}
