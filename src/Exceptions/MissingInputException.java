package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MissingInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1332577639227441532L;

	public MissingInputException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Missing Input");
		alert.setHeaderText("");
		alert.setHeaderText("Failed to continue.");
		alert.setContentText("Please fill every field.");
		alert.show();
	}
}
