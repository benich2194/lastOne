package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

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
    private Button sortFTPButton;

    @FXML
    void goToAddPlayer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_PLAYER);

    }

    @FXML
    void goToModifyPlayer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_PLAYER);

    }

    @FXML
    void goToRemovePlayer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_PLAYER);

    }
    
    @FXML
    void goToViewPlayers(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.VIEW_PLAYER);
    }
    

    @FXML
    void goToSortFirstTeamPlayers(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.SORT_FTPADMIN);
    }
    
    @FXML
    void goToRemovePlayerFromTeam(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.REMOVE_PLAYERFROMTEAM);
    }

}
