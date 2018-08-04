package view;

import java.io.IOException;

import Controller.SysData;
import Model.Customer;
import Model.Receptionist;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.E_Periods;

public class SubToCustomerController {

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
    void addSubscriptionToCustomer(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Subscription To Customer");
		alert.setHeaderText("");
    	Integer id=Integer.parseInt(subId.getText());
    	java.sql.Date work=java.sql.Date.valueOf(startDate.getValue());
    	if(recepList.getSelectionModel().getSelectedItem()!=null&&customerList.getSelectionModel().getSelectedItem()!=null) {
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
    	}
    	else {
    		alert.setHeaderText("failed to add Subscription to Customer.");
    		alert.setContentText("unable to add Subscription to Customer.");
    		alert.show();
    	}
    	
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)connectThem.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/connectionMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
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
