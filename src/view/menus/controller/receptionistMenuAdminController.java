package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;


public class receptionistMenuAdminController {

    @FXML
    private AnchorPane recepMenu;

    @FXML
    private Button addRecep;

    @FXML
    private Button removeRecep;

    @FXML
    private Button modRecep;
    
    @FXML
    private Button viewRecep;


    @FXML
    void goToAddRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_RECEPTIONIST);
    }

    @FXML
    void goToModifyRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_RECEPTIONIST);
    }
    
    @FXML
    void goToViewRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow("viewReceptionist");
    }

    @FXML
    void goToRemoveRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_RECEPTIONIST);
    }

}
