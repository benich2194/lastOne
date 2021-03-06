package view.coach.controller;

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

public class coachReplacePlayersController {
	/**
	 * fx fields
	 */
	@FXML
	private AnchorPane coachToTeam;

	@FXML
	private ListView<Player> playerList;

	@FXML
	private ListView<Player> firstTeamList;

	@FXML
	private Label lblMessage;

	@FXML
	private Label buttonFP;

	@FXML
	private Label buttonBP;

	@FXML
	private Button replaceButton;

	@FXML
	private Label labelSuccess;

	/**
	 * replace players from first players to regular players
	 * 
	 * @param event replace button is pressed
	 * @throws ListNotSelectedException
	 * @throws ObjectExistsException
	 * @throws MaximumReachedException
	 */
	@FXML
	void replacePlayers(ActionEvent event)
			throws ListNotSelectedException, ObjectExistsException, MaximumReachedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Replace players");
		alert.setHeaderText("");
		Team t = SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach()))
				.getCurrentTeam();
		try {
			Player p = playerList.getSelectionModel().getSelectedItem();
			Player f = firstTeamList.getSelectionModel().getSelectedItem();
			if (p == null)
				throw new ListNotSelectedException("Please choose bench player:");
			if (f == null) {
				throw new ListNotSelectedException("Please choose first team player:");
			}
			if (t != null && t.replacePlayerOfFirstTeam(f, p)) {
				labelSuccess.setText("player "+p.getId()+ " and player "+f.getId()+ " were replaced succesfully!");
			} else {
				alert.setHeaderText("Failed to replace players");
				alert.setContentText("Failed to replace players in team.");
				alert.show();
			}
		} catch (ListNotSelectedException e) {

		} catch (ObjectExistsException e) {

		} catch (MaximumReachedException e) {

		}

		// refreshes lists
		firstTeamList.getItems().removeAll(firstTeamList.getItems());
		playerList.getItems().removeAll(playerList.getItems());
		Team te = SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach()))
				.getCurrentTeam();
		if (te != null) {
			for (Player pl : te.getPlayers().keySet()) {
				if (pl != null) {
					if (te.getPlayers().get(pl) == true) {
						firstTeamList.getItems().add(pl);
					} else {
						playerList.getItems().add(pl);
					}
				}
			}
		}
	}

	/**
	 * initializes lists
	 */
	public void initialize() {
		Team t = SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach()))
				.getCurrentTeam();
		if (t != null) {
			for (Player p : t.getPlayers().keySet()) {
				if (p != null) {
					if (t.getPlayers().get(p) == true) {
						firstTeamList.getItems().add(p);
					} else {
						playerList.getItems().add(p);
					}
				}
			}
		} else {
			lblMessage.setText("You don't have a team at the moment, Please try again later.");
			playerList.setVisible(false);
			firstTeamList.setVisible(false);
			replaceButton.setVisible(false);
			buttonBP.setVisible(false);
			buttonFP.setVisible(false);
		}
	} // END of initialize

}
