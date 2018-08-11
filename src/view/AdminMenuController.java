package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import utils.NameToWindow;

public class AdminMenuController implements Initializable{

    @FXML
    private FlowPane menupane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);
		
	}
	
    public void loadUI(NameToWindow ui)
    {
    	WindowManager.openWindow(ui);
    }
    
    public FlowPane getMenupane() {
		return menupane;
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

}

