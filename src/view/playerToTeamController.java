package view;

import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class playerToTeamController {

    @FXML
    private AnchorPane playerToTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;

    @FXML
    void addPlayerToTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To Team");
		alert.setHeaderText("");
    	if(teamList.getSelectionModel().getSelectedItem()!=null&&playerList.getSelectionModel().getSelectedItem()!=null) {
    		if(SysData.getInstance().addPlayerToTeam(playerList.getSelectionModel().getSelectedItem().getId(), teamList.getSelectionModel().getSelectedItem().getId())) {
    			alert.setHeaderText("Added Player to team.");
        		alert.setContentText("Player was added to team successfully.");
        		alert.show();
    		}
    		else {
    			alert.setHeaderText("failed to add Player to team.");
        		alert.setContentText("unable to add Player to team, select a player and a team please.");
        		alert.show();
    		}
    	}
    	else {
    		alert.setHeaderText("failed to add Player to team.");
    		alert.setContentText("unable to add Player to team.");
    		alert.show();
    	}
    }

    @FXML
    void goBack(ActionEvent event) {

    }
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getPlayers().values().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
    
    }

}
