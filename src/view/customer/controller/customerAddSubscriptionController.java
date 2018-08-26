package view.customer.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.ObjectExistsException;
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

public class customerAddSubscriptionController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane addSub;

    @FXML
    private ListView<Receptionist> recepList;

    @FXML
    private Button connectThem;

    @FXML
    private TextField subId;

    @FXML
    private DatePicker startDate;

    @FXML
    private ComboBox<E_Periods> periodList;
    
    @FXML
    private Label labelSuccess;
    
    @FXML
    private Button clearButton;

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) throws MissingInputException, ObjectExistsException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Subscription To Customer");
		alert.setHeaderText("");
		try {
			if(subId.getText().isEmpty()) {
				throw new MissingInputException("id");
			}
			Integer id=Integer.parseInt(subId.getText());
			if(startDate.getValue()==null) {
				throw new MissingInputException("start date");
			}
			Receptionist r=recepList.getSelectionModel().getSelectedItem();
	    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
	    	E_Periods p=periodList.getSelectionModel().getSelectedItem();
	    	if(r==null)
	    		throw new ListNotSelectedException("Please choose a receptionist");
	    			if(p==null) {
	    		throw new ListNotSelectedException("Please choose period");
	    	}
	    	if(recepList.getSelectionModel().getSelectedItem()!=null) {
	    		if(SysData.getInstance().addSubscriptionToCustomer(id, SysData.getInstance().getUserCustomer(), recepList.getSelectionModel().getSelectedItem().getId(), periodList.getSelectionModel().getSelectedItem(), work)) {
	    			labelSuccess.setText("subscription "+id+" was added succesfully!");
	    		}
	    		else {
	    			alert.setHeaderText("failed to add Subscription to Customer.");
	        		alert.setContentText("unable to add Subscription to Customer.");
	        		alert.show();
	    		}
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
    	//refreshes list
		startDate.valueProperty().set(null);
		subId.setText("");
		recepList.getItems().removeAll(recepList.getItems());
		if(SysData.getInstance().getReceptionists().values().size()>0) {
    		for(Receptionist r:SysData.getInstance().getReceptionists().values()) {
    			if(r!=null&&r.getWorkingStadium()!=null) {
    				recepList.getItems().add(r);
    			}
    		}
    	}
		periodList.getItems().removeAll(periodList.getItems());
		periodList.getItems().addAll(E_Periods.values());
    }
    /**
     * initializes lists and defines textfield
     */
    public void initialize() {
    	if(SysData.getInstance().getReceptionists().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
    	periodList.getItems().addAll(E_Periods.values());
    	 subId.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	subId.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
    }
    /**
     * clears form
     * @param event clear button is pressed
     */
    @FXML
    void clearForm(ActionEvent event) {
    	startDate.valueProperty().set(null);
		subId.setText("");
		labelSuccess.setText("");
		recepList.getItems().removeAll(recepList.getItems());
		if(SysData.getInstance().getReceptionists().values().size()>0) {
    		for(Receptionist r:SysData.getInstance().getReceptionists().values()) {
    			if(r!=null&&r.getWorkingStadium()!=null) {
    				recepList.getItems().add(r);
    			}
    		}
    	}
		periodList.getItems().removeAll(periodList.getItems());
		periodList.getItems().addAll(E_Periods.values());
    }
}
