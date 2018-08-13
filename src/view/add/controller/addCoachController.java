package view.add.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.IdExistsException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Levels;
import view.WindowManager;
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
	private PasswordField coachPassword;

	@FXML
	private Button clearButton;

	@FXML
	private Label labelSuccess;

	@FXML
	/**
	 * adds coach once add coach button is pressed
	 * 
	 * @param event coach button is pressed
	 * @throws IOException           input exception might occuer
	 * @throws MissingInputException missing input exception that i created.
	 * @throws InvalidInputException if instead of numbers in some fields text was
	 *                               input
	 */
	void addCoach(ActionEvent event) throws MissingInputException, IdExistsException, ListNotSelectedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Coach");
		alert.setHeaderText("");
		String[] phones = new String[1];
		phones[0] = coachPhone.getText();
		String street = coachStreet.getText();
		String first = firstName.getText();
		String last = lastName.getText();
		String password = coachPassword.getText();
		try {
			if (coachPhone.getText().isEmpty()) {
				throw new MissingInputException("coach phone");
			}
			if (street.isEmpty()) {
				throw new MissingInputException("street name");
			}
			if (first.isEmpty()) {
				throw new MissingInputException("first name");
			}
			if (last.isEmpty()) {
				throw new MissingInputException("last name");
			}
			if (coachId.getText().isEmpty()) {
				throw new MissingInputException("coach id");
			}
			Integer id = Integer.parseInt(coachId.getText());
			if (SysData.getInstance().getReceptionists().containsKey(id)
					|| SysData.getInstance().getPlayers().containsKey(id)
					|| SysData.getInstance().getCoachs().containsKey(id)) {
				throw new IdExistsException("coach");
			}
			if (birthDate.getValue() == null) {
				throw new MissingInputException("birth date");
			}
			if (startWorkingDate.getValue() == null) {
				throw new MissingInputException("start working date");
			}
			if (houseNumber.getText().isEmpty()) {
				throw new MissingInputException("house number");
			}
			java.sql.Date bday = java.sql.Date.valueOf(birthDate.getValue());
			java.sql.Date work = java.sql.Date.valueOf(startWorkingDate.getValue());
			Integer houseNum = Integer.parseInt(houseNumber.getText());
			E_Levels cl = levelCoach.getSelectionModel().getSelectedItem();
			E_Cities cc = coachCity.getSelectionModel().getSelectedItem();
			if (cl == null || cc == null) {
				throw new ListNotSelectedException();
			} else {
				Address ad = new Address(cc, street, houseNum, phones);
				if (SysData.getInstance().getCoachs().containsKey(id)) {
					alert.setHeaderText("Unable to added coach.");
					alert.setContentText("Coach already exists.");
					alert.show();
				} else {
					SysData.getInstance().addCoach(id, password, first, last, bday, work, cl, ad);

					if (SysData.getInstance().getCoachs().containsKey(id)) {
						labelSuccess.setText("Coach "+id+" was added succesfully!");
						coachCity.valueProperty().set(null);
						startWorkingDate.valueProperty().set(null);
						birthDate.valueProperty().set(null);
						coachPassword.setText("");
						coachId.setText("");
						coachPhone.setText("");
						coachStreet.setText("");
						firstName.setText("");
						lastName.setText("");
						houseNumber.setText("");
						levelCoach.valueProperty().set(null);
						
					} else {
						alert.setHeaderText("Unable to added coach.");
						alert.setContentText("Coach wasn't added.");
						alert.show();
					}
				}
			}
		} catch (IdExistsException e) {

		} catch (MissingInputException e) {

		} catch (ListNotSelectedException e) {

		}
	}

	/**
	 * goes back to previous page
	 * 
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}

	/**
	 * initializes combobox/lists, making sure every text field will be only letters
	 * or only numbers, as desired
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
	/**
	 * clear everything in the form
	 * @param event clear button is pressed
	 */
	@FXML
	void clearForm(ActionEvent event) {
		coachCity.valueProperty().set(null);
		startWorkingDate.valueProperty().set(null);
		birthDate.valueProperty().set(null);
		coachPassword.setText("");
		coachId.setText("");
		coachPhone.setText("");
		coachStreet.setText("");
		firstName.setText("");
		lastName.setText("");
		houseNumber.setText("");
		levelCoach.valueProperty().set(null);
		labelSuccess.setText("");
	}

}
