package view.recep.controller;

import java.util.ArrayList;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.NoValidSubscriptionException;
import Exceptions.ObjectExistsException;
import Exceptions.ObjectNotExistException;
import Model.Customer;
import Model.Match;
import Model.Receptionist;
import Model.Subscription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class receptionistAddCustomerToMatchController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane cusToMatch;

    @FXML
    private ListView<Customer> customerList;

    @FXML
    private Button connectThem;

    @FXML
    private ListView<Match> matchList;
    
    @FXML
    private Label labelSuccess;
    
    //Receptionist can add customers who he sold subscriptions to, to a match
    private ArrayList<Customer> myList;
    
    @FXML
    void addCustomerToMatch(ActionEvent event) throws ListNotSelectedException, NoValidSubscriptionException, ObjectExistsException, ObjectNotExistException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Customer to Match");
		alert.setHeaderText("");
    	Customer c=customerList.getSelectionModel().getSelectedItem();
    	Match m=matchList.getSelectionModel().getSelectedItem();
    	try {
    		if(c==null) {
        		throw new ListNotSelectedException("choose customer");
        	}
    		if(m==null) {
        		throw new ListNotSelectedException("choose match:");
        	}
    		if(m.getCrowd().containsKey(c)) {
    			throw new ObjectExistsException("customer is already in match");
    		}
    		if(SysData.getInstance().addCustomerToMatch(c.getId(), m.getId())) {
    			labelSuccess.setText("added customer "+c.getId()+" to match "+m.getId()+" succesfully!");
    		}
    		else {
    		}
    		
    	}catch(ListNotSelectedException e) {
    		
    	}catch(NoValidSubscriptionException e) {
    		
    	}catch(ObjectExistsException e) {
    		
    	}catch(ObjectNotExistException e) {
    		
    	}
    	//refreshes boxes
    	customerList.getItems().removeAll(customerList.getItems());
    	matchList.getItems().removeAll(matchList.getItems());
    	if(SysData.getInstance().getCustomers()!=null) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	if(SysData.getInstance().getMatchs()!=null) {
    		matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
    	}
    }
    /**
     * initializes lists
     */
    public void initialize() {
    	if(SysData.getInstance().getCustomers()!=null) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	if(SysData.getInstance().getMatchs()!=null) {
    		matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
    	}
    }

}
