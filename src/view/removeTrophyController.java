package view;

import Controller.SysData;
import Model.Trophy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class removeTrophyController {

    @FXML
    private AnchorPane removeTrophy;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Trophy> troList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void removeTrophy(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Trophy");
		alert.setHeaderText("");
		if(SysData.getInstance().getTrophies().remove(troList.getSelectionModel().getSelectedItem())) {
			alert.setHeaderText("Removed trophy");
			alert.setContentText("Removed trophy successfully.");
			alert.show();
		}
		else {
			alert.setHeaderText("unable to remove trophy");
			alert.setContentText("Trophy wasn't removed successfully.");
			alert.show();
		}
    }
    public void initialize() {
    	if(SysData.getInstance().getTrophies().size()>0) {
    		troList.getItems().addAll(SysData.getInstance().getTrophies());
    	}
    }
}
