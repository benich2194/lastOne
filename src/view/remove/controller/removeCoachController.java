package view.remove.controller;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import Model.Trophy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

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
    private Label labelSuccess;

    @FXML
    private Button removeButton;
    /**
     * goes back to previous windows
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
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
        		throw new ListNotSelectedException("Choose  a coach from coach list");
        	}
        	if(c.getCurrentTeam()!=null) {
        		alert.setHeaderText("Unable to remove coach.");
        		alert.setContentText("Please disconnect coach from it's team.");
        		alert.show();
        	}
        	else {
        		Trophy t=null;
        		for(Trophy tr:SysData.getInstance().getTrophies()) {
        			if(tr!=null&&tr.getOwner() instanceof Coach&&tr.getOwner().equals(c)) {
        				t=tr;
        			}
        		}
        		if(t!=null) {
        			SysData.getInstance().getTrophies().remove(t);
        		}
    	    	SysData.getInstance().getCoachs().remove(c.getId());
    	    	if(!SysData.getInstance().getCoachs().containsKey(c.getId())) {
    	    		labelSuccess.setText("removed coach "+c.getId()+" succesfully!");
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
    		//refresh list
    		coachList.getItems().removeAll(coachList.getItems());
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
