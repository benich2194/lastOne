package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

public class CoachMenuSidebarController implements Initializable {

	@FXML
	private FlowPane menupane;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);

	}

	public void loadUI(String ui) {
		WindowManager.openWindow(ui);
	}

	@FXML
	void goToAddPlayerToTeam(ActionEvent event) {
		loadUI("coachAddPlayerToTeam");
	}

	@FXML
	void goToChangeFirstTeam(ActionEvent event) {
		loadUI("coachReplacePlayers");
	}

	@FXML
	void goToModifyPlayerInTeam(ActionEvent event) {

	}

	@FXML
	void goToQueries(ActionEvent event) {

	}

	@FXML
	void goToViewPlayers(ActionEvent event) {

	}

	@FXML
	void removePlayerFromTeam(ActionEvent event) {
		loadUI("coachRemovePlayerFromTeam.fxml");
	}

}
