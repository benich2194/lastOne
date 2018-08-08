package view;

import Controller.SysData;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class customerJoinMatchController {

    @FXML
    private AnchorPane joinMatch;

    @FXML
    private Button joinButton;

    @FXML
    private ListView<Match> matchList;

    @FXML
    void joinMatch(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Join Match");
		alert.setHeaderText("");
    	if(SysData.getInstance().addCustomerToMatch(SysData.getInstance().getUserCustomer(), matchList.getSelectionModel().getSelectedItem().getId())) {
    		alert.setHeaderText("Joined Match Successfully");
    		alert.setContentText("You were added to match "+matchList.getSelectionModel().getSelectedItem().getId()+".");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Failed to join match.");
    		alert.setContentText("You were not added to match "+matchList.getSelectionModel().getSelectedItem().getId()+".");
    		alert.show();
    	}
    }
    public void initialize() {
 		if(SysData.getInstance().getMatchs().size()>0) {
 			matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
 		}
 		
 }

}
