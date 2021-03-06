package view.modify.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.MissingInputException;
import Model.Stadium;
import Model.Team;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.E_Levels;
import view.WindowManager;

public class modifyTeamController {

    @FXML
    private TableView<Team> tableViewTeam;

    @FXML
    private TableColumn<Team, String> teamName;

    @FXML
    private TableColumn<Team, E_Levels> teamLvl;

    @FXML
    private TableColumn<Team, Integer> teamStadiumID;

    @FXML
    private AnchorPane modifyTeam;

    @FXML
    private TableColumn<Team, Integer> teamID;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Team, Integer> teamValue;

    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Team
    	if(!SysData.getInstance().getTeams().isEmpty()) {
	    	teamID.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	teamValue.setCellValueFactory(new PropertyValueFactory<>("value"));
	    	teamLvl.setCellValueFactory(new PropertyValueFactory<>("level"));
	    	teamStadiumID.setCellValueFactory(new PropertyValueFactory<>("stadiumID"));
	        
	        // Display row data
	        ObservableList<Team> list = FXCollections.observableArrayList(SysData.getInstance().getTeams().values());
	        tableViewTeam.setItems(list);
	        MakeEditable();
    	}
    	else
    	{
    		System.out.println("No Teams");
    	}
    }// End of modifyTeam Constructor
    
    private void MakeEditable() {
    	// Make changes by double clicking the Cell and pressing enter after editing
    	// === On Cell edit commit (for Team Name column) ===
    	teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	teamName.setCellFactory(TextFieldTableCell.<Team> forTableColumn());
    	teamName.setOnEditCommit((CellEditEvent<Team, String> event) -> {
            TablePosition<Team, String> pos = event.getTablePosition();
 
            String newName = event.getNewValue();
            if(!newName.isEmpty() && newName!=null && newName.matches("[a-zA-Z]+")) {
		            int row = pos.getRow();
		            Team tm = event.getTableView().getItems().get(row);
		 
		            tm.setName(newName);
            } else
				try {
					throw new MissingInputException("Team's name");
				} catch (MissingInputException e) {
				}
        });
    	

    	// === On Cell edit commit (for Value column) ============
    	teamValue.setCellValueFactory(new PropertyValueFactory<>("value"));
    	teamValue.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

            @Override
            public String toString(Integer object) {
            	if(object!=null)
            		return object.toString();
            	return "";
            }

            @Override
            public Integer fromString(String string) {
            	try {
            		return Integer.parseInt(string);
            	} catch (NumberFormatException e) {
            		return 0;
            	}
            }

        }));
    	
    	teamValue.setOnEditCommit((CellEditEvent<Team, Integer> event) -> {
            TablePosition<Team, Integer> pos = event.getTablePosition();
 
            Integer newValue = (event.getNewValue());
            if(newValue!=null && newValue!=0) {
	            int row = pos.getRow();
	            Team tm = event.getTableView().getItems().get(row);
	            tm.setValue(newValue);
            }
            else
				try {
					throw new MissingInputException("Team's value");
				} catch (MissingInputException e) {
				}
        });
 
        // ==== Team Stadium ID (COMBO BOX) ===
    	 
        ObservableList<Integer> stadiumIDS = FXCollections.observableArrayList(SysData.getInstance().getStadiums().keySet());
 
        teamStadiumID.setCellValueFactory(new Callback<CellDataFeatures<Team, Integer>, ObservableValue<Integer>>() 
        {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<Team, Integer> param) {
                Team num = param.getValue();
                return new SimpleObjectProperty<Integer>(num.getStadium().getId());
            }
        });
 
        teamStadiumID.setCellFactory(ComboBoxTableCell.forTableColumn(stadiumIDS));
 
        teamStadiumID.setOnEditCommit((CellEditEvent<Team, Integer> event) -> {
        	
        	Team thisTeam = event.getRowValue();
        	Stadium oldStadium = SysData.getInstance().getStadiums().get(event.getOldValue());
        	System.out.println("Old stadium is " + oldStadium);
        	Stadium newStadium = SysData.getInstance().getStadiums().get(event.getNewValue());
        	System.out.println("New stadium is " + newStadium);
            oldStadium.removeTeam(thisTeam);
        	newStadium.addTeam(thisTeam);
            
            thisTeam.setStadium(newStadium);
            
        });
        
        // ==== Team Level (COMBO BOX) ===
        
        ObservableList<E_Levels> levelList = FXCollections.observableArrayList(E_Levels.values());
 
        teamLvl.setCellValueFactory(new Callback<CellDataFeatures<Team, E_Levels>, ObservableValue<E_Levels>>() {
 
            @Override
            public ObservableValue<E_Levels> call(CellDataFeatures<Team, E_Levels> param) {
                Team tm = param.getValue();

                E_Levels lvl = tm.getLevel();
                return new SimpleObjectProperty<E_Levels>(lvl);
            }
        });
 
        teamLvl.setCellFactory(ComboBoxTableCell.forTableColumn(levelList));
 
        teamLvl.setOnEditCommit((CellEditEvent<Team, E_Levels> event) -> {
            TablePosition<Team, E_Levels> pos = event.getTablePosition();
 
            E_Levels newPos = event.getNewValue();
 
            int row = pos.getRow();
            Team tm = event.getTableView().getItems().get(row);
 
            tm.setLevel(newPos);
        });
 
    }//End of Method MAKEEDITABLE
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
