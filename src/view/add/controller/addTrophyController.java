package view.add.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import Controller.SysData;
import Exceptions.IdExistsException;
import Exceptions.InvalidInputException;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.PasswordTooShortException;
import Model.Address;
import Model.Player;
import Model.Stadium;
import Model.Trophy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import utils.E_Trophy;
import view.WindowManager;

public class addTrophyController {

    @FXML
    private Button addTrophyButton;

    @FXML
    private DatePicker chooseDate;

    @FXML
    private AnchorPane addTrophy;

    @FXML
    private Button back;

    @FXML
    private ComboBox<E_Trophy> chooseTrophy;

    @FXML
    private ComboBox<Object> chooseWinner;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    public void initialize() {
    	chooseTrophy.getItems().addAll(E_Trophy.values());
    	chooseWinner.setVisible(false);
    	chooseDate.setVisible(false);
    	addTrophyButton.setVisible(false);
    }
    
    @FXML
    void showComponents(ActionEvent event) {
    	chooseWinner.getItems().clear();
    	switch(chooseTrophy.getSelectionModel().getSelectedItem()) {
    		case STADIUM_OF_THE_YEAR:
    			chooseWinner.getItems().addAll(SysData.getInstance().getStadiums().values());
    			break;
    		case TEAM_OF_THE_YEAR:
    			chooseWinner.getItems().addAll(SysData.getInstance().getTeams().values());
    			break;
    		case COACH_OF_THE_YEAR:
    			chooseWinner.getItems().addAll(SysData.getInstance().getCoachs().values());
    			break;
    		case PLAYER_OF_THE_YEAR:
    			//chooseWinner.getItems().addAll(SysData.getInstance().getPlayers().values());
    			for(Player p : SysData.getInstance().getPlayers().values())
    			chooseWinner.getItems().add(p);
    			break;
    	}
    	chooseWinner.setVisible(true);
    	chooseDate.setVisible(true);
    	addTrophyButton.setVisible(true);
    }
    
    @FXML
    void goToaddTrophy(ActionEvent event) {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Add Trophy");
    		alert.setHeaderText("");
    		
        	boolean isMyComboBoxEmptyWinner = chooseWinner.getSelectionModel().isEmpty();
        	//Check if a winner was selected
        	if(isMyComboBoxEmptyWinner) {
        		alert.setHeaderText("No Winner was selected");
        		alert.setContentText("Please select a winner from the list.");
        		alert.show();
        	}
        	else {
	        	java.sql.Date datepick = java.sql.Date.valueOf(chooseDate.getValue());
	        	Trophy tt = new Trophy(chooseTrophy.getSelectionModel().getSelectedItem(), chooseWinner.getSelectionModel().getSelectedItem(), datepick); 	
	        	if(SysData.getInstance().getTrophies().contains(tt)) {
	        		alert.setHeaderText("Unable to added Trophy.");
	        		alert.setContentText("Trophy already exists.");
	        		alert.show();
	        	}
	        	else {
	        			if(SysData.getInstance().addTrophy(chooseTrophy.getSelectionModel().getSelectedItem(), chooseWinner.getSelectionModel().getSelectedItem(), datepick)) {
		    	    		alert.setHeaderText("Added Trophy");
		    	    		alert.setContentText("Trophy added successfully.");
		    	    		alert.show();
	        			}
		    	    	else {
		    	    		alert.setHeaderText("Unable to added Trophy.");
		    	    		alert.setContentText("Trophy wasn't added.");
		    	    		alert.show();
		    	    	}
	        	}
        	}
    	
    } // End of add trophy

}
