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

public class ReceptionistMenuSiderbarController implements Initializable{

    @FXML
    private FlowPane menupane;

	@FXML
    private Button logout;
	
    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);
		loadUI(NameToWindow.WELCOMESCREEN);
	}

    public void loadUI(NameToWindow ui)
    {
    	WindowManager.openWindow(ui);
    }
	
	@FXML
    private void GoToHomePage(ActionEvent event) {
    	loadUI(NameToWindow.WELCOMESCREEN);
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
    	loadUI(NameToWindow.VIEW_CUSTOMER);
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
    @FXML
    void goToRemoveCustomerFromMatch(ActionEvent event) {
    	loadUI(NameToWindow.REMOVE_CUSFROMMATCH);
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
