package view.add.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import Controller.SysData;
import Exceptions.IdExistsException;
import Exceptions.InvalidInputException;
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
import utils.E_Levels;
import view.WindowManager;

public class addCustomerController {
	/**
	 * fx fields
	 */
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
	    private TextField cusPassword;
	    /**
	     * add customer
	     * @param event add customer button was pressed
	     * @throws MissingInputException
	     * @throws ListNotSelectedException
	     * @throws PasswordTooShortException
	     * @throws InvalidInputException
	     * @throws IdExistsException
	     */
	    @FXML
	    void addCustomer(ActionEvent event) throws MissingInputException,ListNotSelectedException,PasswordTooShortException, InvalidInputException, IdExistsException {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Add Customer");
			alert.setHeaderText("");
			try {
				String id=cusId.getText();
		    	String first=firstName.getText();
		    	String last=lastName.getText();
		    	String[] phones=new String[1];
		    	phones[0]=cusPhone.getText();
		    	String street=cusStreet.getText();
		    	if(id.isEmpty()) {
		    		throw new MissingInputException("id");
		    	}
		    	if(first.isEmpty()) {
		    		throw new MissingInputException("first name");
		    	}
		    	if(last.isEmpty()) {
		    		throw new MissingInputException("last name");
		    	}
		    	if(street.isEmpty()) {
		    		throw new MissingInputException("street name");
		    	}
		    	if(cusPhone.getText().isEmpty()) {
		    		throw new MissingInputException("phone");
		    	}
		    	if(houseNumber.getText().isEmpty()) {
		    		throw new MissingInputException("house number");
		    	}
		    	if(favTeam.getText().isEmpty()) {
		    		throw new MissingInputException("favorite team id");
		    	}
		    	Integer houseNum=Integer.parseInt(houseNumber.getText());
		    	if(cusCity.getSelectionModel().getSelectedItem()==null) {
		    		throw new ListNotSelectedException();
		    	}
		    	if(cusMail.getText().isEmpty()) {
		    		throw new MissingInputException("email");
		    	}
		    	Address ad=new Address(cusCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
		    	URL mail;
				try {
					mail = new URL("http:\\"+cusMail.getText());
				} catch (MalformedURLException e) {
					throw new InvalidInputException();
				}
		    	if(birthDate.getValue()==null) {
		    		throw new MissingInputException();
		    	}
		    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
		    	Integer fav=Integer.parseInt(favTeam.getText());
		    	String password=cusPassword.getText();
		    	if(password.length()<3) {
		    		throw new PasswordTooShortException();
		    	}
		    	if(SysData.getInstance().getCustomers().containsKey(id)) {
		    		throw new IdExistsException("customer");
		    	}
		    	else {
		    		SysData.getInstance().addCustomer(id,password, first, last, bday, levelCustomer.getSelectionModel().getSelectedItem(), mail, fav, ad);
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
			}catch(PasswordTooShortException e) {
				
			}catch(MissingInputException e) {
				
			}catch(ListNotSelectedException e) {
				
			}catch(InvalidInputException e) {
				
			}catch(IdExistsException e) {
				
			}
	    	
	    }
	    /**
	     * initializes city list and level list, defines text fields to be number only or letter only
	     */
	    public void initialize() {
	  		cusCity.getItems().addAll(E_Cities.values());
	  		levelCustomer.getItems().addAll(E_Levels.values());
	  		 cusId.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.matches("\\d*")) {
			        	cusId.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    });
	  		cusPhone.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	cusPhone.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
	  		houseNumber.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	houseNumber.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
	  		favTeam.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	favTeam.setText(newValue.replaceAll("[^\\d]", ""));
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
	  		cusStreet.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\sa-zA-Z*")) {
		        	cusStreet.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
		        }
		    });
	  		 
	  }
	    /**
	     * goes back to previous window
	     * @param event back button clicked
	     */
    @FXML
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }

}
