package view.remove.controller;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Match;
import Model.Receptionist;
import Model.Stadium;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeStadiumController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removeStadium;

    @FXML
    private Button back;
    
    @FXML
    private Label success;
    
    @FXML
    private ComboBox<Stadium> stadiumList;

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
     * removes stadium and disconnects its connection to every entity
     * @param event remove button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removeStadium(ActionEvent event) throws ListNotSelectedException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Remove Stadium");
	
		alert.setHeaderText("");
		try {
			if(stadiumList.getSelectionModel()==null){
    			alert.setHeaderText("Unable to remove stadium");
    			alert.setContentText("No stadium to remove");
    			alert.show();
    			throw new ListNotSelectedException();
    	}
    	else {
    		Stadium s=stadiumList.getSelectionModel().getSelectedItem();
    		if(s.getReceptionists()!=null) {
    			for(Receptionist r:s.getReceptionists()) {
    				if(r!=null) {
    					r.setWorkingStadium(null);
    				}
    			}
    		}
    		if(s.getTeams()!=null) {
    			for(Team t:s.getTeams()) {
    				if(t!=null) {
    					t.setStadium(null);
    				}
    			}
    		}
    		if(s.getMatches()!=null) {
    			for(Match m:s.getMatches()) {
    				SysData.getInstance().getMatchs().remove(m.getId());
    				for(Team t:SysData.getInstance().getTeams().values()) {
    					if(t!=null) {
    						t.removeMatch(m);
    					}
    				}
    			}
    		}
        	if(s!=null) {
        		SysData.getInstance().getStadiums().remove(s.getId());
        		if(!SysData.getInstance().getStadiums().containsKey(s.getId())) {
        			alert.setHeaderText("Stadium removed");
        			alert.setContentText("Stadium removed successfully.");
        			alert.show();
        		}
        		else {
        			alert.setHeaderText("Unable to remove stadium");
        			alert.setContentText("No stadium to remove");
        			alert.show();
        		}
        	}
    	}
			stadiumList.getItems().removeAll(stadiumList.getItems());
			if(SysData.getInstance().getStadiums().size()>0) {
	    		stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
	    	}
		}catch(ListNotSelectedException e) {
			
		}
    	    
    }
    /**
     * initializes list of stadiums to remvoe
     */
    public void initialize() {
    	if(SysData.getInstance().getStadiums().size()>0) {
    		stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
    	}
    	success=new Label();
    }
}
