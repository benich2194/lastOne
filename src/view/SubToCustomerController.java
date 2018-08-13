package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Customer;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Periods;

public class SubToCustomerController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane subToCustomer;

    @FXML
    private Button back;

    @FXML
    private ListView<Receptionist> recepList;

    @FXML
    private Button connectThem;

    @FXML
    private TextField subId;

    @FXML
    private ListView<Customer> customerList;

    @FXML
    private ComboBox<E_Periods> periodList;
    
    @FXML
    private DatePicker startDate;

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) throws MissingInputException,ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Subscription To Customer");
		alert.setHeaderText("");
		try {
			if(subId.getText()=="") {
				throw new MissingInputException("Subscription ID");
			}
	    	Integer id=Integer.parseInt(subId.getText());
	    	if(startDate.getValue()==null) {
	    		throw new MissingInputException("start date");
	    	}
	    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
	    	if(customerList.getSelectionModel().getSelectedItem()==null||periodList.getSelectionModel().getSelectedItem()==null||recepList.getSelectionModel().getSelectedItem()==null) {
	    		throw new ListNotSelectedException();
	    	}
	    		if(SysData.getInstance().addSubscriptionToCustomer(id, customerList.getSelectionModel().getSelectedItem().getId(), recepList.getSelectionModel().getSelectedItem().getId(), periodList.getSelectionModel().getSelectedItem(), work)) {
	    			alert.setHeaderText("Added Subscription to Customer.");
	        		alert.setContentText("Subscription was added to Customer successfully.");
	        		alert.show();
	    		}
	    		else {
	    			alert.setHeaderText("failed to add Subscription to Customer.");
	        		alert.setContentText("unable to add Subscription to Customer.");
	        		alert.show();
	    		}
		}catch(MissingInputException e) {
			
		}catch(ListNotSelectedException e) {
			
		}
		
    }
    /**
     * goes back to previous screen
     * @param event back button is selected
     */
    @FXML
    void goBack(ActionEvent event) {
       	WindowManager.goBack();
    }
    /**
     * initialize customer and receptionist lists
     */
    public void initialize() {
    	if(SysData.getInstance().getReceptionists().values().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
    		periodList.getItems().addAll(E_Periods.values());
    		if(SysData.getInstance().getCustomers().values().size()>0) {
        		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
        	}
    }

}
