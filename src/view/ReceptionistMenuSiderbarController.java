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
    	loadUI(NameToWindow.ADD_CUSTOMER);
    }

    @FXML
    void goToAddCustomerToMatch(ActionEvent event) {
    	loadUI(NameToWindow.RECEP_CUSTOMATCH);
    }

    @FXML
    void goToAddSubscription(ActionEvent event) {
    	loadUI(NameToWindow.RECEP_ADDSUBTOCUST);
    }

    @FXML
    void goToModifySubscription(ActionEvent event) {
    	loadUI(NameToWindow.RECEP_MODIFSUB);
    }


    @FXML
    void goToRemoveCustomer(ActionEvent event) {
    	loadUI(NameToWindow.REMOVE_CUSTOMER);
    }

    @FXML
    void goToRemoveSubscription(ActionEvent event) {
    	loadUI(NameToWindow.RECEP_REMOVESUB);
    }

    @FXML
    void goToViewCustomers(ActionEvent event) {
    	loadUI("viewCustomer");
    }

    @FXML
    void goToViewSubscriptions(ActionEvent event) {
    	loadUI(NameToWindow.RECEP_SUBVIEW);
    }
    

    @FXML
    void goToGSPM(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETSPM);
    }

    @FXML
    void goToGASPM(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETALLSPM);
    }

    @FXML
    void goMPP(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETMPP);
    }

    @FXML
    void goToMAC(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETMAC);
    }

    @FXML
    void goToFPBHT(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETFPOBHT);
    }

}
