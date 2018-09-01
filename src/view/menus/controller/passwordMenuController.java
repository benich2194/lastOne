package view.menus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class passwordMenuController {

    @FXML
    private AnchorPane passwordMenu;

    @FXML
    private Button viewPassword;

    @FXML
    private Button changePassword;

    @FXML
    void goToChangePassword(ActionEvent event) {
    	
    }

    @FXML
    void goToViewPasswords(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.VIEW_PASSWORD);
    }

}
