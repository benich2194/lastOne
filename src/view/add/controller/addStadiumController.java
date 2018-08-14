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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import view.Main;
import view.WindowManager;

public class addStadiumController {
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

	@FXML
	private Label labelSuccess;

	@FXML
	private Button clearButton;

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
		id.setEditable(true);
		id.setDisable(false);
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
		id.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				id.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
	}

	/**
	 * adds stadium
	 * 
	 * @param event add button is pressed
	 * @throws MissingInputException
	 * @throws ListNotSelectedException
	 */
	@FXML
	void addStadium(ActionEvent event) throws MissingInputException, ListNotSelectedException, NumberFormatException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Stadium");
		alert.setHeaderText("");
		try {
			// ID is size of the map + 1, if exists, it will keep adding 1
			if(this.id.getText().isEmpty()) {
				throw new MissingInputException("id");
			}
			Integer id = Integer.parseInt(this.id.getText());
			String NAME = name.getText();
			if (NAME.isEmpty()) {
				throw new MissingInputException("name");
			}

			if (houseNumber.getText().isEmpty()) {
				throw new MissingInputException("house number");
			}
			Integer HOUSENUM = Integer.parseInt(houseNumber.getText());
			if (capacity.getText().isEmpty()) {
				throw new MissingInputException("capacity");
			}
			Integer CAPACITY = Integer.parseInt(capacity.getText());
			String PHONE = phone.getText();
			if (PHONE.isEmpty()) {
				throw new MissingInputException("phone");
			}
			E_Cities c = cityList.getSelectionModel().getSelectedItem();
			if (c == null) {
				throw new ListNotSelectedException();
			}
			String STREET = street.getText();
			if (STREET.isEmpty()) {
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
					labelSuccess.setText("Stadium "+id+" was added succesfully!");
					cityList.valueProperty().set(null);
					this.id.setText("");
					street.setText("");
					capacity.setText("");
					name.setText("");
					phone.setText("");
					houseNumber.setText("");
				} else {
					alert.setHeaderText("Unable to add stadium");
					alert.setContentText("Stadium wasn't added to stadiums.");
					alert.show();
				}
			}

		} catch (NumberFormatException e) {

		} catch (ListNotSelectedException e) {

		} catch (MissingInputException e) {

		}
	}
	/**
	 * clears form
	 * @param event clear button is pressed
	 */
	@FXML
	void clearForm(ActionEvent event) {
		cityList.valueProperty().set(null);
		id.setText("");
		street.setText("");
		capacity.setText("");
		name.setText("");
		phone.setText("");
		houseNumber.setText("");
		labelSuccess.setText("");
	}

}
