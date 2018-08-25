package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

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
    private Button viewTeams;

    @FXML
    void goToAddTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_TEAM);
    }

    @FXML
    void goToModifyTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_TEAM);
    }

    @FXML
    void goToRemoveTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_TEAM);
    }
    @FXML
    void goToViewTeams(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.VIEW_TEAM);
    }

}
