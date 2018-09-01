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

public class CustomerMenuSidebarController implements Initializable{

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
    
    public FlowPane getMenupane() {
		return menupane;
	}
    
	@FXML
    private void GoToHomePage(ActionEvent event) {
    	loadUI(NameToWindow.WELCOMESCREEN);
    }
	
    @FXML
    void goToAddSubscription(ActionEvent event) {
    	loadUI(NameToWindow.CUSTOMER_ADDSUB);
    }

    @FXML
    void goToJoinMatch(ActionEvent event) {
    	loadUI(NameToWindow.CUSTOMER_JOINMATCH);
    }

    @FXML
    void goToViewSubscriptions(ActionEvent event) {
    	loadUI(NameToWindow.CUSTOMER_VIEWSUB);
    }
    @FXML
    void goToRemoveSubscription(ActionEvent event) {
    	loadUI(NameToWindow.CUSTOMER_REMOVESUB);
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
    @FXML
    void goToChangePassword(ActionEvent event) {
    	loadUI(NameToWindow.CUSTOMER_CHANGEPASSWORD);
    }
    
}
