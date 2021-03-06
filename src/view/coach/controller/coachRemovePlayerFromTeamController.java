package view.coach.controller;

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

public class coachRemovePlayerFromTeamController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private Label lblMessage;
    
    @FXML
    private Label labelSuccess;

    //Logged in coach's current team
    private Team t = SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam();
    
    @FXML
    void removePlayerFromTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Player From Team");
		alert.setHeaderText("");
    	
    	try {
    		Player p=playerList.getSelectionModel().getSelectedItem();
    		if(p==null) {
    			throw new ListNotSelectedException("Please choose a player from list:");
    		}
        	if(t!=null&&!t.removePlayer(p)) {
        		alert.setHeaderText("failed to remove Player from team.");
        		alert.setContentText("unable to remove Player from team.");
        		alert.show();
        	}
        	else {
        		p.setCurrentTeam(null);
        		labelSuccess.setText("player "+p.getId()+" was removed succesfully from team!");
        	}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	//refresh list
    	playerList.getItems().removeAll(playerList.getItems());
    	if(SysData.getInstance().getCoachs()!=null) {
    		playerList.getItems().addAll(SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam().getPlayers().keySet());
    	}
    }
    /**
     * initializes list
     */
    public void initialize() {
    	
    	if(t==null)
    	{
    		playerList.setVisible(false);
    		removeButton.setVisible(false);
    		lblMessage.setText("You don't have a team at the moment, Please try again later.");
    	}
    	else
    	{
        	if(SysData.getInstance().getCoachs()!=null) {
        		playerList.getItems().addAll(SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam().getPlayers().keySet());
        	}
    	}
    }

}
