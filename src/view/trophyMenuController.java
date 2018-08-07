package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    	WindowManager.openWindow("addTrophy");
    }

    @FXML
    void goToModifyTro(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyTrophy");

    }

    @FXML
    void goToRemoveTrophy(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeTrophy");

    }

}
