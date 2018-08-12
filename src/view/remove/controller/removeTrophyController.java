package view.remove.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Trophy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeTrophyController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removeTrophy;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Trophy> troList;

    @FXML
    private Button removeButton;
    /**
     * goes back to previous screen
     * @param event back button isp ressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    /**
     * removes trophy
     * @param event remove button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removeTrophy(ActionEvent event) throws ListNotSelectedException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Trophy");
		alert.setHeaderText("");
		try {
			if(troList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			if(SysData.getInstance().getTrophies().remove(troList.getSelectionModel().getSelectedItem())) {
				alert.setHeaderText("Removed trophy");
				alert.setContentText("Removed trophy successfully.");
				alert.show();
			}
			else {
				alert.setHeaderText("unable to remove trophy");
				alert.setContentText("Trophy wasn't removed successfully.");
				alert.show();
			}
		}catch(ListNotSelectedException e) {
			
		}
		
    }
    /**
     * initializes trophy list
     */
    public void initialize() {
    	if(SysData.getInstance().getTrophies().size()>0) {
    		troList.getItems().addAll(SysData.getInstance().getTrophies());
    	}
    }
}
