package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PasswordTooShortException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6690254922366757380L;
	public PasswordTooShortException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Password");
		alert.setHeaderText("");
		alert.setHeaderText("Password is too short.");
		alert.setContentText("Please choose password more than 3 digits");
		alert.show();
	}
}
