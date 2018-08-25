package view.coach.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MaximumReachedException;
import Exceptions.ObjectExistsException;
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
    private Label lblMessage;

    @FXML
    private Label labelSuccess;
    
	private Integer coachID = Integer.parseInt(SysData.getInstance().getUserCoach());
	private Coach ch = SysData.getInstance().getCoachs().get(coachID);
	
    /**
     * adds player to the team
     * @param event add player button pressed
     * @throws ListNotSelectedException
     * @throws ObjectExistsException
     * @throws MaximumReachedException 
     */
    @FXML
    void addPlayerToTeam(ActionEvent event) throws ListNotSelectedException,ObjectExistsException, MaximumReachedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To Team");
		alert.setHeaderText("");

		try {
			Player p=playerList.getSelectionModel().getSelectedItem();
			if(p==null)
				throw new ListNotSelectedException("Please choose from player list");
			if(p!=null) {
	    			if(SysData.getInstance().addPlayerToTeam(p.getId(), SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam().getId())) {
	    				labelSuccess.setText("added player "+p.getId()+" to team successfully");
	    			}
	            		else {
	            			alert.setHeaderText("Failed to add Player to team.");
	                		alert.setContentText("Fails at player seniority.");
	                		alert.show();
	            		}
	        		}
	    		
	    	}catch(ListNotSelectedException e) {
			
		}catch(ObjectExistsException e) {
			
		}catch(MaximumReachedException e) {
			
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
    				System.out.println(p.getCurrentTeam());
    				System.out.println(p);
    			}
    		}
    	}

		
		if(ch.getCurrentTeam()==null)
		{
			playerList.setVisible(false);
			connectThem.setVisible(false);
			lblMessage.setText("You don't have a team at the moment, Please try again later.");
		}
		
    }

}
