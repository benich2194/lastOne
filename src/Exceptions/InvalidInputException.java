package Exceptions;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidInputException extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958964505912431852L;
	public InvalidInputException(String m) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("");
		alert.setHeaderText(m);
		alert.show();
	}
}
