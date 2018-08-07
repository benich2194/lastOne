package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class matchMenuController {

    @FXML
    private AnchorPane matchMenu;

    @FXML
    private Button addMatch;

    @FXML
    private Button removeMatch;

    @FXML
    private Button modMatch;

    @FXML
    void goToAddMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addMatch");
    }

    @FXML
    void goToModifyMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyMatch");
    }

    @FXML
    void goToRemoveMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeMatch");
    }

}
