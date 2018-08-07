package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

public class AdminMenuController implements Initializable{

    @FXML
    private FlowPane menupane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);
		
	}
	
    public void loadUI(String ui)
    {
    	WindowManager.openWindow(ui);
    }
    
    public FlowPane getMenupane() {
		return menupane;
	}


    @FXML
    private void goToAdminControl(ActionEvent event) {
        loadUI("administratorMenu");
    }
    
	@FXML
    private void GoToCoachPage(ActionEvent event) {
    	loadUI("coachModif");
    }

    @FXML
    private void goToTeamPage(ActionEvent event) {
    	loadUI("teamMenu");
    }

    @FXML
    private void goToPlayerMenu(ActionEvent event) {
    	loadUI("playerMenu");
    }

    @FXML
    private void goToCustomerMenu(ActionEvent event) {
    	loadUI("/view/customerMenuAdmin");
    }

    @FXML
    private void goToRecepMenu(ActionEvent event) {
    	loadUI("receptionistMenuAdmin");
    }

    @FXML
    private void goToStadiumMenu(ActionEvent event) {
    	loadUI("stadiumMenu");
    }

    @FXML
    private void goToSubscriptionButton(ActionEvent event) {
    	loadUI("subscriptionMenu");
    }

    @FXML
    private void goToTrophyMenu(ActionEvent event) {
    	loadUI("trophyMenu");
    }

    @FXML
    private void goToMatchMenu(ActionEvent event) {
    	loadUI("matchMenu");
    }

    @FXML
    private void goToConnectionMenu(ActionEvent event) {
    	loadUI("connectionMenu");
    }
    @FXML
    void goToQueries(ActionEvent event) {
    	loadUI("queries");
    }

}

