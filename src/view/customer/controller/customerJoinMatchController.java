package view.customer.controller;

import Controller.SysData;
import Exceptions.InvalidInputException;
import Exceptions.ListNotSelectedException;
import Exceptions.NoValidSubscriptionException;
import Exceptions.ObjectExistsException;
import Exceptions.ObjectNotExistException;
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

public class customerJoinMatchController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane joinMatch;

    @FXML
    private Button joinButton;

    @FXML
    private ListView<Match> matchList;
    
    @FXML
    private Label labelSuccess;

    @FXML
    void joinMatch(ActionEvent event) throws ListNotSelectedException, NoValidSubscriptionException, ObjectExistsException, ObjectNotExistException, InvalidInputException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Customer to Match");
		alert.setHeaderText("");
    	Match m=matchList.getSelectionModel().getSelectedItem();
    	Customer c=SysData.getInstance().getCustomers().get(SysData.getInstance().getUserCustomer());
    	try {
    		if(m==null) {
        		throw new ListNotSelectedException("choose match:");
        	}
    		if(c==null) {
    			throw new InvalidInputException("no customer");
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
    		
    	}catch(InvalidInputException e) {
    		
    	}
    	//refreshes boxes
    	matchList.getItems().removeAll(matchList.getItems());
    	if(SysData.getInstance().getMatchs().size()>0) {
 			for(Match ma:SysData.getInstance().getMatchs().values()) {
 				if(ma!=null&&!ma.getCrowd().containsKey(c)) {
 					matchList.getItems().add(ma);
 				}
 			}
 		}
    }
    /**
     * initialize list
     */
    public void initialize() {
    	Customer c=SysData.getInstance().getCustomers().get(SysData.getInstance().getUserCustomer());
 		if(SysData.getInstance().getMatchs().size()>0) {
 			for(Match m:SysData.getInstance().getMatchs().values()) {
 				if(m!=null&&!m.getCrowd().containsKey(c)) {
 					matchList.getItems().add(m);
 				}
 			}
 		}
 		
 }

}
