package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Coach;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class removeCoachFromTeamController {
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
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    /**
     * removes coach from its team
     * @param event	remove button is pressed
     * @throws MissingInputException 
     */
    @FXML
    void removeCoach(ActionEvent event) throws ListNotSelectedException {
    	try {
    		Coach c=coachList.getSelectionModel().getSelectedItem();
        	if(c!=null) {
        		Team t=c.getCurrentTeam();
        		c.getTeams().add(t);
        		c.setCurrentTeam(null);
        		t.setCoach(null);
        	}
        	else {
        		throw new ListNotSelectedException();
        	}
        	if(SysData.getInstance().getCoachs()!=null) {
        		for(Coach co:SysData.getInstance().getCoachs().values()) {
        			if(co!=null) {
        				if(co.getCurrentTeam()!=null) {
        					coachList.getItems().add(co);
        				}
        			}
        		}
        	}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	
    }
    /**
     * initializes coach with team list
     */
    public void initialize() {
    	if(SysData.getInstance().getCoachs()!=null) {
    		for(Coach c:SysData.getInstance().getCoachs().values()) {
    			if(c!=null) {
    				if(c.getCurrentTeam()!=null) {
    					coachList.getItems().add(c);
    				}
    			}
    		}
    	}
    }

}
