package view;

import Controller.SysData;
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

    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button back;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<Player> playerList;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void removePlayerFromTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Player From Team");
		alert.setHeaderText("");
    	Player p=playerList.getSelectionModel().getSelectedItem();
    	Team t=p.getCurrentTeam();
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
    	for(Player pl:SysData.getInstance().getPlayers().values()) {
    		if(pl!=null&&pl.getCurrentTeam()!=null) {
    			playerList.getItems().add(pl);
    		}
    	}
    	
    }
    public void initialize() {
    	for(Player p:SysData.getInstance().getPlayers().values()) {
    		if(p!=null&&p.getCurrentTeam()!=null) {
    			playerList.getItems().add(p);
    		}
    	}
    }

}
