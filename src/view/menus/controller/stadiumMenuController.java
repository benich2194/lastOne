package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class stadiumMenuController {

    @FXML
    private AnchorPane stadiumMenu;

    @FXML
    private Button addStadium;

    @FXML
    private Button removeStadium;

    @FXML
    private Button modStadium;
    
    @FXML
    private Button viewStadium;


    @FXML
    void goToAddStadium(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_STADIUM);
    }
    
    @FXML
   void goToModifyStadium(ActionEvent event) throws IOException {
        WindowManager.openWindow(NameToWindow.MODIFY_STADIUM);
    }
    
    @FXML
   void goToViewStadium(ActionEvent event) throws IOException {
        WindowManager.openWindow(NameToWindow.VIEW_STADIUM);
    }

    @FXML
    void goToRemoveStadium(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_STADIUM);
    }
}
