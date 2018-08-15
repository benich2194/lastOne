package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class playerToTeamController {
	/**
	 * fx fields
	 */
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
	private Label labelSuccess;

	/**
	 * adds player to team
	 * 
	 * @param event add button was pressed
	 * @throws ListNotSelectedException
	 */
	@FXML
	void addPlayerToTeam(ActionEvent event) throws ListNotSelectedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To Team");
		alert.setHeaderText("");
		Team t = teamList.getSelectionModel().getSelectedItem();
		Player p = playerList.getSelectionModel().getSelectedItem();
		try {
			if (t == null) {
				throw new ListNotSelectedException("Please choose a team");
			}
			if (p == null) {
				throw new ListNotSelectedException("Please choose a player");
			}
			if (SysData.getInstance().addPlayerToTeam(p.getId(), t.getId())) {
				labelSuccess.setText("Player "+p.getId()+" was added succesfully to team "+t.getId());
			} else {
				if(t.getPlayers().size()==utils.Constants.MAX_PLAYERS_FOR_TEAM) {
					alert.setHeaderText("failed to add Player to team.");
					alert.setContentText("players limit has reached.");
					alert.show();
				}
				else {
					alert.setHeaderText("failed to add Player to team.");
					alert.setContentText("unable to add Player to team, select a player and a team please.");
					alert.show();
				}
				
			}
		} catch (ListNotSelectedException e) {

		}

		// refreshes lists
		teamList.getItems().removeAll(teamList.getItems());
		playerList.getItems().removeAll(playerList.getItems());
		if (SysData.getInstance().getTeams().values().size() > 0) {
			teamList.getItems().addAll(SysData.getInstance().getTeams().values());
		}
		if (SysData.getInstance().getPlayers() != null) {
			for (Player z : SysData.getInstance().getPlayers().values()) {
				if (z != null && z.getCurrentTeam() == null) {
					playerList.getItems().add(z);
				}
			}
		}
	}

	/**
	 * goes back to previous screen
	 * 
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}

	/**
	 * initializes lists
	 */
	public void initialize() {
		if (SysData.getInstance().getTeams().values().size() > 0) {
			teamList.getItems().addAll(SysData.getInstance().getTeams().values());
		}
		if (SysData.getInstance().getPlayers() != null) {
			for (Player p : SysData.getInstance().getPlayers().values()) {
				if (p != null && p.getCurrentTeam() == null) {
					playerList.getItems().add(p);
				}
			}
		}

	}

}
