package view.add.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.PasswordTooShortException;
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
import view.WindowManager;

public class addReceptionistController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane addReceptionist;

    @FXML
    private Button back;

    @FXML
    private TextField recepId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker startWorkingDate;

    @FXML
    private ComboBox<E_Cities> recepCity;

    @FXML
    private TextField houseNumber;

    @FXML
    private TextField recepStreet;

    @FXML
    private TextField recepPhone;

    @FXML
    private DatePicker birthDate;

    @FXML
    private Button addButton;

    @FXML
    private TextField recepPassword;

    @FXML
    void addRecep(ActionEvent event) throws MissingInputException, PasswordTooShortException, ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Receptionist");
		alert.setHeaderText("");
		try {
			if(recepId.getText()=="") {
				throw new MissingInputException("id");
			}
			Integer id = Integer.parseInt(recepId.getText());
	    	String first=firstName.getText();
	    	String last=lastName.getText();
	    	if(first==""||last=="") {
	    		throw new MissingInputException("name");
	    	}
	    	if(birthDate.getValue()==null||startWorkingDate==null) {
	    		throw new MissingInputException("date");
	    	}
	    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
	    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
	    	String[] phones=new String[1];
	    	if(recepPhone.getText()=="") {
	    		throw new MissingInputException("phone");
	    	}
	    	phones[0]=recepPhone.getText();
	    	if(recepStreet.getText()=="") {
	    		throw new MissingInputException("street");
	    	}
	    	String street=recepStreet.getText();
	    	if(houseNumber.getText()=="") {
	    		throw new MissingInputException("house number");
	    	}
	    	Integer houseNum=Integer.parseInt(houseNumber.getText());
	    	String password=recepPassword.getText();
	    	if(password.length()<3) {
	    		throw new PasswordTooShortException();
	    	}
	    	if(recepCity.getSelectionModel().getSelectedItem()==null) {
	    		throw new ListNotSelectedException();
	    	}
	    	Address ad=new Address(recepCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
	    	if(SysData.getInstance().getReceptionists().containsKey(id)) {
	    		alert.setHeaderText("Unable to added Receptionist.");
	    		alert.setContentText("Receptionist already exists.");
	    		alert.show();
	    	}
	    	else {
	    		SysData.getInstance().addReceptionist(id,password, first, last, bday, work, ad);
		    	if(SysData.getInstance().getReceptionists().containsKey(id)) {
		    		alert.setHeaderText("Added Receptionist");
		    		alert.setContentText("Receptionist added successfully.");
		    		alert.show();
		    	}
		    	else {
		    		alert.setHeaderText("Unable to added Receptionist.");
		    		alert.setContentText("Receptionist wasn't added.");
		    		alert.show();
		    	}
	    	}
		}catch(MissingInputException e) {
			
		}catch(PasswordTooShortException e) {
			
		}catch(ListNotSelectedException e) {
			
		}
    	
    }
    /**
     * initializes list and defines text fields for numbers only or letters only
     */
    public void initialize() {
  		recepCity.getItems().addAll(E_Cities.values());	
  		houseNumber.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	houseNumber.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
  		recepId.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	recepId.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
  		recepPhone.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	recepPhone.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
  		firstName.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	firstName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
  		lastName.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	lastName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
  		recepStreet.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	recepStreet.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
  		
    }
    /**
     * goes back to previous window
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}
