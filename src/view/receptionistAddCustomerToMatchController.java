package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import Model.Receptionist;
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

    @FXML
    void addCustomerToMatch(ActionEvent event) throws ListNotSelectedException{
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
	    		alert.setHeaderText("Unable to Add Customer to Match.");
	    		alert.setContentText("Customer cannot be added to match");
	    		alert.show();
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
    	
    }
    }
    /**
     * initializes lists
     */
    public void initialize() {
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
    }

}
