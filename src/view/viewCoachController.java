package view;

import java.util.Date;

import Controller.SysData;
import Model.Coach;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import utils.E_Levels;

public class viewCoachController {

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
    private TableColumn<Coach, Team[]> coachPastTeams;
    @FXML
    private TableColumn<Coach, String> coachFirstName;

    @FXML
    private TableColumn<Coach, String> coachLastName;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    public void initialize() {
    	// Defines how to fill data for each cell.
        // Get value from property of Player.
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
    }

}
