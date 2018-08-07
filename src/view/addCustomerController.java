package view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import Controller.SysData;
import Model.Address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import utils.E_Levels;

public class addCustomerController {

	 @FXML
	    private AnchorPane addCustomer;

	    @FXML
	    private Button back;

	    @FXML
	    private TextField cusId;

	    @FXML
	    private TextField firstName;

	    @FXML
	    private TextField lastName;

	    @FXML
	    private DatePicker birthDate;

	    @FXML
	    private ComboBox<E_Levels> levelCustomer;
	    
	    @FXML
	    private ComboBox<E_Cities> cusCity;

	    @FXML
	    private TextField houseNumber;

	    @FXML
	    private TextField cusStreet;

	    @FXML
	    private TextField cusPhone;

	    @FXML
	    private Button addButton;

	    @FXML
	    private TextField favTeam;

	    @FXML
	    private TextField cusMail;

	    @FXML
	    void addCustomer(ActionEvent event) throws MalformedURLException {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Add Customer");
			alert.setHeaderText("");
	    	String id=cusId.getText();
	    	String first=firstName.getText();
	    	String last=lastName.getText();
	    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
	    	String[] phones=new String[1];
	    	phones[0]=cusPhone.getText();
	    	String street=cusStreet.getText();
	    	Integer houseNum=Integer.parseInt(houseNumber.getText());
	    	Address ad=new Address(cusCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
	    	URL mail=new URL("http:\\"+cusMail.getText());
	    	Integer fav=Integer.parseInt(favTeam.getText());
	    	if(SysData.getInstance().getCustomers().containsKey(id)) {
	    		alert.setHeaderText("Unable to added Customer.");
	    		alert.setContentText("Customer already exists.");
	    		alert.show();
	    	}
	    	else {
	    		SysData.getInstance().addCustomer(id, first, last, bday, levelCustomer.getSelectionModel().getSelectedItem(), mail, fav, ad);
		    	if(SysData.getInstance().getCustomers().containsKey(id)) {
		    		alert.setHeaderText("Added Customer");
		    		alert.setContentText("Customer added successfully.");
		    		alert.show();
		    	}
		    	else {
		    		alert.setHeaderText("Unable to added Customer.");
		    		alert.setContentText("Customer wasn't added.");
		    		alert.show();
		    	}
	    	}
	    }
	    public void initialize() {
	  		cusCity.getItems().addAll(E_Cities.values());
	  		levelCustomer.getItems().addAll(E_Levels.values());
	  }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
