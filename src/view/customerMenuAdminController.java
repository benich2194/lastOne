package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    void goToAddCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addCustomer");	
    }

    @FXML
    void goToModifyCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyCustomer");	
    }

    @FXML
    void goToRemoveCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeCustomer");
    }

}
