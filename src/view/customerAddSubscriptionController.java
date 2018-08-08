package view;

import Controller.SysData;
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
    void addSubscriptionToCustomer(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Subscription To Customer");
		alert.setHeaderText("");
    	Integer id=Integer.parseInt(subId.getText());
    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
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
    	
    }
    public void initialize() {
    	if(SysData.getInstance().getReceptionists().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
    	periodList.getItems().addAll(E_Periods.values());
    }
}
