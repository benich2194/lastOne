package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MissingInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1332577639227441532L;

	public MissingInputException() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		alert.setHeaderText("");
		alert.setHeaderText("Failed to login.");
		alert.setContentText("Please fill both user field and password field");
		alert.show();
	}
}
