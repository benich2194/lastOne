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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

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
	 * @throws ObjectExistsException
	 * @throws MaximumReachedException 
	 */
	@FXML
	void addPlayerToTeam(ActionEvent event) throws ListNotSelectedException,ObjectExistsException, MaximumReachedException {
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
			if (p.getCurrentTeam() == null && SysData.getInstance().addPlayerToTeam(p.getId(), t.getId())) {
				labelSuccess.setText("Player " + p.getId() + " was added succesfully to team " + t.getId());
			} else {
				if (t.getPlayers().size() == utils.Constants.MAX_PLAYERS_FOR_TEAM) {
					alert.setHeaderText("failed to add Player to team.");
					alert.setContentText("players limit has reached.");
					alert.show();
				} else {
					if(p.getCurrentTeam()!=null&&p.transferTo(t)) {
						labelSuccess.setText("Player " + p.getId() + " was added succesfully to team " + t.getId());
					}
					else {
						alert.setHeaderText("failed to add Player to team.");
						alert.setContentText("cannot add player, compares their seniority.");
						alert.show();
					}
				}

			}
		} catch (ListNotSelectedException e) {

		}catch(ObjectExistsException e) {
			
		}catch(MaximumReachedException e) {
			
		}

		// refreshes lists
		teamList.getItems().removeAll(teamList.getItems());
		playerList.getItems().removeAll(playerList.getItems());
		if (SysData.getInstance().getTeams().values().size() > 0) {
			teamList.getItems().addAll(SysData.getInstance().getTeams().values());
		}
		if (SysData.getInstance().getPlayers() != null) {
			playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
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
			playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
		}

	}

}
