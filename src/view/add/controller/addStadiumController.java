package view.add.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import view.Main;
import view.WindowManager;

public class addStadiumController extends Main {
	/**
	 * fx fields
	 */
	@FXML
	private AnchorPane addStadium;

	@FXML
	private Button back;

	@FXML
	private TextField id;

	@FXML
	private TextField name;

	@FXML
	private ComboBox<E_Cities> cityList;

	@FXML
	private TextField phone;

	@FXML
	private TextField street;

	@FXML
	private TextField capacity;

	@FXML
	private TextField houseNumber;

	@FXML
	private Button submit;

	/**
	 * goes back to previous screen
	 * 
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}
	/**
	 * initializes lists, defines textfields for numbers only or letters only
	 */
	public void initialize() {
		cityList.getItems().addAll(E_Cities.values());
		id.setEditable(false);
		id.setDisable(true);

		Integer idCurrent = SysData.getInstance().getStadiums().size() + 1;
		while (SysData.getInstance().getStadiums().containsKey(idCurrent))
			idCurrent++;
		id.setText(idCurrent.toString());
		street.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	street.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		name.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	name.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		capacity.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	capacity.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
		phone.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	phone.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
		houseNumber.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	houseNumber.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
	}
	/**
	 * adds stadium
	 * @param event add button is pressed
	 * @throws MissingInputException
	 * @throws ListNotSelectedException
	 */
	@FXML
	void addStadium(ActionEvent event) throws MissingInputException,ListNotSelectedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Stadium");
		alert.setHeaderText("");
		try {
			// ID is size of the map + 1, if exists, it will keep adding 1
			Integer id = SysData.getInstance().getStadiums().size() + 1;
			while (SysData.getInstance().getStadiums().containsKey(id))
				id++;
			String NAME = name.getText();
			if(NAME=="") {
				throw new MissingInputException("name");
			}
			if(houseNumber.getText()=="") {
				throw new MissingInputException("house number");
			}
			Integer HOUSENUM = Integer.parseInt(houseNumber.getText());
			if(capacity.getText()=="") {
				throw new MissingInputException("capacity");
			}
			Integer CAPACITY = Integer.parseInt(capacity.getText());
			String PHONE = phone.getText();
			if(PHONE=="") {
				throw new MissingInputException("phone");
			}
			E_Cities c = cityList.getSelectionModel().getSelectedItem();
			if(c==null) {
				throw new ListNotSelectedException();
			}
			String STREET = street.getText();
			if(STREET=="") {
				throw new MissingInputException("street");
			}
			String[] phoneArr = new String[1];
			phoneArr[0] = PHONE;
			if (SysData.getInstance().getStadiums().containsKey(id)) {
				alert.setHeaderText("Unable to add stadium");
				alert.setContentText("Stadium with the same ID already exists.");
				alert.show();
			} else {
				SysData.getInstance().addStadium(id, NAME, CAPACITY, c, STREET, HOUSENUM, phoneArr);
				if (SysData.getInstance().getStadiums().containsKey(id)) {
					alert.setHeaderText("Added Stadium");
					alert.setContentText("Stadium was added succesfully");
					alert.show();
				} else {
					alert.setHeaderText("Unable to add stadium");
					alert.setContentText("Stadium wasn't added to stadiums.");
					alert.show();
				}
			}

		}catch(ListNotSelectedException e) {
			
		}catch(MissingInputException e) {
			
		}
		}
		

}
