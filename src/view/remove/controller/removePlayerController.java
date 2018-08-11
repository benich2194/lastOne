package view.remove.controller;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Coach;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;


public class removePlayerController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Player> playerList;

    @FXML
    private Button removeButton;
    /**
     * goes to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }
    /**
     * initializes list
     */
    public void initialize() {
    	if(SysData.getInstance().getPlayers().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
    }
    /**
     * removes player from data base
     * @param event remove button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removePlayer(ActionEvent event) throws ListNotSelectedException {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Remove Player");
    		alert.setHeaderText("");
    		try {
    			Player c=playerList.getSelectionModel().getSelectedItem();
    			if(c==null) {
    				throw new ListNotSelectedException();
    			}
            	//remove player from a team he is in..
            	if(c.getCurrentTeam()!=null) {
            		if(c.getCurrentTeam().getPlayers().containsKey(c)) {
            			c.getCurrentTeam().removePlayer((Player)c);
            		}
            	}
            	SysData.getInstance().getPlayers().remove(c.getId());
            	if(!SysData.getInstance().getPlayers().containsKey(c.getId())) {
            		alert.setHeaderText("Removed Player");
            		alert.setContentText("Removed Player successfully.");
            		alert.show();
            	}
            	else {
            		alert.setHeaderText("Unable to remove Player.");
            		alert.setContentText("Cannot remove Player from database.");
            		alert.show();
            	}
    		}catch(ListNotSelectedException e) {
    		}
    		playerList.getItems().removeAll(playerList.getItems());
    		if(SysData.getInstance().getPlayers().size()>0) {
        		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
        	}
        	
    }
    

}
