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
import javafx.scene.control.Label;
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
        		labelSuccess.setText("coach "+c.getId()+" was removed successfully from team "+t.getId());
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
    	coachList.getItems().removeAll(coachList.getItems());//refreshes list
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
