package view.modify.controller;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class modifyMatchController {

    @FXML
    private Button buttonTime;

    @FXML
    private AnchorPane modifyMatch;

    @FXML
    private Button buttonDate;

    @FXML
    private TextField timepicker;

    @FXML
    private Button back;

    @FXML
    private DatePicker datepicker;

    @FXML
    private ComboBox<Match> comboBoxMatches;

    /**
     * initializes matches Combobox
     */
    public void initialize() {
    	comboBoxMatches.getItems().clear();
    	if(SysData.getInstance().getMatchs().size()>0) {
    		comboBoxMatches.getItems().addAll(SysData.getInstance().getMatchs().values());
    	}
    	buttonDate.setVisible(false);
    	buttonTime.setVisible(false);
    	datepicker.setVisible(false);
    	timepicker.setVisible(false);
    }
    
    @FXML
    void showSP(ActionEvent event) {
    	buttonDate.setVisible(true);
    	buttonTime.setVisible(true);
    	datepicker.setVisible(true);
    	timepicker.setVisible(true);
    }

    @FXML
    void changeDate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Modify Match");
		alert.setHeaderText("");
    	try {
			if(comboBoxMatches.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			else {
				if(SysData.getInstance().getMatchs().containsValue(comboBoxMatches.getSelectionModel().getSelectedItem()))
				{
					if(datepicker.getValue()==null)
						throw new MissingInputException("Please choose a Date");
					else {
						java.sql.Date datepick = java.sql.Date.valueOf(datepicker.getValue());
						comboBoxMatches.getSelectionModel().getSelectedItem().setStartDateTime(datepick);
						alert.setHeaderText("Successfully Modified Match");
						alert.setContentText("The Match's Date has been updated.");
						alert.show();
						initialize();
					}
				}
				else {
					alert.setHeaderText("Unable To Modify Match");
					alert.setContentText("The Match's Date has not been updated.");
					alert.show();
				}
			}
		}catch(ListNotSelectedException e) {
			
		}
		catch(MissingInputException e) {
					
		}
    }

    @FXML
    void changeTime(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Modify Match");
		alert.setHeaderText("");
    	try {
			if(comboBoxMatches.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			else {
				if(SysData.getInstance().getMatchs().containsValue(comboBoxMatches.getSelectionModel().getSelectedItem()))
				{
					if(timepicker.getText()=="")
						throw new MissingInputException("Please set extra time");
					else {
						comboBoxMatches.getSelectionModel().getSelectedItem().setExtraTime(Integer.parseInt(timepicker.getText()));
						alert.setHeaderText("Successfully Modified Match");
						alert.setContentText("The Match's Extra Time has been updated.");
						alert.show();
						initialize();
					}
				}
				else {
					alert.setHeaderText("Unable To Modify Match");
					alert.setContentText("The Match's Extra Time has not been updated.");
					alert.show();
				}
			}
		}catch(ListNotSelectedException e) {
			
		}
		catch(MissingInputException e) {
					
		}
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
