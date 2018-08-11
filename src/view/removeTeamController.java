package view;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Player;
import Model.Stadium;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class removeTeamController {

	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removeTeam;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Team> teamList;

    @FXML
    private Button removeButton;
    /**
     * goes to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    
    @FXML
    void removeTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Team");
		alert.setHeaderText("");
		try {
			Team t=teamList.getSelectionModel().getSelectedItem();
	    	if(t==null) {
	    		throw new ListNotSelectedException();
	    	}
	    	Stadium toRemoveTeamFrom = t.getStadium();
	    	//Remove team from it's current Stadium
	    	if(toRemoveTeamFrom!=null)
	    		toRemoveTeamFrom.removeTeam(t);
	    	//remove team from its players
	    	for(Player p:SysData.getInstance().getPlayers().values()) {
	    		if(p!=null) {
	    			if(p.getCurrentTeam()!=null) {
	    				if(p.getCurrentTeam().equals(t)) {
	    					p.setCurrentTeam(null);
	    				}
	    			}
	    		}
	    	}
	    	SysData.getInstance().getTeams().remove(t.getId());
	    	if(SysData.getInstance().getTeams().containsKey(t.getId())) {
	    		alert.setHeaderText("Team wasn't removed");
	    		alert.setContentText("Team wasn't removed successfully.");
	    		alert.show();
	    	}
	    	else {
	    		alert.setHeaderText("Team removed");
	    		alert.setContentText("Team was removed successfully.");
	    		alert.show();
	    		if(SysData.getInstance().getTeams().size()>0) {//refreshes list
	        		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
	        	}
	    	}
		}catch(ListNotSelectedException e) {
			
		}
		if(SysData.getInstance().getTeams().size()>0) {//refreshes list
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
    /**
     * initializes list
     */
    public void initialize() {
    	if(SysData.getInstance().getTeams().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
}
