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

public class AdminMenuController implements Initializable{

    @FXML
    private FlowPane menupane;

    @FXML
    private Button logout;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);
		loadUI(NameToWindow.WELCOMESCREEN);
		
	}
	
	
    public void loadUI(NameToWindow ui)
    {
    	WindowManager.openWindow(ui);
    }
    
    public FlowPane getMenupane() {
		return menupane;
	}
    
	@FXML
    private void GoToHomePage(ActionEvent event) {
    	loadUI(NameToWindow.WELCOMESCREEN);
    }
	
	@FXML
    private void GoToCoachPage(ActionEvent event) {
    	loadUI(NameToWindow.MENU_COACH);
    }

    @FXML
    private void goToTeamPage(ActionEvent event) {
    	loadUI(NameToWindow.MENU_TEAM);
    }

    @FXML
    private void goToPlayerMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_PLAYER);
    }

    @FXML
    private void goToCustomerMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_CUSTOMER);
    }

    @FXML
    private void goToRecepMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_RECEPTIONIST);
    }

    @FXML
    private void goToStadiumMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_STADIUM);
    }

    @FXML
    private void goToSubscriptionButton(ActionEvent event) {
    	loadUI(NameToWindow.MENU_SUB);
    }

    @FXML
    private void goToTrophyMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_TROPHY);
    }

    @FXML
    private void goToMatchMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_MATCH);
    }

    @FXML
    private void goToConnectionMenu(ActionEvent event) {
    	loadUI(NameToWindow.MENU_CONNECTION);
    }
    @FXML
    void goToQueries(ActionEvent event) {
    	loadUI(NameToWindow.QUERIES);
    }
    @FXML
    void goToPassword(ActionEvent event) {
    	loadUI(NameToWindow.MENU_PASSWORD);
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

