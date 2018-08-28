package view.coach.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MaximumReachedException;
import Model.Coach;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class coachRemovePlayerFromFirstTeamController {

    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button back;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private Label labelSuccess;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    /**
     * removes player from first team
     * @param event remove button is pressed
     * @throws ListNotSelectedException 
     * @throws MaximumReachedException 
     */
    @FXML
    void removePlayerFromTeam(ActionEvent event) throws ListNotSelectedException, MaximumReachedException {
    	Coach c=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach()));
    	Team t=c.getCurrentTeam();
    	Player p=playerList.getSelectionModel().getSelectedItem();
    	try {
    		if(p==null) {
        		throw new ListNotSelectedException("Please choose player from list");
        	}
    		t.removePlayer(p);
    		t.addPlayer(p);
    		labelSuccess.setText("player "+p.getId()+" is now a bench player");
    	}catch(ListNotSelectedException e) {
    		
    	}catch(MaximumReachedException e) {
    		
    	}
    	//refresh list
    	playerList.getItems().removeAll(playerList.getItems());
    	if(t!=null) {
    		for(Player pl:t.getPlayers().keySet()) {
    			if(pl!=null&&t.getPlayers().get(pl)) {
    				playerList.getItems().add(pl);
    			}
    		}
    	}
    }
    public void initialize() {
    	Coach c=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach()));
    	Team t=c.getCurrentTeam();
    	if(t!=null) {
    		for(Player p:t.getPlayers().keySet()) {
    			if(p!=null&&t.getPlayers().get(p)) {
    				playerList.getItems().add(p);
    			}
    		}
    	}
    }
}
