package view;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class coachToTeamController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane coachToTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Coach> coachList;

    @FXML
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;
    /**
     * initializes lists
     */
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getCoachs().values().size()>0) {
    		coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    	}
    
    }
    
    @FXML
    void addCoachToTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Coach To Team");
		alert.setHeaderText("");
		try {
			if(teamList.getSelectionModel().getSelectedItem()!=null&&coachList.getSelectionModel().getSelectedItem()!=null) {
	    		if(SysData.getInstance().addCoachToTeam(coachList.getSelectionModel().getSelectedItem().getId(), teamList.getSelectionModel().getSelectedItem().getId())) {
	    			alert.setHeaderText("Added coach to team.");
	        		alert.setContentText("Coach was added to team successfully.");
	        		alert.show();
	    		}
	    		else {
	    			alert.setHeaderText("failed to add coach to team.");
	        		alert.setContentText("unable to add coach to team.");
	        		alert.show();
	    		}
	    	}
	    	else {
	    		throw new ListNotSelectedException();
	    	}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	//refreshes lists
    	teamList.getItems().removeAll(teamList.getItems());
    	coachList.getItems().removeAll(coachList.getItems());
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getCoachs().values().size()>0) {
    		coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    	}
    }

    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }

}
