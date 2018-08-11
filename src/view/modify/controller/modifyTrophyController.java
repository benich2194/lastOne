package view.modify.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class modifyTrophyController {

    @FXML
    private AnchorPane modifyTrophy;

    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
