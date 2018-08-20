package view.customer.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.NoValidSubscriptionException;
import Exceptions.ObjectExistsException;
import Exceptions.ObjectNotExistException;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class customerJoinMatchController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane joinMatch;

    @FXML
    private Button joinButton;

    @FXML
    private ListView<Match> matchList;

    @FXML
    void joinMatch(ActionEvent event) throws ListNotSelectedException, NoValidSubscriptionException, ObjectExistsException, ObjectNotExistException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Join Match");
		alert.setHeaderText("");
		try {
			if(matchList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			if(SysData.getInstance().addCustomerToMatch(SysData.getInstance().getUserCustomer(), matchList.getSelectionModel().getSelectedItem().getId())) {
	    		alert.setHeaderText("Joined Match Successfully");
	    		alert.setContentText("You were added to match "+matchList.getSelectionModel().getSelectedItem().getId()+".");
	    		alert.show();
	    	}
	    	else {
	    		
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(NoValidSubscriptionException e) {
			
		}catch(ObjectNotExistException e) {
			
		}
    	
    	//refresh list
    	matchList.getItems().removeAll(matchList.getItems());
    	if(SysData.getInstance().getMatchs().size()>0) {
 			matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
 		}
    }
    /**
     * initialize list
     */
    public void initialize() {
 		if(SysData.getInstance().getMatchs().size()>0) {
 			matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
 		}
 		
 }

}
