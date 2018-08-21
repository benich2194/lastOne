package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MaximumReachedException;
import Exceptions.ObjectExistsException;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class playerToFirstTeamController {
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
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;
    
    @FXML
    private Label labelSuccess;
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
		Team t=teamList.getSelectionModel().getSelectedItem();
		Player p=playerList.getSelectionModel().getSelectedItem();
		try {
			if(t!=null&&p!=null) {
	    		if(SysData.getInstance().addPlayerToTeamFirstPlayers(p.getId(),t.getId())){
	    			labelSuccess.setText("player "+p.getId()+" was added to first team players in team "+t.getId());
	    		}
	    		else {
	    			alert.setHeaderText("Failed to add Player to First Team Players.");
	        		alert.setContentText("player is already a first team player in this team");
	        		alert.show();
	    		}
	    	}
	    	else {
	    		if(p==null) {
	    			throw new ListNotSelectedException("please choose player from list");
	    		}
	    		if(t==null) {
	    			throw new ListNotSelectedException("Please choose team from list");
	    		}
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(ObjectExistsException e) {
			
		}
    	//refreshes lists
		playerList.getItems().removeAll(playerList.getItems());
    	teamList.getItems().removeAll(teamList.getItems());
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    		}
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    		}	
    	//Clear players list until a Team is chosen
        playerList.getItems().clear();
    }
    
    /**
     * shows all the players on current team in players list
     * @param mouse click
     */
    @FXML
    void showTeamPlayers(MouseEvent event) {
    	//If there are players on the team
    	playerList.getItems().removeAll(playerList.getItems());
    	if(!teamList.getSelectionModel().getSelectedItem().getPlayers().keySet().isEmpty())
    		for(Player p:teamList.getSelectionModel().getSelectedItem().getPlayers().keySet()) {
    			if(p!=null&&teamList.getSelectionModel().getSelectedItem().getPlayers().get(p)==false) {
    				playerList.getItems().add(p);
    			}
    		}
    	for(Player p:SysData.getInstance().getPlayers().values()) {
    		if(p!=null&&p.getCurrentTeam()==null&&!teamList.getSelectionModel().getSelectedItem().getPlayers().containsKey(p)) {
    			playerList.getItems().add(p);
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
