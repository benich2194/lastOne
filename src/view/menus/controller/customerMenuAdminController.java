package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class customerMenuAdminController {

    @FXML
    private AnchorPane customerMenuAdmin;

    @FXML
    private Button addCustomer;

    @FXML
    private Button removeCustomer;

    @FXML
    private Button modCustomer;
    
    @FXML
    private Button viewCustomer;

    @FXML
    void goToAddCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_CUSTOMER);	
    }

    @FXML
    void goToModifyCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_CUSTOMER);	
    }

    @FXML
    void goToRemoveCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_CUSTOMER);
    }

    @FXML
    void goToViewCustomer(ActionEvent event) {
    	WindowManager.openWindow("viewCustomer");
    }
}
