package view;

import java.io.IOException;

import Controller.SysData;
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


public class removePlayerController {

    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Player> playerList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	if(SysData.getInstance().getPlayers().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
    }
    
    @FXML
    void removePlayer(ActionEvent event) {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Remove Player");
    		alert.setHeaderText("");
        	Coach c=playerList.getSelectionModel().getSelectedItem();
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
    }
    

}
