package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NoValidSubscriptionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5232637848316592718L;

	public NoValidSubscriptionException(String m) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("");
		alert.setHeaderText("No valid subscription for this match");
		alert.setContentText(m);
		alert.show();
	}
}
