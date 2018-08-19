package view.connection.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeCusFromMatchController {
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
    private ListView<Match> matchList;

    @FXML
    private Label labelSuccess;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    
    @FXML
    void removeCustomerFromMatch(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Customer from Match");
		alert.setHeaderText("");
    	Customer c=customerList.getSelectionModel().getSelectedItem();
    	Match m=matchList.getSelectionModel().getSelectedItem();
    	try {
    		if(c==null) {
        		throw new ListNotSelectedException("choose from customer list");
        	}
        	if(m==null) {
        		throw new ListNotSelectedException("choose from match list");
        	}
        	if(m.removeFan(c)) {
        		if(c.removeMatch(m)) {
        			labelSuccess.setText("Removed match "+m.getId()+" from customer "+c.getId()+" sucessfully!");
        		}
        		else {
        			alert.setHeaderText("Unable to remove Customer from Match.");
    	    		alert.setContentText("cannot remove match from Customer.");
    	    		alert.show();
    	    		m.addFan(c);
        		}
        	}
        	else {
        		alert.setHeaderText("Unable to remove Customer from Match.");
	    		alert.setContentText("cannot remove customer from match.");
	    		alert.show();
        	}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	
    }
    public void initialize() {
    	if(SysData.getInstance().getCustomers()!=null) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    }
    @FXML
    void updateMatchList(MouseEvent event) {
    	matchList.getItems().removeAll(matchList.getItems());
    	Customer c=customerList.getSelectionModel().getSelectedItem();
    	if(c!=null) {
    		if(SysData.getInstance().getMatchs().values()!=null) {
    			for(Match m:SysData.getInstance().getMatchs().values()) {
    				if(m!=null) {
    					if(m.getCrowd().containsKey(c)) {
    						matchList.getItems().add(m);
    					}
    				}
    			}
    		}
    	}
    }
    @FXML
    void updateMatchListtwo(KeyEvent event) {
    	matchList.getItems().removeAll(matchList.getItems());
    	Customer c=customerList.getSelectionModel().getSelectedItem();
    	if(c!=null) {
    		if(SysData.getInstance().getMatchs().values()!=null) {
    			for(Match m:SysData.getInstance().getMatchs().values()) {
    				if(m!=null) {
    					if(m.getCrowd().containsKey(c)) {
    						matchList.getItems().add(m);
    					}
    				}
    			}
    		}
    	}
    	for(Subscription s:c.getSubscriptions()) {
    		if(s!=null) {
    			for(Match match:s.getMatches()) {
    				if(!matchList.getItems().contains(match)) {
    					matchList.getItems().add(match);
    				}
    			}
    		}
    	}
    }

}
