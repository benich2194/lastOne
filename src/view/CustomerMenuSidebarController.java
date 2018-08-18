package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import utils.NameToWindow;

public class CustomerMenuSidebarController implements Initializable{

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
    
    public FlowPane getMenupane() {
		return menupane;
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

}
