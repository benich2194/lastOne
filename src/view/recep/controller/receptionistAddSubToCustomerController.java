package view.recep.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.ObjectExistsException;
import Model.Customer;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Periods;
import view.WindowManager;

public class receptionistAddSubToCustomerController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane subToCustomer;

    @FXML
    private Button back;

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
    private Label labelSuccess;
    
    //Logged in Receptionist
    private Receptionist r = SysData.getInstance().getReceptionists().get(Integer.parseInt(SysData.getInstance().getUserRecep()));

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) throws MissingInputException,ListNotSelectedException, ObjectExistsException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Subscription To Customer");
		alert.setHeaderText("");
		Customer c=customerList.getSelectionModel().getSelectedItem();
		E_Periods p=periodList.getSelectionModel().getSelectedItem();
		try {
			if(subId.getText().isEmpty()) {
				throw new MissingInputException("Subscription ID");
			}
	    	Integer id=Integer.parseInt(subId.getText());
	    	if(startDate.getValue()==null) {
	    		throw new MissingInputException("start date");
	    	}
	    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
	    	if(c==null) {
	    		throw new ListNotSelectedException("Choose from customer list");
	    	}
	    	if(p==null) {
	    		throw new ListNotSelectedException("Choose from period list");
	    	}
	    		if(SysData.getInstance().addSubscriptionToCustomer(id, c.getId(),r.getId(), p, work)) {
	    			labelSuccess.setText("Subscription "+id+" was added succesfully to customer "+c.getId());
	    			startDate.valueProperty().set(null);
	    			subId.setText("");
	    			//refreshes lists
	    			customerList.getItems().removeAll(customerList.getItems());
	    	    		periodList.getItems().addAll(E_Periods.values());
	    	    		if(SysData.getInstance().getCustomers().values().size()>0) {
	    	        		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
	    	        	}
	    			periodList.getItems().removeAll(periodList.getItems());
	    			periodList.getItems().addAll(E_Periods.values());
	    		}
	    		else {
	    			alert.setHeaderText("failed to add Subscription to Customer.");
	        		alert.setContentText("unable to add Subscription to Customer.");
	        		alert.show();
	    		}
		}catch(MissingInputException e) {
			
		}catch(ListNotSelectedException e) {
			
		}catch(ObjectExistsException e) {
			
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

    		periodList.getItems().addAll(E_Periods.values());
    		if(SysData.getInstance().getCustomers().values().size()>0) {
        		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
        	}
    }// End of initialize

}
