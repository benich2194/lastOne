package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MaximumReachedException;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removePlayerFromFirstTeamController {
	/**
	 * fx fields
	 */
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
     * removes player from first Team players
     * @param event remove button is pressed
     * @throws ListNotSelectedException 
     * @throws MaximumReachedException 
     */
    @FXML
    void removePlayerFromTeam(ActionEvent event) throws ListNotSelectedException, MaximumReachedException {
    	Player p=playerList.getSelectionModel().getSelectedItem();
    	try {
    		if(p==null) {
        		throw new ListNotSelectedException("please choose a player");
        	}
    		Team t=p.getCurrentTeam();
    		t.removePlayer(p);
    		t.addPlayer(p);
    		labelSuccess.setText("player "+p.getId()+" is now a bench player");
    		
    	}catch(ListNotSelectedException e) {
    		
    	}catch(MaximumReachedException e) {
    		
    	}
    	//refresh list
    	playerList.getItems().removeAll(playerList.getItems());
    	for(Team te:SysData.getInstance().getTeams().values()) {
    		if(te!=null&&te.getPlayers()!=null) {
    			for(Player pl:te.getPlayers().keySet()) {
    				if(pl!=null&&te.getPlayers().get(pl)==true) {
    					playerList.getItems().add(pl);
    				}
    			}
    		}
    	}
    }
    public void initialize() {
    	for(Team t:SysData.getInstance().getTeams().values()) {
    		if(t!=null) {
    			for(Player p:t.getPlayers().keySet()) {
    				if(p!=null&&t.getPlayers().get(p)) {
    					playerList.getItems().add(p);
    				}
    			}
    		}
    	}
    }
}
