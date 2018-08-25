package view.add.controller;

import Controller.SysData;
import Exceptions.InvalidInputException;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.ObjectNotExistException;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.Constants;
import utils.E_Levels;
import view.WindowManager;

public class addTeamController {
	/**
	 * fxml fields
	 */
	@FXML
	private AnchorPane addTeam;

	@FXML
	private Button back;

	@FXML
	private TextField teamId;

	@FXML
	private TextField teamName;

	@FXML
	private TextField teamValue;

	@FXML
	private ComboBox<Stadium> teamStadiumId;

	@FXML
	private ComboBox<E_Levels> teamLevel;

	@FXML
	private Button pushToAdd;
	
	@FXML
	private Label labelSuccess;
	
    @FXML
    private Button clearButton;

	/**
	 * Adds Team to database
	 * 
	 * @param event add team button is pressed
	 * @throws MissingInputException
	 * @throws ObjectNotExistException 
	 * @throws InvalidInputException
	 */
	@FXML
	void addTeam(ActionEvent event) throws MissingInputException,ListNotSelectedException{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Stadium");
		alert.setHeaderText("");
		try {
			
				if(teamId.getText().isEmpty()) {
					throw new MissingInputException("team id");
				}
				if(teamValue.getText().isEmpty()) {
					throw new MissingInputException("team value");
				}
				if(teamStadiumId.getSelectionModel().getSelectedItem()==null) {
					throw new ListNotSelectedException();
				}
				if(teamValue.getText().isEmpty()) {
					throw new MissingInputException("team value");
				}
				if(teamName.getText().isEmpty()) {
					throw new MissingInputException("team name");
				}
			 else {
				Integer id=Integer.parseInt(teamId.getText());
				String name = teamName.getText();
				Integer value = Integer.parseInt(teamValue.getText());
				Integer stadium = teamStadiumId.getSelectionModel().getSelectedItem().getId();
				if (id==null || name.isEmpty() || value==null || stadium==null) {
					alert.setHeaderText("Unable to add team");
					alert.setContentText("Invalid input.");
					alert.show();
				}
				if (teamLevel.getSelectionModel().getSelectedItem() == null) {
					throw new ListNotSelectedException();
				} else {
					if (SysData.getInstance().getTeams().containsKey(id)) {
						alert.setHeaderText("Unable to add team");
						alert.setContentText("Team with the same id already exists.");
						alert.show();
					} else {
						SysData.getInstance().addTeam(id, name, value, teamLevel.getSelectionModel().getSelectedItem(),
								stadium);
						if (SysData.getInstance().getTeams().containsKey(id)) {
							labelSuccess.setText("Team "+id+" was added succesfully!");
					 		teamLevel.valueProperty().set(null);
					 		teamStadiumId.valueProperty().set(null);
					 		teamName.setText("");
					 		teamValue.setText("");
					 		teamId.setText("");
							
						} else {
							if(teamStadiumId.getSelectionModel().getSelectedItem().getTeams().size()==Constants.MAX_TEAMS_FOR_STADIUM)
							{
								alert.setHeaderText("Unable to add Team");
								alert.setContentText("Stadium has reached it's maximun amount of teams. Please choose another stadium.");
								alert.show();
							}
							else {
								alert.setHeaderText("Unable to add Team");
								alert.setContentText("Team wasn't added to stadiums.");
								alert.show();
							}
						}
					}
				}
			}
		} catch (MissingInputException e) {
		}
		 catch (ListNotSelectedException e) {

		}

	}

	/**
	 * Initializes scene's functionality
	 */
	public void initialize() {
		teamLevel.getItems().addAll(E_Levels.values());
		teamStadiumId.getItems().addAll(SysData.getInstance().getStadiums().values());
		teamId.setEditable(true);
		teamId.setDisable(false);
		teamName.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\sa-zA-Z*")) {
	        	teamName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
	        }
	    });
		teamId.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	teamId.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
		teamValue.textProperty().addListener((observable, oldValue, newValue) -> {
	        if (!newValue.matches("\\d*")) {
	        	teamValue.setText(newValue.replaceAll("[^\\d]", ""));
	        }
	    });
	}
	
     @FXML
	 void clearForm(ActionEvent event) {
 		teamLevel.valueProperty().set(null);
 		teamStadiumId.valueProperty().set(null);
 		teamName.setText("");
 		teamValue.setText("");
 		labelSuccess.setText("");
 		teamId.setText("");
	 }
	
	/**
	 * goes back to previous screen
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}

}
