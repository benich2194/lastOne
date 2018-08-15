package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ObjectNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362900764996268211L;
	public ObjectNotExistException(String m) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(m+" "+"does not exist");
		alert.setHeaderText("");
		alert.setHeaderText("Failed to continue,"+m+" does not exist");
		alert.show();
	}

}
