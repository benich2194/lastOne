package view;

import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class playerToTeamController {

    @FXML
    private AnchorPane playerToTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;
    

    @FXML
    private ComboBox<Boolean> firstList;

    @FXML
    void addPlayerToTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To Team");
		alert.setHeaderText("");
		Team t=teamList.getSelectionModel().getSelectedItem();
    	if(playerList.getSelectionModel().getSelectedItem()!=null&&teamList.getSelectionModel().getSelectedItem()!=null) {
    		if(firstList.getSelectionModel().getSelectedItem()) {
    			if(SysData.getInstance().addPlayerToTeamFirstPlayers(playerList.getSelectionModel().getSelectedItem().getId(),t.getId() )) {
        			alert.setHeaderText("Added Player to first team players.");
            		alert.setContentText("Player was added to first team players successfully.");
            		alert.show();
        		}
        		else {
        			if(SysData.getInstance().addPlayerToTeam(playerList.getSelectionModel().getSelectedItem().getId(),t.getId())) {
            			alert.setHeaderText("Added Player to team.");
                		alert.setContentText("Player was added to team successfully.");
                		alert.show();
            		}
            		else {
            			alert.setHeaderText("failed to add Player to team.");
                		alert.setContentText("unable to add Player to team, select a player and a team please.");
                		alert.show();
            		}
        		}
    		}
    		
    	}
    	else {
    		alert.setHeaderText("failed to add Player to team.");
    		alert.setContentText("unable to add Player to team.");
    		alert.show();
    	}
    	playerList.getItems().removeAll(playerList.getItems());
    	if(SysData.getInstance().getPlayers()!=null) {
    		for(Player p:SysData.getInstance().getPlayers().values()) {
    			if(p!=null&&p.getCurrentTeam()==null) {
    				playerList.getItems().add(p);
    			}
    		}
    	}
    }

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getPlayers().values().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
    	firstList.getItems().add(true);
    	firstList.getItems().add(false);
    
    }

}
