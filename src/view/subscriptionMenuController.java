package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    private Button back;

    @FXML
    void goBack(ActionEvent event) throws IOException {
       	WindowManager.goBack();
    }

    @FXML
    void goToAddSub(ActionEvent event) throws IOException {
    	WindowManager.openWindow("SubToCustomer");
    }

    @FXML
    void goToModSub(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifySub");
    }

    @FXML
    void goToRemoveSub(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeSub");
    }

}
