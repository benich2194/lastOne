package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ObjectExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6814556722112607651L;
	public ObjectExistsException(String m) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(m+" "+"already exists");
		alert.setHeaderText("");
		alert.setHeaderText("Failed to continue,"+m);
		alert.show();
	}
}
