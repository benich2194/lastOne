package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class cusToMatchController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane subToCustomer;

    @FXML
    private Button back;

    @FXML
    private ListView<Customer> customerList;

    @FXML
    private Button connectThem;

    @FXML
    private ListView<Match> matchList;

    @FXML
    void addCustomerToMatch(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Customer to Match");
		alert.setHeaderText("");
		try {
			if(customerList.getSelectionModel().getSelectedItem()==null||matchList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			if(SysData.getInstance().getMatchs().get(matchList.getSelectionModel().getSelectedItem().getId()).getCrowd().containsKey(customerList.getSelectionModel().getSelectedItem())) {
				alert.setHeaderText("Unable to add Customer To Match.");
	    		alert.setContentText("Customer exists in match.");
	    		alert.show();
			}
	    	else {
		    	if(SysData.getInstance().addCustomerToMatch(customerList.getSelectionModel().getSelectedItem().getId(), matchList.getSelectionModel().getSelectedItem().getId())) {
		    		alert.setHeaderText("Added Customer to Match");
		    		alert.setContentText("Added Customer to Match successfully.");
		    		alert.show();
		    	}
		    	else {
		    		alert.setHeaderText("Unable to Add Customer to Match.");
		    		alert.setContentText("Customer cannot be added to match");
		    		alert.show();
		    	}
	    	}
		}catch(ListNotSelectedException e) {
			
		}
		//refreshing lists
		customerList.getItems().removeAll(customerList.getItems());
		matchList.getItems().removeAll(matchList.getItems());
		if(SysData.getInstance().getCustomers().values().size()>0) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	if(SysData.getInstance().getMatchs().values().size()>0) {
    		matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
    	}
    }
    /**
     * initializes lists
     */
    public void initialize() {
    	if(SysData.getInstance().getCustomers().values().size()>0) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	if(SysData.getInstance().getMatchs().values().size()>0) {
    		matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
    	}
    
    }
    /**
     * goes back to previous screen
     * @param event back button was pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}
