package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

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
    private Label labelSuccess;

    @FXML
    void addCustomerToMatch(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Customer to Match");
		alert.setHeaderText("");
		Customer c=customerList.getSelectionModel().getSelectedItem();
		Match m=matchList.getSelectionModel().getSelectedItem();
		try {
			if(c==null) {
				throw new ListNotSelectedException("Select from customer list:");
			}
			if(m==null) {
				throw new ListNotSelectedException("Select from match list:");
			}
			if(SysData.getInstance().getMatchs().get(m.getId()).getCrowd().containsKey(c)) {
				alert.setHeaderText("Unable to add Customer To Match.");
	    		alert.setContentText("Customer exists in match.");
	    		alert.show();
			}
	    	else {
		    	if(SysData.getInstance().addCustomerToMatch(c.getId(), m.getId())) {
		    		labelSuccess.setText("Added customer "+c.getId()+ "to match "+m.getId()+" succesfully!");
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
