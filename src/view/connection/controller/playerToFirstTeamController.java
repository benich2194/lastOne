package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class playerToFirstTeamController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane playerToFirstTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;
    /**
     * adds player to first team players
     * @param event add button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void addPlayerToFirstTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To First Team Players");
		alert.setHeaderText("");
		try {
			if(teamList.getSelectionModel().getSelectedItem()!=null&&playerList.getSelectionModel().getSelectedItem()!=null) {
	    		if(SysData.getInstance().addPlayerToTeamFirstPlayers(playerList.getSelectionModel().getSelectedItem().getId(),teamList.getSelectionModel().getSelectedItem().getId())){
	    			alert.setHeaderText("Added Player to First Team Players.");
	        		alert.setContentText("Player was added to First Team Players successfully.");
	        		alert.show();
	    		}
	    		else {
	    			alert.setHeaderText("failed to add Player to First Team Players.");
	        		alert.setContentText("unable to add Player to First Team Players, select a player and a team please.");
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
    	playerList.getItems().removeAll(playerList.getItems());
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getPlayers().values().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
    }
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getPlayers().values().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
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
