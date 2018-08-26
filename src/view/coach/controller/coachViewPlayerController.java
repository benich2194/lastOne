package view.coach.controller;

import java.util.ArrayList;
import java.util.Map;

import Controller.SysData;
import Model.Coach;
import Model.Player;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class coachViewPlayerController {

    @FXML
    private AnchorPane modifyPlayer;

    @FXML
    private Button back;

    @FXML
    private ListView<Player> playerListView;
    
    @FXML
    private ListView<Player> firstplysList;

	private Integer coachID = Integer.parseInt(SysData.getInstance().getUserCoach());
	private Coach ch = SysData.getInstance().getCoachs().get(coachID);
	
    public void initialize() {
    	//Check if logged in coach is connected to a team
    	Team t = ch.getCurrentTeam();
    	if(t!=null)
    	{
    		ObservableList<Player> playersOnTeam = FXCollections.observableArrayList(t.getPlayers().keySet());
    		playerListView.setItems(playersOnTeam);
    		
        	//Make a list of first team players on selected team
        	ArrayList<Player> plys = new ArrayList<Player>();
        	for(Map.Entry<Player,Boolean> entry: t.getPlayers().entrySet()) {
        		if(entry.getValue()) //If he's value is true (then hes a first team player) add him to 'plys'
        			plys.add(entry.getKey());
        	}
            
            ObservableList<Player> firstTeamplayers = FXCollections.observableArrayList(plys);
            if(!firstTeamplayers.isEmpty())
            	firstplysList.setItems(firstTeamplayers);
    	}
        
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}