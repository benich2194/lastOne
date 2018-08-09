package Exceptions;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidInputException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958964505912431852L;
	public InvalidInputException(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		alert.setHeaderText("");
		alert.setHeaderText("Failed to login.");
		alert.setContentText(message);
		alert.show();
	}
}
