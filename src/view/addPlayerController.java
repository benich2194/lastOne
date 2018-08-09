package view;

import java.io.IOException;

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
import utils.E_Position;

public class addPlayerController {

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
    void addPlayer(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player");
		alert.setHeaderText("");
    	Integer id = Integer.parseInt(playerId.getText());
    	String first=firstName.getText();
    	String last=lastName.getText();
    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
    	String[] phones=new String[1];
    	phones[0]=playerPhone.getText();
    	String street=playerStreet.getText();
    	Integer houseNum=Integer.parseInt(houseNumber.getText());
    	Address ad=new Address(playerCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
    	Long val=Long.parseLong(playerValue.getText());
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
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	playerPosition.getItems().addAll(E_Position.values());
    	rightLeg.getItems().add(true);
    	rightLeg.getItems().add(false);
    	playerCity.getItems().addAll(E_Cities.values());
    	playerLevel.getItems().addAll(E_Levels.values());
    }

}
