package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.ObjectExistsException;
import Model.Coach;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

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
    
    @FXML
    private Label labelSuccess;
    /**
     * initializes lists
     */
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		for(Team t:SysData.getInstance().getTeams().values()) {
    			if(t!=null&&t.getCoach()==null) {
    				teamList.getItems().add(t);
    			}
    		}
    	}
    	if(SysData.getInstance().getCoachs().values().size()>0) {
    		coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    	}  
    }
    /**
     * adds coach to a team
     * @param event add coach button is pressed
     * @throws ListNotSelectedException
     * @throws ObjectExistsException 
     */
    @FXML
    void addCoachToTeam(ActionEvent event) throws ListNotSelectedException, ObjectExistsException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Coach To Team");
		alert.setHeaderText("");
		try {
			Coach c=coachList.getSelectionModel().getSelectedItem();
			Team t=teamList.getSelectionModel().getSelectedItem();
			if(teamList.getSelectionModel().getSelectedItem()!=null&&coachList.getSelectionModel().getSelectedItem()!=null) {
	    		if(c.getCurrentTeam()==null&&SysData.getInstance().addCoachToTeam(coachList.getSelectionModel().getSelectedItem().getId(), teamList.getSelectionModel().getSelectedItem().getId())) {
	    			labelSuccess.setText("coach "+c.getId()+" was added to team "+t.getId());
	    		}
	    		else {
	    			if(c.getCurrentTeam()!=null&&c.transferTo(t)) {
	    				labelSuccess.setText("coach "+c.getId()+" was added to team "+t.getId());
	    			}
	    			else {
	    				if(c.getLevel().getLevel()<t.getLevel().getLevel()) {
	    					alert.setHeaderText("failed to add coach to team.");
			        		alert.setContentText("coach level is lower than team level.");
			        		alert.show();
	    				}
	    				else {
	    					alert.setHeaderText("failed to add coach to team.");
			        		alert.setContentText("unable to add coach to team.");
			        		alert.show();
	    				}
	    				
	    			}
	    		}
	    	}
	    	else {
	    		throw new ListNotSelectedException();
	    	}
    	}catch(ListNotSelectedException e) {
    		
    	}catch(ObjectExistsException e) {
    		
    	}
    	//refreshes lists
    	teamList.getItems().removeAll(teamList.getItems());
    	coachList.getItems().removeAll(coachList.getItems());
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		for(Team t:SysData.getInstance().getTeams().values()) {
    			if(t!=null&&t.getCoach()==null) {
    				teamList.getItems().add(t);
    			}
    		}
    	}
    	if(SysData.getInstance().getCoachs().values().size()>0) {
    		coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
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

}
