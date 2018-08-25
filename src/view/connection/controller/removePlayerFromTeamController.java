package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Player;
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

public class removePlayerFromTeamController {
	/**
	 * fxml fields
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
     * removes player from his team
     * @param event remove player button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removePlayerFromTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Player From Team");
		alert.setHeaderText("");
		try {
			Player p=playerList.getSelectionModel().getSelectedItem();
	    	if(p==null) {
	    		throw new ListNotSelectedException("Choose player from list");
	    	}
	    		Team t=p.getCurrentTeam();
	    		if(t!=null&&p!=null) {
	    			t.removePlayer(p);
	    			p.setCurrentTeam(null);
		    		labelSuccess.setText("removed player "+p.getId()+" from team "+t.getId());
	    		}
	    		
		}catch(ListNotSelectedException e) {
			
		}
    	playerList.getItems().removeAll(playerList.getItems());//refreshes list
    	   for(Team t:SysData.getInstance().getTeams().values()) {
    	    	if(t!=null) {
    	    		for(Player p:t.getPlayers().keySet()) {
    	    			if(p!=null) {
    	    				playerList.getItems().add(p);
    	    			}
    	    		}
    	    	}
    	    }
    	
    }
    /**
     * initializes player list
     */
    public void initialize() {
    for(Team t:SysData.getInstance().getTeams().values()) {
    	if(t!=null) {
    		for(Player p:t.getPlayers().keySet()) {
    			if(p!=null) {
    				playerList.getItems().add(p);
    			}
    		}
    	}
    }
    }
}
