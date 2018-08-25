package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EmailNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3079820755825722832L;
	public EmailNotValidException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Email");
		alert.setHeaderText("");
		alert.setHeaderText("Email is invalid, please enter a valid email");
		alert.show();
	}
}
