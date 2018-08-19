package view.remove.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import Model.Customer;
import Model.Player;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeSubController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane removeSub;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Subscription> subList;
    
    @FXML
    private Label labelSuccess;

    @FXML
    private Button removeButton;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    /**
     * removes subscription from data base
     * @param event remove button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removeSubscription(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Subscription");
		alert.setHeaderText("");
		try {
			if(subList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			Subscription s=subList.getSelectionModel().getSelectedItem();
			if(SysData.getInstance().removeSubscription(s.getId())) {
	    		labelSuccess.setText("subscription "+s.getId()+" was removed succesfully!");
	    	}
	    	else {
	    		alert.setHeaderText("Subscription wasn't removed");
	    		alert.setContentText("Subscription wasn't removed successfully.");
	    		alert.show();
	    	}
		}catch(ListNotSelectedException e) {
			
		}
    	subList.getItems().removeAll(subList.getItems());
    	if(SysData.getInstance().getCustomers().values()!=null) {//refreshes list
    		for(Customer c:SysData.getInstance().getCustomers().values()) {
    			if(c!=null&&c.getSubscriptions()!=null) {
    				for(Subscription s:c.getSubscriptions()) {
    					if(s!=null) {
    						subList.getItems().add(s);
    					}
    				}
    			}
    		}
    	}
    	
    }
    public void initialize() {
    	if(SysData.getInstance().getCustomers().values()!=null) {
    		for(Customer c:SysData.getInstance().getCustomers().values()) {
    			if(c!=null&&c.getSubscriptions()!=null) {
    				for(Subscription s:c.getSubscriptions()) {
    					if(s!=null) {
    						subList.getItems().add(s);
    					}
    				}
    			}
    		}
    	}
    }
}
