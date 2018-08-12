package view;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Receptionist;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class recepToStadiumController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane recepToStadium;

    @FXML
    private Button back;

    @FXML
    private ListView<Receptionist> recepList;

    @FXML
    private ListView<Stadium> stadiumList;

    @FXML
    private Button connectThem;

    @FXML
    void addRecepToStadium(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Receptionist To stadium");
		alert.setHeaderText("");
		try {
			if(recepList.getSelectionModel().getSelectedItem()!=null&&stadiumList.getSelectionModel().getSelectedItem()!=null) {
	    		if(SysData.getInstance().addReceptionistToStadium(recepList.getSelectionModel().getSelectedItem().getId(), stadiumList.getSelectionModel().getSelectedItem().getId())) {
	    			alert.setHeaderText("Added Receptionist to stadium.");
	        		alert.setContentText("Receptionist was added to stadium successfully.");
	        		alert.show();
	    		}
	    		else {
	    			throw new ListNotSelectedException();
	    		}
	    	}
	    	else {
	    		alert.setHeaderText("failed to add Receptionist to stadium.");
	    		alert.setContentText("unable to add Receptionist to stadium.");
	    		alert.show();
	    	}
		}catch(ListNotSelectedException e) {
			
		}
    	
    }
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }
    /**
     * initializes stadium and receptionists lists
     */
    public void initialize() {
    	if(SysData.getInstance().getReceptionists().values().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
    	if(SysData.getInstance().getStadiums().values().size()>0) {
    		stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
    	}
    
    }

}
