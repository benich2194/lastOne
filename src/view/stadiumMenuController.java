package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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
    	WindowManager.openWindow("addStadium");
    }
    
    @FXML
   void goToModifyStadium(ActionEvent event) throws IOException {
        WindowManager.openWindow("modifyStadium");
    }
    
    @FXML
   void goToViewStadium(ActionEvent event) throws IOException {
        WindowManager.openWindow("viewStadium");
    }

    @FXML
    void goToRemoveStadium(ActionEvent event) throws IOException {
    	WindowManager.openWindow("removeStadium");
    }
}
