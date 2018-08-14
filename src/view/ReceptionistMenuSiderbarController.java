package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import utils.NameToWindow;

public class ReceptionistMenuSiderbarController implements Initializable{

    @FXML
    private FlowPane menupane;

    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);

	}

    public void loadUI(NameToWindow ui)
    {
    	WindowManager.openWindow(ui);
    }
    
	public void loadUI(String ui) {
		WindowManager.openWindow(ui);
	}
	
    @FXML
    void goToAddCustomer(ActionEvent event) {
    	loadUI("receptionistAddCustomer");
    }

    @FXML
    void goToAddCustomerToMatch(ActionEvent event) {
    	loadUI("receptionistAddCustomerToMatch");
    }

    @FXML
    void goToAddSubscription(ActionEvent event) {
    	loadUI("receptionistAddSubscription");
    }

    @FXML
    void goToModifyCustomer(ActionEvent event) {

    }

    @FXML
    void goToModifySubscription(ActionEvent event) {

    }

    @FXML
    void goToQueries(ActionEvent event) {
		loadUI(NameToWindow.QUERIES);
    }

    @FXML
    void goToRemoveCustomer(ActionEvent event) {
    	loadUI(NameToWindow.REMOVE_CUSTOMER);
    }

    @FXML
    void goToRemoveSubscription(ActionEvent event) {
    	loadUI("receptionistRemoveSubscription");
    }

    @FXML
    void goToViewCustomers(ActionEvent event) {

    }

    @FXML
    void goToViewSubscriptions(ActionEvent event) {

    }

}
