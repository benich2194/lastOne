package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

public class CustomerMenuSidebarController implements Initializable{

    @FXML
    private FlowPane menupane;

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
    void goToAddSubscription(ActionEvent event) {
    	loadUI("customerAddSubscription");
    }

    @FXML
    void goToJoinMatch(ActionEvent event) {
    	loadUI("customerJoinMatch");
    }

    @FXML
    void goToViewSubscriptions(ActionEvent event) {
    	loadUI("customerViewSubscriptions");
    }

}
