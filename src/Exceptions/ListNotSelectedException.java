package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ListNotSelectedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1677381654974711655L;
	public ListNotSelectedException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("");
		alert.setHeaderText("Nothing was selected in the list.");
		alert.setContentText("Please choose from list.");
		alert.show();
	}
	public ListNotSelectedException(String m) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("");
		alert.setHeaderText("Nothing was selected in the list.");
		alert.setContentText(m);
		alert.show();
	}
}
