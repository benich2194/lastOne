package view;

import java.io.IOException;
import Controller.SysData;
import Exceptions.IdExistsException;
import Exceptions.InvalidInputException;
import Exceptions.MissingInputException;
import Model.Address;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	/**
	 * add coach fxml fields
	 */
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
	/**
	 * adds coach once add coach button is pressed
	 * @param event coach button is pressed
	 * @throws IOException input exception might occuer
	 * @throws MissingInputException missing input exception that i created.
	 * @throws InvalidInputException if instead of numbers in some fields text was input
	 */
	    void addCoach(ActionEvent event) throws IOException, MissingInputException,InvalidInputException, IdExistsException {
		int flag =0;
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Add Coach");
			alert.setHeaderText("");
			String[] phones=new String[1];
	    	phones[0]=coachPhone.getText();
	    	String street=coachStreet.getText();
	    	String first=firstName.getText();
	    	String last=lastName.getText();
	    	try {
	    		java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
		    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
	    	}catch(NullPointerException e) {
	    		new MissingInputException();
	    	}
	    	
	    	String password=coachPassword.getText();
if(phones[0]==""||street==""||first==""||last==""||coachId.getText()=="") {
	flag=1;
	throw new MissingInputException();
			}
			try{
				Integer id = Integer.parseInt(coachId.getText());
				if(SysData.getInstance().getReceptionists().containsKey(id)||SysData.getInstance().getPlayers().containsKey(id)||SysData.getInstance().getCoachs().containsKey(id)) {
					throw new IdExistsException("coach");
				}
				java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
		    	java.sql.Date work=java.sql.Date.valueOf(startWorkingDate.getValue());
				Integer houseNum=Integer.parseInt(houseNumber.getText());
				E_Levels cl=levelCoach.getSelectionModel().getSelectedItem();
				E_Cities cc=coachCity.getSelectionModel().getSelectedItem();
				if(cl==null||cc==null) {
					throw new NullPointerException();
				}
				else {
				Address ad=new Address(cc,street,houseNum,phones);
				if(SysData.getInstance().getCoachs().containsKey(id)) {
		    		alert.setHeaderText("Unable to added coach.");
		    		alert.setContentText("Coach already exists.");
		    		alert.show();
		    	}
		    	else {
		    		SysData.getInstance().addCoach(id,password, first, last, bday, work,cl , ad);
		    		
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
			}catch(NumberFormatException e) {
				if(flag==1) {
					
				}
				else {
					if(houseNumber.getText()==""&&coachId.getText()=="") {}
					else {
						new InvalidInputException();
					}
					
				}
				
				
			}catch(NullPointerException e) {
				new MissingInputException();
			}catch(IdExistsException e) {
				
			}
	    	}
	 /**
	  * goes back to previous page
	  * @param event back button is pressed
	  */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}
	/**
	 * initializes combobox/lists, making sure every text field will be only letters or only numbers, as desired
	 */
	public void initialize() {
		coachCity.getItems().addAll(E_Cities.values());
		levelCoach.getItems().addAll(E_Levels.values());
		 coachId.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	coachId.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
		 houseNumber.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	houseNumber.setText(newValue.replaceAll("[^\\d]", ""));
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
		 coachStreet.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\sa-zA-Z*")) {
		        	coachStreet.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
		        }
		    });
		 coachPhone.textProperty().addListener((observable, oldValue, newValue) -> {
		        if (!newValue.matches("\\d*")) {
		        	coachPhone.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    });
		 
		
	}
	
}
