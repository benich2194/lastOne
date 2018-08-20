package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IdExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1130100319421884731L;

	public IdExistsException(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid ID entered");
		alert.setHeaderText("");
		alert.setHeaderText("Id already exists in system.");
		alert.setContentText("Please choose a different id for:"+message);
		alert.show();
	}
}
