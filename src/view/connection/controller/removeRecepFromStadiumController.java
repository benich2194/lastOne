package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Receptionist;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeRecepFromStadiumController {

    @FXML
    private AnchorPane removeRecepFromStadium;

    @FXML
    private Button back;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<Receptionist> recepList;

    @FXML
    private Label labelSuccess;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    /**
     * removes receptionist from its stadium
     * @param event remove receptionist button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removeRecepFromStadium(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Receptionist From Stadium");
		alert.setHeaderText("");
    	Receptionist recep=recepList.getSelectionModel().getSelectedItem();
    	try {
    		if(recep==null) {
        		throw new ListNotSelectedException("choose receptionist");
        	}
    		else {
    			Stadium s=recep.getWorkingStadium();
    			if(s.removeReceptionist(recep)) {
    				recep.setWorkingStadium(null);
    				labelSuccess.setText("Receptionist "+recep.getId()+" is removed succesfully from stadium "+s.getId());
    			}
    			else {
    				alert.setHeaderText("failed to remove receptionist from stadium.");
	        		alert.setContentText("unable to remove receptionist from stadium.");
	        		alert.show();
    			}
    			
    			
    		}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	//refreshes list
    	recepList.getItems().removeAll(recepList.getItems());
    	if(SysData.getInstance().getReceptionists()!=null) {
    		for(Receptionist r:SysData.getInstance().getReceptionists().values()) {
    			if(r!=null&&r.getWorkingStadium()!=null) {
    				recepList.getItems().add(r);
    			}
    		}
    	}
    }
    /**
     * initializes list
     */
    public void initialize() {
    	if(SysData.getInstance().getReceptionists()!=null) {
    		for(Receptionist r:SysData.getInstance().getReceptionists().values()) {
    			if(r!=null&&r.getWorkingStadium()!=null) {
    				recepList.getItems().add(r);
    			}
    		}
    	}
    }

}
