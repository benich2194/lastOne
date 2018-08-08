package view;

import Controller.SysData;
import Model.Coach;
import Model.Player;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class removeSubController {

    @FXML
    private AnchorPane removeSub;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Subscription> subList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void removeSubscription(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Subscription");
		alert.setHeaderText("");
    	if(SysData.getInstance().removeSubscription(subList.getSelectionModel().getSelectedItem().getId())) {
    		alert.setHeaderText("Subscription was removed");
    		alert.setContentText("Subscription was removed successfully.");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Subscription wasn't removed");
    		alert.setContentText("Subscription wasn't removed successfully.");
    		alert.show();
    	}
    	
    }
}
