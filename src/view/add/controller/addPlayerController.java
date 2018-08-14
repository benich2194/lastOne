package view.add.controller;

import java.io.IOException;

import Controller.SysData;
import Exceptions.InvalidInputException;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Position;
import view.WindowManager;

public class addPlayerController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane addPlayer;

    @FXML
    private Button back;

    @FXML
    private TextField playerId;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private DatePicker birthDate;

    @FXML
    private DatePicker startWorkingDate;

    @FXML
    private ComboBox<E_Levels> playerLevel;

    @FXML
    private ComboBox<E_Cities> playerCity;

    @FXML
    private TextField houseNumber;

    @FXML
    private TextField playerStreet;

    @FXML
    private TextField playerPhone;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<Boolean> rightLeg;

    @FXML
    private TextField playerValue;

    @FXML
    private ComboBox<E_Position> playerPosition;
    
    @FXML
    private Button clearButton;

    @FXML
    private Label labelSuccess;

    /**
     * adds a new player to data base
     * @param event add button is pressed
     * @throws MissingInputException
     * @throws ListNotSelectedException
     * @throws InvalidInputException
     */
    @FXML
    void addPlayer(ActionEvent event)  throws MissingInputException,ListNotSelectedException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player");
		alert.setHeaderText("");
		try {
			if(playerValue.getText().isEmpty()) {
				throw new MissingInputException("player value");
			}
			Long val=Long.parseLong(playerValue.getText());
			if(playerId.getText().isEmpty()) {
				throw new MissingInputException("player id");
			}
			Integer id = Integer.parseInt(playerId.getText());
	    	String first=firstName.getText();
	    	String last=lastName.getText();
	    	String[] phones=new String[1];
	    	phones[0]=playerPhone.getText();
	    	String street=playerStreet.getText();
	    	if(houseNumber.getText().isEmpty()) {
	    		throw new MissingInputException("house number");
	    	}
	    	Integer houseNum=Integer.parseInt(houseNumber.getText());
	    	if(playerValue.getText().isEmpty()) {
	    		throw new MissingInputException("player value");
	    	}
	    	if(playerId.getText().isEmpty()) {
	    		throw new MissingInputException("player id");
	    	}
	    	if(birthDate.getValue()==null||startWorkingDate.getValue()==null) {
	    		throw new MissingInputException("date");
	    	}
	    	if(first.isEmpty()) {
	    		throw new MissingInputException("first name");
	    	}
	    	if(last.isEmpty()) {
	    		throw new MissingInputException("last name");
	    	}
	    	if(playerPhone.getText().isEmpty()) {
	    		throw new MissingInputException("phone number");
	    	}
	    	if(street.isEmpty()) {
	    		throw new MissingInputException("street");
	    	}
	    	
	    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
	    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
	    	if(playerCity.getSelectionModel().getSelectedItem()==null||playerLevel.getSelectionModel().getSelectedItem()==null||rightLeg.getSelectionModel().getSelectedItem()==null||playerPosition.getSelectionModel().getSelectedItem()==null) {
	    		throw new ListNotSelectedException();
	    	}
	    	Address ad=new Address(playerCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
	    	
	    	if(SysData.getInstance().getPlayers().containsKey(id)) {
	    		alert.setHeaderText("Unable to add player.");
	    		alert.setContentText("Player already exists.");
	    		alert.show();
	    	}
	    	else {
	    		SysData.getInstance().addPlayer(id,"0",first,last,bday,work,playerLevel.getSelectionModel().getSelectedItem(),val,rightLeg.getSelectionModel().getSelectedItem(),playerPosition.getSelectionModel().getSelectedItem(),ad);
		    	if(SysData.getInstance().getPlayers().containsKey(id)) {
		    		labelSuccess.setText("Player "+id+" was added succesfully!");
		    		playerCity.valueProperty().set(null);
		    		birthDate.valueProperty().set(null);
		    		startWorkingDate.valueProperty().set(null);
		    		playerPosition.valueProperty().set(null);
		    		playerId.setText("");
		    		playerPhone.setText("");
		    		playerStreet.setText("");
		    		firstName.setText("");
		    		lastName.setText("");
		    		houseNumber.setText("");
		    		playerValue.setText("");
		    		rightLeg.valueProperty().set(null);
		    		playerLevel.valueProperty().set(null);
		    	}
		    	else {
		    		alert.setHeaderText("Unable to add Player.");
		    		alert.setContentText("Player wasn't added.");
		    		alert.show();
		    	}
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(MissingInputException e) {
    	
    }
    }
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event){
    	WindowManager.goBack();
    }
    /**
     * initializes lists
     */
    public void initialize() {
    	playerPosition.getItems().addAll(E_Position.values());
    	rightLeg.getItems().add(true);
    	rightLeg.getItems().add(false);
    	playerCity.getItems().addAll(E_Cities.values());
    	playerLevel.getItems().addAll(E_Levels.values());
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
    	 playerStreet.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\sa-zA-Z*")) {
		        	playerStreet.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
		        }
		    });
    	 playerPhone.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	playerPhone.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
    	 playerValue.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	playerValue.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
    	 playerId.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	playerId.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
    	 houseNumber.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	houseNumber.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
    	 
    }
    /**
     * clears form
     * @param event clear button is pressed
     */
    @FXML
    void clearForm(ActionEvent event) {
    	playerCity.valueProperty().set(null);
		birthDate.valueProperty().set(null);
		startWorkingDate.valueProperty().set(null);
		playerPosition.valueProperty().set(null);
		playerId.setText("");
		playerPhone.setText("");
		playerStreet.setText("");
		firstName.setText("");
		lastName.setText("");
		houseNumber.setText("");
		playerValue.setText("");
		rightLeg.valueProperty().set(null);
		playerLevel.valueProperty().set(null);
		labelSuccess.setText("");
    }

}
