package view;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
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
    void addSubscriptionToCustomer(ActionEvent event) throws MissingInputException {
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
	    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
	    	if(recepList.getSelectionModel().getSelectedItem()==null||periodList.getSelectionModel().getSelectedItem()==null) {
	    		throw new ListNotSelectedException();
	    	}
	    	if(recepList.getSelectionModel().getSelectedItem()!=null) {
	    		if(SysData.getInstance().addSubscriptionToCustomer(id, SysData.getInstance().getUserCustomer(), recepList.getSelectionModel().getSelectedItem().getId(), periodList.getSelectionModel().getSelectedItem(), work)) {
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
	    		alert.setHeaderText("failed to add Subscription to Customer.");
	    		alert.setContentText("unable to add Subscription to Customer.");
	    		alert.show();
		}
    	
    	}catch(MissingInputException e) {
    		
    	}catch(ListNotSelectedException e) {
    		
    	}
    	//refreshes list
    	recepList.getItems().removeAll(recepList.getItems());
    	if(SysData.getInstance().getReceptionists().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
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
}
