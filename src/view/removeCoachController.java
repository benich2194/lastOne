package view;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class removeCoachController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removeCoach;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Coach> coachList;

    @FXML
    private Button removeButton;
    /**
     * goes back to previous windows
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.openWindow("coachModif");
    }
    /**
     * removes coach selected from list
     * @param event remove button is pressed
     * @throws ListNotSelectedException if list wasn't selected
     */
    @FXML
    void removeCoach(ActionEvent event) throws ListNotSelectedException {

    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Coach");
		alert.setHeaderText("");
    	try {
    		Coach c=coachList.getSelectionModel().getSelectedItem();
    		if(c==null) {
        		throw new ListNotSelectedException();
        	}
        	if(c.getCurrentTeam()!=null) {
        		alert.setHeaderText("Unable to remove coach.");
        		alert.setContentText("Please disconnect coach from it's team.");
        		alert.show();
        	}
        	else {
    	    	SysData.getInstance().getCoachs().remove(c.getId());
    	    	if(!SysData.getInstance().getCoachs().containsKey(c.getId())) {
    	    		alert.setHeaderText("Removed coach");
    	    		alert.setContentText("Removed coach successfully.");
    	    		alert.show();
    	    	}
    	    	else {
    	    		alert.setHeaderText("Unable to remove coach.");
    	    		alert.setContentText("Cannot remove coach from database.");
    	    		alert.show();
    	    	}
        	}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	finally {
    		if(SysData.getInstance().getCoachs().size()>0) {
    			coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    		}
    	}
    	
    }
    /**
     * initializes fxml
     */
    public void initialize() {
    		if(SysData.getInstance().getCoachs().size()>0) {
    			coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    		}
    		
    }
}
