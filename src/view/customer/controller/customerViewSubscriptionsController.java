package view.customer.controller;

import Controller.SysData;
import Model.Subscription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class customerViewSubscriptionsController {

	  @FXML
	    private AnchorPane modifyTeam;

	    @FXML
	    private ListView<Subscription> listSubs;

	    @FXML
	    private Button back;

	    public void initialize() {
	    	String customerID = SysData.getInstance().getUserCustomer();
	        ObservableList<Subscription> subs = FXCollections.observableArrayList(SysData.getInstance().getCustomers().get(customerID).getSubscriptions());
	        listSubs.setItems(subs);
	    }
	    
	    @FXML
	    void goBack(ActionEvent event) {
	    	WindowManager.goBack();
	    }
}


