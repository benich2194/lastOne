package view.add.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.PrimitiveIterator.OfDouble;

import Controller.SysData;
import Exceptions.IdExistsException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class addMatchController {
	/**
	 * fx fields
	 */
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
    private Button clearButton;
    
    @FXML
    private Label labelSuccess;
    
    @FXML
    private ComboBox<Integer> hourList;

    @FXML
    private ComboBox<Integer> minuteList;
    @FXML
    private DatePicker matchDate;

    /**
     * adds match to database
     * @param event add match button is pressed
     * @throws MissingInputException
     * @throws ListNotSelectedException
     * @throws ObjectExistsException
     * @throws IdExistsException 
     */
    @FXML
    void addMatch(ActionEvent event) throws MissingInputException, ListNotSelectedException, ObjectExistsException, IdExistsException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Match");
		alert.setHeaderText("");
		int flag=0;
		try {
			if(matchId.getText().isEmpty()) {
				throw new MissingInputException("match id");
			}
			Integer id=Integer.parseInt(matchId.getText());
			if(SysData.getInstance().getMatchs().containsKey(id)) {
				throw new IdExistsException("match already exists");
			}
	    	if(Extra.getText().isEmpty()) {
	    		throw new MissingInputException("extra time");
	    	}
	    	Integer extra=Integer.parseInt(Extra.getText());
	    	if(matchDate==null) {
	    		throw new MissingInputException("date of match");

	    	}
	    	
	    	
    		if(minuteList.getSelectionModel().getSelectedItem()==null) {
    			throw new ListNotSelectedException("Choose minute");
    		}
    		if(hourList.getSelectionModel().getSelectedItem()==null) {
    			throw new ListNotSelectedException("Choose hour");
    		}
    		Integer minute=minuteList.getSelectionModel().getSelectedItem();
    		Integer hour=hourList.getSelectionModel().getSelectedItem();
    		LocalDate date=matchDate.getValue();
    		LocalTime lt=LocalTime.of(hour, minute);
    		Instant instant = lt.atDate(LocalDate.of(date.getYear(),date.getMonth(), date.getDayOfMonth())).atZone(ZoneId.systemDefault()).toInstant();
			Date time = Date.from(instant);
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
	    		SysData.getInstance().addMatch(id, time, extra, Home.getSelectionModel().getSelectedItem().getId(), Away.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(homeS.getText()), Integer.parseInt(awayS.getText()));
		    	if(SysData.getInstance().getMatchs().containsKey(id)) {
		    		labelSuccess.setText("Match "+matchId.getText()+" added succesfully!");
		    		matchDate.valueProperty().set(null);
		    		Away.valueProperty().set(null);
		    		matchId.setText("");
		    		homeS.setText("");
		    		awayS.setText("");
		    		Extra.setText("");
		    		Home.valueProperty().set(null);
		    		minuteList.getItems().removeAll(minuteList.getItems());
		    		hourList.getItems().removeAll(hourList.getItems());
		    		for(int i=0;i<61;i++) {
		        		minuteList.getItems().add(i);
		        	}
		        	for(int i=0;i<24;i++) {
		        		hourList.getItems().add(i);
		        	}
		    		
		    	}
		    	else {
		    		alert.setHeaderText("Unable to add Match.");
		    		if(flag==1) {
		    			alert.setContentText("Match wasn't added. the teams are the same");
		    			alert.show();
		    		}
		    		else {
		    			alert.setContentText("cannot add match, overlaps with a different match");
		    			alert.show();
		    		}
		    		
		    	}
	    	}
		}catch(MissingInputException e) {
			
		}catch(ListNotSelectedException e) {
			
		}catch(ObjectExistsException e) {
			
		}catch(IdExistsException e) {
			
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
    	matchId.setEditable(true);
    	matchId.setDisable(false);
    	Extra.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	Extra.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
    	matchId.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	matchId.setText(newValue.replaceAll("[^\\d]", ""));
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
    	for(int i=0;i<60;i++) {
    		minuteList.getItems().add(i);
    	}
    	for(int i=0;i<24;i++) {
    		hourList.getItems().add(i);
    	}
    }
    /**
     * clears form
     * @param event clear button is pressed
     */
    @FXML
    void clearForm(ActionEvent event) {
    	matchDate.valueProperty().set(null);
		Away.valueProperty().set(null);
		matchId.setText("");
		homeS.setText("");
		awayS.setText("");
		Extra.setText("");
		Home.valueProperty().set(null);
		labelSuccess.setText("");
		minuteList.getItems().removeAll(minuteList.getItems());
		hourList.getItems().removeAll(hourList.getItems());
		for(int i=0;i<60;i++) {
    		minuteList.getItems().add(i);
    	}
    	for(int i=0;i<24;i++) {
    		hourList.getItems().add(i);
    	}
    }
}
