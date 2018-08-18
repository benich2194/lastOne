package view.view.controller;

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
        ObservableList<Player> teamplayers = FXCollections.observableArrayList(item.getPlayers().keySet());
        ObservableList<Match> matchestm = FXCollections.observableArrayList(item.getMatches());
        if(!teamplayers.isEmpty())
        	teamPlayersList.setItems(teamplayers);
        
        if(!matchestm.isEmpty())
        	teamsMatches.setItems(matchestm);
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
}
