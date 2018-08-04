package view;

import java.io.IOException;

import Controller.SysData;
import Model.Customer;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class cusToMatchController {

    @FXML
    private AnchorPane subToCustomer;

    @FXML
    private Button back;

    @FXML
    private ListView<Customer> customerList;

    @FXML
    private Button connectThem;

    @FXML
    private ListView<Match> matchList;

    @FXML
    void addCustomerToMatch(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Customer to Match");
		alert.setHeaderText("");
		if(SysData.getInstance().getMatchs().get(matchList.getId()).getCrowd().containsKey(customerList.getSelectionModel().getSelectedItem())) {
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
    }
    public void initialize() {
    	if(SysData.getInstance().getCustomers().values().size()>0) {
    		customerList.getItems().addAll(SysData.getInstance().getCustomers().values());
    	}
    	if(SysData.getInstance().getMatchs().values().size()>0) {
    		matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
    	}
    
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	    	Stage stage=(Stage)matchList.getScene().getWindow();
    			stage.close();
    	    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/connectionMenu.fxml"));
    	    	Stage primaryStage=new Stage();
    	    	Parent root=load.load();
    	    	Scene scene=new Scene(root);
    	    	primaryStage.setScene(scene);
    	    	primaryStage.show();
    }

}
