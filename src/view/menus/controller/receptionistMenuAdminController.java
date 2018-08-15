package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;


public class receptionistMenuAdminController {
	/**
	 * fx fields
	 */
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
    /**
     * opens add receptionist
     * @param event add receptionist button is pressed
     */
    @FXML
    void goToAddRecep(ActionEvent event){
    	WindowManager.openWindow(NameToWindow.ADD_RECEPTIONIST);
    }
    /**
     * opens modify receptionist
     * @param event modify receptionist button is pressed
     */
    @FXML
    void goToModifyRecep(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.MODIFY_RECEPTIONIST);
    }
    /**
     * opens view receptionists
     * @param event view receptionists button is pressed
     */
    @FXML
    void goToViewRecep(ActionEvent event) {
    	WindowManager.openWindow("viewReceptionist");
    }
    /**
     * opens remove receptionist
     * @param event remove receptionist button is pressed
     */
    @FXML
    void goToRemoveRecep(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.REMOVE_RECEPTIONIST);
    }
    /**
     * opens remove receptionist from stadium
     * @param event remove receptionist from stadium button is pressed
     */
    @FXML
    void goToRemoveFromStadium(ActionEvent event) {
    	WindowManager.openWindow("removeRecepFromStadium");
    }

}
