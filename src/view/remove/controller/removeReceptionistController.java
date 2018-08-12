package view.remove.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Receptionist;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;


public class removeReceptionistController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane removeReceptionist;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Receptionist> recepList;

    @FXML
    private Button removeButton;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }
    
    @FXML
    void removeReceptionist(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Receptionist");
		alert.setHeaderText("");
		try {
			Receptionist c=recepList.getSelectionModel().getSelectedItem();
	    	if(c==null)
	    		throw new ListNotSelectedException();
	    	//remove receptionist from its working stadium
	    	if(c!=null) {
	    		if(c.getWorkingStadium()!=null) {
	    			if(c.getWorkingStadium().getReceptionists()!=null) {
	    				if(c.getWorkingStadium().getReceptionists().contains(c)) {
	    					c.getWorkingStadium().removeReceptionist(c);
	    				}
	    			}
	    		}
	    	}
	    	for(Subscription s:c.getSubscriptions()) {//remove its subscriptions from data base and customers
	    		if(s!=null) {
	    			SysData.getInstance().removeSubscription(s.getId());
	    		}
	    	}
	    	SysData.getInstance().getReceptionists().remove(c.getId());
	    	if(!SysData.getInstance().getReceptionists().containsKey(c.getId())) {
	    		alert.setHeaderText("Removed Receptionist");
	    		alert.setContentText("Removed Receptionist successfully.");
	    		alert.show();
	    	}
	    	else {
	    		alert.setHeaderText("Unable to remove Receptionist.");
	    		alert.setContentText("Cannot remove Receptionist from database.");
	    		alert.show();
	    	}
		}catch(ListNotSelectedException e) {
			
		}
		recepList.getItems().removeAll(recepList.getItems());//refreshes list
		if(SysData.getInstance().getReceptionists().size()>0) {
			recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
		}
    }
    /**
     * initializes receptionists list
     */
    public void initialize() {
		if(SysData.getInstance().getReceptionists().size()>0) {
			recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
		}
		
}
}
