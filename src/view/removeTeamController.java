package view;

import java.io.IOException;

import Controller.SysData;
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


    @FXML
    private AnchorPane removeTeam;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Team> teamList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    @FXML
    void removeTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Team");
		alert.setHeaderText("");
    	Team t=teamList.getSelectionModel().getSelectedItem();
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
    	}
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTeams().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
}
