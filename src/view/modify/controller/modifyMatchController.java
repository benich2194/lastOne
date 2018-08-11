package view.modify.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.WindowManager;

public class modifyMatchController {

    @FXML
    private AnchorPane modifyMatch;

    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
