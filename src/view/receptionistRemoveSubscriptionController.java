package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class receptionistRemoveSubscriptionController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane removeSub;

    @FXML
    private ListView<Subscription> subList;

    @FXML
    private Button removeButton;

    @FXML
    void removeSubscription(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Subscription");
		alert.setHeaderText("");
    	try {
    		if(subList.getSelectionModel().getSelectedItem()!=null) {
        		if(SysData.getInstance().removeSubscription(subList.getSelectionModel().getSelectedItem().getId())) {
        			alert.setHeaderText("Subscription was removed");
            		alert.setContentText("Subscription was removed successfully.");
            		alert.show();
        		}
        		
        	}
    		else {
    			throw new ListNotSelectedException();
    		}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	subList.getItems().removeAll(subList.getItems());//refreshes list
    	if(SysData.getInstance().getReceptionists()!=null) {
    		if(SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep()))!=null) {
    			if(SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep())).getSubscriptions()!=null) {
    				subList.getItems().addAll(SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep())).getSubscriptions());
    			}
    		}
    	}
    }
    //initializes list
    public void initialize() {
    	if(SysData.getInstance().getReceptionists()!=null) {
    		if(SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep()))!=null) {
    			if(SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep())).getSubscriptions()!=null) {
    				subList.getItems().addAll(SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep())).getSubscriptions());
    			}
    		}
    	}
    }

}
