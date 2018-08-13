package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class coachAddPlayerToTeamController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane playerToTeam;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private Button connectThem;
    
    @FXML
    private ComboBox<Boolean> firstList;

    @FXML
    private Label lblMessage;
    
	private Integer coachID = Integer.parseInt(SysData.getInstance().getUserCoach());
	private Coach ch = SysData.getInstance().getCoachs().get(coachID);
	
    /**
     * adds player to the team
     * @param event add player button pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void addPlayerToTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To Team");
		alert.setHeaderText("");

		try {
			if(playerList.getSelectionModel().getSelectedItem()==null||firstList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			if(playerList.getSelectionModel().getSelectedItem()!=null) {
	    		if(firstList.getSelectionModel().getSelectedItem()) {
	    			if(SysData.getInstance().addPlayerToTeamFirstPlayers(playerList.getSelectionModel().getSelectedItem().getId(), ch.getCurrentTeam().getId())) {
	        			alert.setHeaderText("Added Player to first team players.");
	            		alert.setContentText("Player was added to first team players successfully.");
	            		alert.show();
	        		}
	        		else {
	        			if(SysData.getInstance().addPlayerToTeam(playerList.getSelectionModel().getSelectedItem().getId(), ch.getCurrentTeam().getId())) {
	            			alert.setHeaderText("Added Player to team.");
	                		alert.setContentText("Player was added to team successfully.");
	                		alert.show();
	            		}
	            		else {
	            			alert.setHeaderText("Failed to add Player to team.");
	                		alert.setContentText("unable to add Player to team, select a player and a team please.");
	                		alert.show();
	            		}
	        		}
	    		}
	    		
	    	}
	    	else {
	    		throw new ListNotSelectedException();
	    	}
		}catch(ListNotSelectedException e) {
			
		}
		
		//refreshes lists
    	playerList.getItems().removeAll(playerList.getItems());
    	if(SysData.getInstance().getPlayers()!=null) {
    		for(Player p:SysData.getInstance().getPlayers().values()) {
    			if(p!=null&&p.getCurrentTeam()==null) {
    				playerList.getItems().add(p);
    			}
    		}
    	}
    }
    /**
     * initializes lists
     */
    public void initialize() {
    	if(SysData.getInstance().getPlayers()!=null) {
    		for(Player p:SysData.getInstance().getPlayers().values()) {
    			if(p!=null&&p.getCurrentTeam()==null) {
    				playerList.getItems().add(p);
    			}
    		}
    	}
    	firstList.getItems().add(true);
    	firstList.getItems().add(false);
		
		if(ch.getCurrentTeam()==null)
		{
			playerList.setVisible(false);
			firstList.setVisible(false);
			connectThem.setVisible(false);
			lblMessage.setText("You don't have a team at the moment, Please try again later.");
		}
		
    }

}
