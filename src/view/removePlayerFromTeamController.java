package view;

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
	    	Team t=p.getCurrentTeam();
	    	if(t==null||p==null) {
	    		throw new ListNotSelectedException();
	    	}
	    	if(t.removePlayer(p)) {
	    		p.setCurrentTeam(null);
	    		alert.setHeaderText("Removed Player from team");
	    		alert.setContentText("Removed Player from team successfully.");
	    		alert.show();
	    	}
	    	else {
	    		alert.setHeaderText("Player removal from team failed.");
	    		alert.setContentText("Unable to remove player from team.");
	    		alert.show();
	    	}
		}catch(ListNotSelectedException e) {
			
		}
    	playerList.getItems().removeAll(playerList.getItems());//refreshes list
    	for(Player pl:SysData.getInstance().getPlayers().values()) {
    		if(pl!=null&&pl.getCurrentTeam()!=null) {
    			playerList.getItems().add(pl);
    		}
    	}
    	
    }
    /**
     * initializes player list
     */
    public void initialize() {
    	for(Player p:SysData.getInstance().getPlayers().values()) {
    		if(p!=null&&p.getCurrentTeam()!=null) {
    			playerList.getItems().add(p);
    		}
    	}
    }

}
