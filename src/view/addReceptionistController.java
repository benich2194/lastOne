package view;

import java.io.IOException;

import Controller.SysData;
import Model.Address;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.E_Cities;

public class addReceptionistController {

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
    void addRecep(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Receptionist");
		alert.setHeaderText("");
    	Integer id=Integer.parseInt(recepId.getText());
    	String first=firstName.getText();
    	String last=lastName.getText();
    	java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
    	String[] phones=new String[1];
    	phones[0]=recepPhone.getText();
    	String street=recepStreet.getText();
    	Integer houseNum=Integer.parseInt(houseNumber.getText());
    	Address ad=new Address(recepCity.getSelectionModel().getSelectedItem(),street,houseNum,phones);
    	if(SysData.getInstance().getReceptionists().containsKey(id)) {
    		alert.setHeaderText("Unable to added Receptionist.");
    		alert.setContentText("Receptionist already exists.");
    		alert.show();
    	}
    	else {
    		SysData.getInstance().addReceptionist(id, first, last, bday, work, ad);
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
    }
    public void initialize() {
  		recepCity.getItems().addAll(E_Cities.values());
  		
  }
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addReceptionist.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/receptionistMenuAdmin.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();	
    }

}
