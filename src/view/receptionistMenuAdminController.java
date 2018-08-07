package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


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
    void goToAddRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addReceptionist");
    }

    @FXML
    void goToModifyRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow("modifyReceptionist");
    }

    @FXML
    void goToRemoveRecep(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeReceptionist");
    }

}
