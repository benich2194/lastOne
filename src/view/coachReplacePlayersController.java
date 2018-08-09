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

public class coachReplacePlayersController {

    @FXML
    private AnchorPane coachToTeam;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private ListView<Player> firstTeamList;

    @FXML
    private Button replaceButton;

    @FXML
    void replacePlayers(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Replace players");
		alert.setHeaderText("");
    	Team t=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam();
    	if(t!=null&&t.replacePlayerOfFirstTeam(playerList.getSelectionModel().getSelectedItem(), firstTeamList.getSelectionModel().getSelectedItem())) {
    		alert.setHeaderText("Replaced players in team.");
    		alert.setContentText("Replaced players in team successfully.");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Failed to replace players");
    		alert.setContentText("Failed to replace players in team.");
    		alert.show();
    	}
    }
    public void initialize() {
    	Team t=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam();
    	if(t!=null) {
    		for(Player p:t.getPlayers().keySet()) {
    			if(p!=null) {
    				if(t.getPlayers().get(p)==true) {
    					firstTeamList.getItems().add(p);
    				}
    				else {
    					playerList.getItems().add(p);
    				}
    			}
    		}
    	}
    }

}
