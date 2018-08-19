package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidBirthdateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7989097461053881096L;
	public InvalidBirthdateException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Birthday");
		alert.setHeaderText("");
		alert.setHeaderText("Birthday is after today's date, change it please");
		alert.show();
	}
}
