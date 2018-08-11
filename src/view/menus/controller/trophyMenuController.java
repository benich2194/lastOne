package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class trophyMenuController {

    @FXML
    private AnchorPane trophyMenu;

    @FXML
    private Button addTrophy;

    @FXML
    private Button removeTrophy;

    @FXML
    private Button modTrophy;


    @FXML
    void goToAddTro(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.ADD_TROPHY);
    }

    @FXML
    void goToModifyTro(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.MODIFY_TROPHY);

    }

    @FXML
    void goToRemoveTrophy(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.REMOVE_TROPHY);

    }

}
