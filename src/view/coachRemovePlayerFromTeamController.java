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

public class coachRemovePlayerFromTeamController {

    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<Player> playerList;

    @FXML
    void removePlayerFromTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Player From Team");
		alert.setHeaderText("");
    	Team t=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam();
    	Player p=playerList.getSelectionModel().getSelectedItem();
    	if(p==null||!t.removePlayer(p)) {
    		alert.setHeaderText("failed to remove Player from team.");
    		alert.setContentText("unable to remove Player from team.");
    		alert.show();
    	}
    	else {
    		p.setCurrentTeam(null);
    		alert.setHeaderText("Player was removed from team successfully.");
    		alert.setContentText("Player is no longer in this team.");
    		alert.show();
    	}
    }
    public void initialize() {
    	if(SysData.getInstance().getCoachs()!=null) {
    		playerList.getItems().addAll(SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach())).getCurrentTeam().getPlayers().keySet());
    	}
    }

}
