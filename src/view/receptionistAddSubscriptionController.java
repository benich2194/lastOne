package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Customer;
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

public class receptionistAddSubscriptionController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane subToCustomer;

    @FXML
    private Button connectThem;

    @FXML
    private TextField subId;

    @FXML
    private DatePicker startDate;

    @FXML
    private ListView<Customer> customerList;

    @FXML
    private ComboBox<E_Periods> periodList;

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) throws ListNotSelectedException,MissingInputException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Subscription To Customer");
		alert.setHeaderText("");
		try {
			if(subId.getText().isEmpty()) {
				throw new MissingInputException("id");
			}
			Integer id=Integer.parseInt(subId.getText());
			if(startDate.getValue()==null) {
				throw new MissingInputException("date");
			}
			if(periodList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
	    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
	    	if(customerList.getSelectionModel().getSelectedItem()!=null) {
	    		if(SysData.getInstance().addSubscriptionToCustomer(id, customerList.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(SysData.getInstance().getUserRecep()), periodList.getSelectionModel().getSelectedItem(), work)) {
	    			alert.setHeaderText("Added Subscription to Customer.");
	        		alert.setContentText("Subscription was added to Customer successfully.");
	        		alert.show();
	    		}
	    		else {
	    			alert.setHeaderText("failed to add Subscription to Customer.");
	        		alert.setContentText("unable to add Subscription to Customer.");
	        		alert.show();
	    		}
	    	}
	    	else {
	    		throw new ListNotSelectedException();
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(MissingInputException e) {
			
		}
    	
    	
    }
    /**
     * initializes list and defines text fields
     */
    public void initialize() {
    	periodList.getItems().addAll(E_Periods.values());
    	if(SysData.getInstance().getCustomers()!=null) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	 subId.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	subId.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
		 
    }

}
