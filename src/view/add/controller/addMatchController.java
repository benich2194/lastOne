package view.add.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.ObjectExistsException;
import Model.Team;
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

public class addMatchController {

    @FXML
    private AnchorPane addMatch;

    @FXML
    private Button back;

    @FXML
    private TextField matchId;

    @FXML
    private TextField Extra;

    @FXML
    private ComboBox<Team> Home;

    @FXML
    private ComboBox<Team> Away;

    @FXML
    private TextField homeS;

    @FXML
    private TextField awayS;

    @FXML
    private Button addButton;

    @FXML
    private DatePicker matchDate;
    /**
     * adds match to database
     * @param event add match button is pressed
     * @throws MissingInputException
     * @throws ListNotSelectedException
     * @throws ObjectExistsException
     */
    @FXML
    void addMatch(ActionEvent event) throws MissingInputException, ListNotSelectedException, ObjectExistsException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Match");
		alert.setHeaderText("");
		try {
			//ID is size of the map + 1, if exists, it will keep adding 1
	    	Integer id = SysData.getInstance().getMatchs().size()+1;
	    	while(SysData.getInstance().getMatchs().containsKey(id))
	    		id++;
	    	if(Extra.getText().isEmpty()) {
	    		throw new MissingInputException("extra time");
	    	}
	    	Integer extra=Integer.parseInt(Extra.getText());
	    	java.sql.Date date = null;
	    	if(matchDate!=null) {
	    		date=java.sql.Date.valueOf(matchDate.getValue());
	    	}
	    	else {
	    		throw new MissingInputException("date of match");
	    	}
	    	if(SysData.getInstance().getMatchs().containsKey(id)) {
	    		throw new ObjectExistsException("match");
	    	}
	    	else {
	    		if(Home.getSelectionModel().getSelectedItem()==null||Away.getSelectionModel().getSelectedItem()==null) {
	    			throw new ListNotSelectedException();
	    		}
	    		if(homeS.getText().isEmpty()) {
	    			throw new MissingInputException("home score");
	    		}
	    		if(awayS.getText().isEmpty()) {
	    			throw new MissingInputException("away score");
	    		}
	    		SysData.getInstance().addMatch(id, date, extra, Home.getSelectionModel().getSelectedItem().getId(), Away.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(homeS.getText()), Integer.parseInt(awayS.getText()));
		    	if(SysData.getInstance().getMatchs().containsKey(id)) {
		    		alert.setHeaderText("Added Match");
		    		alert.setContentText("Match added successfully.");
		    		alert.show();
		    	}
		    	else {
		    		alert.setHeaderText("Unable to add Match.");
		    		alert.setContentText("Match wasn't added.");
		    		alert.show();
		    	}
	    	}
		}catch(MissingInputException e) {
			
		}catch(ListNotSelectedException e) {
			
		}catch(ObjectExistsException e) {
			
		}
    }
   /**
    * goes back to previous screen
    * @param event back button is pressed
    */
    @FXML
    void goBack(ActionEvent event) {
      	WindowManager.goBack();
    }
    /**
     * initializes list and automatic id, defines text fields to be numbers only or letters only
     */
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		Home.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		Away.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	matchId.setEditable(false);
    	matchId.setDisable(true);
    	Integer idCurrent = SysData.getInstance().getMatchs().size()+1;
    	while(SysData.getInstance().getMatchs().containsKey(idCurrent))
    		idCurrent++;
    	matchId.setText(idCurrent.toString());
    	Extra.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	Extra.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
    	homeS.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	homeS.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
    	awayS.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	awayS.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
    }
}
