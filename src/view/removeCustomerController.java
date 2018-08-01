package view;

import java.io.IOException;

import Controller.SysData;
import Model.Customer;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class removeCustomerController {

	  @FXML
	    private AnchorPane removeCustomer;

	    @FXML
	    private Button back;

	    @FXML
	    private ComboBox<Customer> cusList;

	    @FXML
	    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)removeCustomer.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/customerMenuAdmin.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();	
    }
    @FXML
    void removeCustomer(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Customer");
		alert.setHeaderText("");
    	Customer c=cusList.getSelectionModel().getSelectedItem();
    	SysData.getInstance().getCustomers().remove(c.getId());
    	if(!SysData.getInstance().getCustomers().containsKey(c.getId())) {
    		alert.setHeaderText("Removed Customer");
    		alert.setContentText("Removed Customer successfully.");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Unable to remove Customer.");
    		alert.setContentText("Cannot remove Customer from database.");
    		alert.show();
    	}
    }
    public void initialize() {
 		if(SysData.getInstance().getCustomers().size()>0) {
 			cusList.getItems().addAll(SysData.getInstance().getCustomers().values());
 		}
 		
 }
}
