package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class subscriptionMenuController {

    @FXML
    private AnchorPane subscriptionMenu;

    @FXML
    private Button addSub;

    @FXML
    private Button removeSub;

    @FXML
    private Button modSub;


    @FXML
    void goToAddSub(ActionEvent event) throws IOException {
    	WindowManager.openWindow("SubToCustomer");
    }

    @FXML
    void goToModSub(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_SUB);
    }

    @FXML
    void goToRemoveSub(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_SUB);
    }

}
