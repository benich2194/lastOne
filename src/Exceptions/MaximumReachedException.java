package Exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MaximumReachedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1529360071636961382L;
	public MaximumReachedException(String m) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Maximum reached");
		alert.setHeaderText("");
		alert.setHeaderText("Limit has reached.cannot add another one");
		alert.setContentText(m);
		alert.show();
	}

}
