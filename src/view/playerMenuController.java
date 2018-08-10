package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class playerMenuController {

    @FXML
    private AnchorPane playerMenu;

    @FXML
    private Button addPlayer;

    @FXML
    private Button removePlayer;

    @FXML
    private Button modPlayer;
    
    @FXML
    private Button removeFromTeamButton;

    @FXML
    private Button viewPlayer;

    @FXML
    void goToAddPlayer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addPlayer");

    }

    @FXML
    void goToModifyPlayer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyPlayer");

    }

    @FXML
    void goToRemovePlayer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removePlayer");

    }
    
    @FXML
    void goToViewPlayers(ActionEvent event) {
    	WindowManager.openWindow("viewPlayer");
    }
    
    @FXML
    void goToRemovePlayerFromTeam(ActionEvent event) {
    	WindowManager.openWindow("removePlayerFromTeam");
    }

}
