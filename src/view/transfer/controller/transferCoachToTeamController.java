package view.transfer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;


public class transferCoachToTeamController {

    @FXML
    private AnchorPane modifyStadium;

    @FXML
    private Button buttonTransfer;

    @FXML
    private Button back;

    @FXML
    private ListView<Coach> coachList;

    @FXML
    private ListView<Team> sysTeams;


  
    public void initialize() {
    	if(!SysData.getInstance().getCoachs().isEmpty())
    	{
    		ObservableList<Coach> allCoaches = FXCollections.observableArrayList(SysData.getInstance().getCoachs().values());
    		coachList.setItems(allCoaches);
    	}
        
    }//
    
    @FXML
    void showTeams(ActionEvent event) {
    	try {
	    	if(!SysData.getInstance().getTeams().values().isEmpty()) {
	    		if(coachList.getSelectionModel().getSelectedItem()==null)
	    			throw new ListNotSelectedException();
	    		Coach cc = coachList.getSelectionModel().getSelectedItem();
		    	ArrayList<Team> teamsTocoach = new ArrayList<Team>(SysData.getInstance().getTeams().values());
		    	//If coach has a current team, remove it from the team list it can be transfered to 
		    	if(cc.getCurrentTeam()!=null)
		    		teamsTocoach.remove(cc.getCurrentTeam());
		    	//Remove teams that have a coach and show only teams without one
		    	Iterator<Team> it = teamsTocoach.iterator();
		    	while (it.hasNext()) {
			    	Team check = it.next();	
			    	if(check.getCoach()!=null)
			    		it.remove();
		    	}
		    			
		        ObservableList<Team> allTeams = FXCollections.observableArrayList(teamsTocoach);
		        sysTeams.setItems(allTeams);
	    	}
    	}
    	catch (ListNotSelectedException e) {
    	
    	}
    }
    


    @FXML
    void transferThem(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Transfer Coach To Team");
		alert.setHeaderText("");
    	try {
	    		if(sysTeams.getSelectionModel().getSelectedItem()==null)
	    			throw new ListNotSelectedException();
	    		Coach cc = coachList.getSelectionModel().getSelectedItem();
	    		Team tt = sysTeams.getSelectionModel().getSelectedItem();
	    		if(cc.transferTo(tt)) {
					alert.setHeaderText("Successfully Transfered Coach.");
					alert.setContentText("Coach has been transfered to team.");
					alert.show();
	    		}
	    		else
	    		{
					alert.setHeaderText("Unable to transfer Coach");
					alert.setContentText("Coach hasn't been transfered to teams");
					alert.show();
	    		}
    	}
    	catch (ListNotSelectedException e) {
        	
    	}
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
