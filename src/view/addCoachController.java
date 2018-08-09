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
import utils.E_Levels;
import utils.E_Cities;

public class addCoachController {

	  @FXML
	    private AnchorPane addCoachModif;

	    @FXML
	    private Button back;

	    @FXML
	    private TextField coachId;

	    @FXML
	    private TextField firstName;

	    @FXML
	    private TextField lastName;

	    @FXML
	    private DatePicker birthDate;

	    @FXML
	    private DatePicker startWorkingDate;

	    @FXML
	    private ComboBox<E_Levels> levelCoach;

	    @FXML
	    private ComboBox<E_Cities> coachCity;

	    @FXML
	    private TextField houseNumber;

	    @FXML
	    private TextField coachStreet;

	    @FXML
	    private TextField coachPhone;

	    @FXML
	    private Button addButton;
	    
	    @FXML
	    private TextField coachPassword;

	    @FXML
	    void addCoach(ActionEvent event) throws IOException {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Add Coach");
			alert.setHeaderText("");
	    	Integer id = Integer.parseInt(coachId.getText());
	    	String first=firstName.getText();
	    	String last=lastName.getText();
	    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
	    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
	    	String[] phones=new String[1];
	    	phones[0]=coachPhone.getText();
	    	String street=coachStreet.getText();
	    	Integer houseNum=Integer.parseInt(houseNumber.getText());
	    	Address ad=new Address(coachCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
	    	String password=coachPassword.getText();
	    	if(SysData.getInstance().getCoachs().containsKey(id)) {
	    		alert.setHeaderText("Unable to added coach.");
	    		alert.setContentText("Coach already exists.");
	    		alert.show();
	    	}
	    	else {
	    		SysData.getInstance().addCoach(id,password, first, last, bday, work, levelCoach.getSelectionModel().getSelectedItem(), ad);
	    		
		    	if(SysData.getInstance().getCoachs().containsKey(id)) {
		    		alert.setHeaderText("Added coach");
		    		alert.setContentText("Coach added successfully.");
		    		alert.show();
		    	}
		    	else {
		    		alert.setHeaderText("Unable to added coach.");
		    		alert.setContentText("Coach wasn't added.");
		    		alert.show();
		    	}
	    	}
	    	
	    }


    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	coachCity.getItems().addAll(E_Cities.values());
    	levelCoach.getItems().addAll(E_Levels.values());
    }
}
