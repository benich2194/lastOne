package view.view.controller;

import java.util.ArrayList;
import java.util.Map;

import Controller.SysData;
import Model.Match;
import Model.Player;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class viewTeamsController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane modifyTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Team> listViewTeams;

    @FXML
    private ListView<Player> teamPlayersList;
    
    @FXML
    private ListView<Match> teamsMatches;

    @FXML
    private ListView<Player> teamPlayersListFirst;
    
    public void initialize() {
    	if(!SysData.getInstance().getTeams().isEmpty())
    	{
    		ObservableList<Team> allTeams = FXCollections.observableArrayList(SysData.getInstance().getTeams().values());
    		listViewTeams.setItems(allTeams);
    	}
    }
   
    
    //Action Event when team is selected in listView
    @FXML
    void showDetails(MouseEvent event) {
    	Team clicked = listViewTeams.getSelectionModel().getSelectedItem();
    	showRowDetails(clicked);
    }
    
    private void showRowDetails(Team item) {
    	ArrayList<Player> allPlys = new ArrayList<Player>(item.getPlayers().keySet());
        ObservableList<Player> teamplayers = FXCollections.observableArrayList(allPlys);
        ObservableList<Match> matchestm = FXCollections.observableArrayList(item.getMatches());
        if(!teamplayers.isEmpty())
        	teamPlayersList.setItems(teamplayers);
        
        if(!matchestm.isEmpty())
        	teamsMatches.setItems(matchestm);

        //Display First Team Players
        //Remove everyone who is not a first team player, from allPlys ArrayList
        for(Map.Entry<Player, Boolean> entry: item.getPlayers().entrySet()) {
        	if(!entry.getValue())
        		allPlys.remove(entry.getKey());
        }
        
        ObservableList<Player> firstTeamplayers = FXCollections.observableArrayList(allPlys);
        if(!firstTeamplayers.isEmpty())
        	teamPlayersList.setItems(firstTeamplayers);
    }
    
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
}
