package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import utils.NameToWindow;

public class CoachMenuSidebarController implements Initializable {

	@FXML
	private FlowPane menupane;

	@FXML
	private Button logout;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);
		loadUI(NameToWindow.WELCOMESCREEN);

	}

	public void loadUI(NameToWindow ui) {
		WindowManager.openWindow(ui);
	}

	@FXML
    private void GoToHomePage(ActionEvent event) {
    	loadUI(NameToWindow.WELCOMESCREEN);
    }
	
	@FXML
	void goToAddPlayerToTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_ADDPLTOTEAM);
	}

	@FXML
	void goToChangeFirstTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_REPLACEPL);
	}

	@FXML
	void goToModifyPlayerInTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_MODIFPLAYERS);
	}

	@FXML
	void goToAddTOFIRSTTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_PLYSTOFIRSTTEAM);
	}

	@FXML
	void goToViewPlayers(ActionEvent event) {
		loadUI(NameToWindow.COACH_VIEWPL);
	}

	@FXML
	void goToRemovePlayersFromFirstTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_REMOVEFROMFIRST);
	}

	@FXML
	void removePlayerFromTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_REMOVEPL);
	}

	@FXML
	void goToGSPM(ActionEvent event) {
		loadUI(NameToWindow.QUERY_GETSPM);
	}

	@FXML
	void goToASPM(ActionEvent event) {
		loadUI(NameToWindow.QUERY_GETALLSPM);
	}

	@FXML
	void goToMPP(ActionEvent event) {
		loadUI(NameToWindow.QUERY_GETMPP);
	}

	@FXML
	void goToMFT(ActionEvent event) {
		loadUI(NameToWindow.QUERY_MFT);
	}

	@FXML
	void goToTWLHC(ActionEvent event) {
		loadUI(NameToWindow.QUERY_GETTWLHC);
	}

	@FXML
	void goToFPOBHT(ActionEvent event) {
		loadUI(NameToWindow.QUERY_GETFPOBHT);
	}

	@FXML
	void GoToLogin(ActionEvent event) throws IOException {
		Stage stage = (Stage) menupane.getScene().getWindow();

		SysData.getInstance().setUserCoach(null);
		SysData.getInstance().setUserCustomer(null);
		SysData.getInstance().setUserRecep(null);

		stage.close();
		FXMLLoader load = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		Stage primaryStage = new Stage();
		Parent root = load.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
