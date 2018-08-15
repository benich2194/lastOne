package view;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.ObjectExistsException;
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
    private Label labelSuccess;
    
    @FXML
    private Button connectThem;
    /**
     * adds receptionist to stadium
     * @param event add receptionist button is pressed
     * @throws ListNotSelectedException one of the lists is not selected
     * @throws ObjectExistsException 
     */
    @FXML
    void addRecepToStadium(ActionEvent event) throws ListNotSelectedException, ObjectExistsException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Receptionist To stadium");
		alert.setHeaderText("");
		Receptionist r=recepList.getSelectionModel().getSelectedItem();
		Stadium s=stadiumList.getSelectionModel().getSelectedItem();
		try {
			if(r!=null&&s!=null) {
				if(r.getWorkingStadium()!=null&&r.getWorkingStadium().equals(s)) {
					throw new ObjectExistsException("Receptionist already belongs to this stadium.");
				}
	    		if(SysData.getInstance().addReceptionistToStadium(r.getId(), s.getId())) {
	    			labelSuccess.setText("added receptionist "+r.getId()+" to stadium "+s.getId());
	    		}
	    		else {
	    			alert.setHeaderText("failed to add Receptionist to stadium.");
		    		alert.setContentText("unable to add Receptionist to stadium.");
		    		alert.show();
	    		}
	    	}
	    	else {
	    		throw new ListNotSelectedException();
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(ObjectExistsException e) {
			
		}
		if(SysData.getInstance().getReceptionists().values().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
		recepList.getItems().removeAll(recepList.getItems());
		stadiumList.getItems().removeAll(stadiumList.getItems());
		if(SysData.getInstance().getReceptionists().values().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
    	if(SysData.getInstance().getStadiums().values().size()>0) {
    		for(Stadium y:SysData.getInstance().getStadiums().values()) {
    			if(y!=null) {
    				if(y.getReceptionists().size()<utils.Constants.MAX_RESEPTIONISTS_FOR_STADIUM) {
    					stadiumList.getItems().add(y);
    				}
    			}
    		}
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
    		for(Stadium s:SysData.getInstance().getStadiums().values()) {
    			if(s!=null) {
    				if(s.getReceptionists().size()<utils.Constants.MAX_RESEPTIONISTS_FOR_STADIUM) {
    					stadiumList.getItems().add(s);
    				}
    			}
    		}
    	}
    
    }

}
