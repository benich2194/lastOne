package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IdExistsException extends Exception {
	public IdExistsException(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("");
		alert.setHeaderText("Id already exists in system.");
		alert.setContentText("Please choose a different id for:"+message);
		alert.show();
	}
}
