package view.modify.controller;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Trophy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class modifyTrophyController {

    @FXML
    private AnchorPane modifyTrophy;

    @FXML
    private Label lblMessage;

    @SuppressWarnings("rawtypes")
	@FXML
    private ComboBox<Trophy> troList;

    @FXML
    private Button back;

    @FXML
    private DatePicker datepicker;

    @FXML
    private Button buttonModify;

    @FXML
    private Label datelbl;

    /**
     * initializes trophy list
     */
    public void initialize() {
    	troList.getItems().clear();
    	if(SysData.getInstance().getTrophies().size()>0) {
    		troList.getItems().addAll(SysData.getInstance().getTrophies());
    	}
    	buttonModify.setVisible(false);
    	datelbl.setVisible(false);
    	datepicker.setVisible(false);
    }
    
    @FXML
    void showDP(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Modify Trophy");
		alert.setHeaderText("");
    	buttonModify.setVisible(true);
    	datelbl.setVisible(true);
    	datepicker.setVisible(true);
    }
    
    @FXML
    void hidelbl(ActionEvent event) {
    	lblMessage.setText("");
    }
    
    @FXML
    void modifyPressed(ActionEvent event) {
		try {
			if(troList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			else {
				if(SysData.getInstance().getTrophies().contains(troList.getSelectionModel().getSelectedItem()))
				{
					if(datepicker.getValue()==null)
						throw new MissingInputException("Please choose a Date");
					else {
						java.sql.Date datepick = java.sql.Date.valueOf(datepicker.getValue());
						troList.getSelectionModel().getSelectedItem().setTrophyWinningDate(datepick);
						lblMessage.setText("Successfully modified Trophy!");
						initialize();
					}
				}
				else
					lblMessage.setText("Unable to Modify Trophy.");
			}
		}catch(ListNotSelectedException e) {
			
		}
		catch(MissingInputException e) {
					
		}
    	
    } //End of modifyPressed Method

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
