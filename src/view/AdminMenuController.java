package view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

public class AdminMenuController implements Initializable{

    @FXML
    private FlowPane menupane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
    private void loadUI(String ui)
    {
    	Parent root = null;
    	try {
        	root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
    	} catch (IOException ex) {
    		Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	menupane.getChildren().removeAll();
    	menupane.getChildren().setAll(root);
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
    	//loadUI("teamMenu");
    }
   

}

