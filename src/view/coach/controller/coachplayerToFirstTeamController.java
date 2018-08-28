package view.coach.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MaximumReachedException;
import Exceptions.ObjectExistsException;
import Model.Coach;
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

public class coachplayerToFirstTeamController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane playerToFirstTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Player> playerList;
    
    @FXML
    private Label lblMessage;

    @FXML
    private Button connectThem;
    
    @FXML
    private Label labelSuccess;
    
    //Logged in coach ID
	private Integer coachID = Integer.parseInt(SysData.getInstance().getUserCoach());
	
	//Logged in coach
	private Coach ch = SysData.getInstance().getCoachs().get(coachID);
	
	
    /**
     * adds player to first team players
     * @param event add button is pressed
     * @throws ListNotSelectedException
     * @throws ObjectExistsException 
     * @throws MaximumReachedException 
     */
    @FXML
    void addPlayerToFirstTeam(ActionEvent event) throws ListNotSelectedException, MaximumReachedException, ObjectExistsException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To First Team Players");
		alert.setHeaderText("");
		try {
			Player p=playerList.getSelectionModel().getSelectedItem();
			if(p!=null) {
	    		if(SysData.getInstance().addPlayerToTeamFirstPlayers(p.getId(),ch.getCurrentTeam().getId())){
	    			labelSuccess.setText("player" +p.getId()+" was added succesfully to first team players!");
	        		//refreshses list
	        		playerList.getItems().removeAll(playerList.getItems());
	        		Team t=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam();
	        		if(t!=null) {
	        			if(t.getPlayers()!=null) {
	        				for(Player pl:t.getPlayers().keySet()) {
	        					if(pl!=null&&!t.getPlayers().get(pl)) {
	        						playerList.getItems().add(pl);
	        					}
	        				}
	        			}
	        		}
	    		}
	    		else {
	    			alert.setHeaderText("Failed to add Player to First Team Players.");
	        		alert.setContentText("Unable to add Player to First Team Players, Please select a player.");
	        		alert.show();
	    		}
	    	}
	    	else {
	    		throw new ListNotSelectedException();
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(MaximumReachedException e) {
			
		}
    	
    }
    
    public void initialize() {
    	playerList.getItems().clear();
    	if(ch.getCurrentTeam()==null)
    	{
    		lblMessage.setText("Sorry, You don't have a current Team.");
    		playerList.setVisible(false);
    		connectThem.setVisible(false);
    	}
    	else {
    		lblMessage.setText("Please select a player to add to the first team:");
    			//Add to list only who isn't a first team player
    		Team t=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam();
    		if(t!=null) {
    			if(t.getPlayers()!=null) {
    				for(Player p:t.getPlayers().keySet()) {
    					if(p!=null&&!t.getPlayers().get(p)) {
    						playerList.getItems().add(p);
    					}
    				}
    			}
    		}
    	}
    }
   
    
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }

}
