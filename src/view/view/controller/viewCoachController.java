package view.view.controller;

import java.util.Date;
import Controller.SysData;
import Model.Coach;
import Model.Team;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import utils.E_Cities;
import utils.E_Levels;
import view.WindowManager;

public class viewCoachController {
	/**
	 * fxml fields of this class
	 */
    @FXML
    private AnchorPane viewCoaches;

    @FXML
    private Button back;

    @FXML
    private TableView<Coach> coachTableView;

    @FXML
    private TableColumn<Coach, Integer> coachID;

    @FXML
    private TableColumn<Coach, Date> coachBirth;

    @FXML
    private TableColumn<Coach, E_Cities> coachCity;

    @FXML
    private TableColumn<Coach, String> coachStreet;

    @FXML
    private TableColumn<Coach, Integer> coachHouseNum;

    @FXML
    private TableColumn<Coach, String> coachPhoneNum;

    @FXML
    private TableColumn<Coach, Date> coachStartWork;

    @FXML
    private TableColumn<Coach, Team> coachCurrentTeam;

    @FXML
    private TableColumn<Coach, E_Levels> coachLevel;

    @FXML
    private ListView<Team> coachPastTeams;
    
    @FXML
    private TableColumn<Coach, String> coachFirstName;

    @FXML
    private TableColumn<Coach, String> coachLastName;
    /**
     * goes back to previous screen	
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    public void initialize() {
    	// Defines how to fill data for each cell.
        // Get value from property of Coach.
        coachID.setCellValueFactory(new PropertyValueFactory<>("id"));
        coachFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        coachLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        coachBirth.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        coachLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        coachCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        coachPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        coachHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        coachStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        coachStartWork.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        coachCurrentTeam.setCellValueFactory(new PropertyValueFactory<>("currentTeam"));
        
        // Display row data
        ObservableList<Coach> list = FXCollections.observableArrayList(SysData.getInstance().getCoachs().values());
        coachTableView.setItems(list);
        
        coachPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Coach , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        
        coachStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Coach , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	
      	coachHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Coach , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});

    	
        coachCity.setCellValueFactory(new Callback<CellDataFeatures<Coach, E_Cities>, ObservableValue<E_Cities>>() {
        	 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Coach, E_Cities> param) {
            	Coach cc = param.getValue();

            	E_Cities ct = cc.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
        
      	coachCurrentTeam.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , Team>, ObservableValue<Team>>() {

    	    @Override
    	    public ObservableValue<Team> call(TableColumn.CellDataFeatures<Coach , Team> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getCurrentTeam());

    	    }
    	});
      	
      	coachTableView.setRowFactory(tv -> {
             TableRow<Coach> row = new TableRow<>();
             row.setOnMouseClicked(event -> {
                 if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                      && event.getClickCount() == 2) {

                	 Coach clickedRow = row.getItem();
                     showRowDetails(clickedRow);
                 }
             });
             return row ;
         });
    }
    
    private void showRowDetails(Coach item) {
      
        ObservableList<Team> teamsCoachPast = FXCollections.observableArrayList(item.getTeams());
        coachPastTeams.setItems(teamsCoachPast);
        
    }

}
