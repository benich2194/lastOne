package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class matchMenuController {

    @FXML
    private AnchorPane matchMenu;

    @FXML
    private Button addMatch;

    @FXML
    private Button removeMatch;

    @FXML
    private Button modMatch;
    
    @FXML
    private Button viewMatch;

    @FXML
    void goToAddMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_MATCH);
    }

    @FXML
    void goToModifyMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_MATCH);
    }

    @FXML
    void goToRemoveMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_MATCH);
    }
    
    @FXML
    void goToViewMatch(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.VIEW_MATCHES);
    }

}
