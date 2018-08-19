package view.recep.controller;

import java.util.ArrayList;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.NoValidSubscriptionException;
import Exceptions.ObjectExistsException;
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
    
    //Receptionist can add customers who he sold subscriptions to, to a match
    private ArrayList<Customer> myList;
    
    @FXML
    void addCustomerToMatch(ActionEvent event) throws ListNotSelectedException, NoValidSubscriptionException, ObjectExistsException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Customer to Match");
		alert.setHeaderText("");
		try {
			if(matchList.getSelectionModel().getSelectedItem()==null||customerList.getSelectionModel().getSelectedItem()==null) {
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
	    	}
    	}
		customerList.getItems().removeAll(customerList.getItems());//refreshes list
		matchList.getItems().removeAll(matchList.getItems());
		if(SysData.getInstance().getCustomers()!=null) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	Receptionist r=SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep()));
    	for(Match m:SysData.getInstance().getMatchs().values()) {
    		if(m!=null) {
    			if(m.getHomeTeam()!=null&&m.getHomeTeam().getStadium()!=null) {
    				if(m.getHomeTeam().getStadium().getReceptionists().contains(r)) {
    					matchList.getItems().add(m);
    				}
    			}
    		}
    	}
		
    }catch(ListNotSelectedException e){
    	
    }catch(NoValidSubscriptionException e) {
    	
    }
    }
    /**
     * initializes lists
     */
    public void initialize() {
    	myList = new ArrayList<Customer>();
    	
    	Receptionist r=SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep()));
    	
    	//Creates List of customers that belong to the Receptionist
    	for(Subscription s: r.getSubscriptions())
    		if(s!=null && s.getCustomer()!=null)
    			myList.add(s.getCustomer());
    	
    	if(myList!=null) {
	        ObservableList<Customer> customersSoldSubTo = FXCollections.observableArrayList(myList);
	        customerList.setItems(customersSoldSubTo);
    	}
    	
    	
    	for(Match m:SysData.getInstance().getMatchs().values()) {
    		if(m!=null) {
    			if(m.getHomeTeam()!=null&&m.getHomeTeam().getStadium()!=null) {
    				if(m.getHomeTeam().getStadium().getReceptionists().contains(r)) {
    					matchList.getItems().add(m);
    				}
    			}
    		}
    	}
    }

}
