package view;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Position;

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
    void addPlayer(ActionEvent event)  throws MissingInputException,ListNotSelectedException, InvalidInputException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player");
		alert.setHeaderText("");
		try {
			Long val=Long.parseLong(playerValue.getText());
			Integer id = Integer.parseInt(playerId.getText());
	    	String first=firstName.getText();
	    	String last=lastName.getText();
	    	String[] phones=new String[1];
	    	phones[0]=playerPhone.getText();
	    	String street=playerStreet.getText();
	    	Integer houseNum=Integer.parseInt(houseNumber.getText());
	    	if(playerValue.getText()==""||playerId.getText()==""||first==""||last==""||playerPhone.getText()==""||street==""||houseNumber.getText()=="") {
	    		throw new MissingInputException();
	    	}
	    	if(birthDate.getValue()==null||startWorkingDate.getValue()==null) {
	    		throw new MissingInputException();
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
		    		alert.setHeaderText("Added Player");
		    		alert.setContentText("Player added successfully.");
		    		alert.show();
		    	}
		    	else {
		    		alert.setHeaderText("Unable to add Player.");
		    		alert.setContentText("Player wasn't added.");
		    		alert.show();
		    	}
	    	}
		}catch(ListNotSelectedException e) {
			
		}catch(MissingInputException e) {
			
		}catch(NumberFormatException e) {
			throw new InvalidInputException();
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

}
