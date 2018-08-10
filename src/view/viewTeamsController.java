package view;

import Controller.SysData;
import Model.Team;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import utils.E_Levels;

public class viewTeamsController {

    @FXML
    private AnchorPane modifyTeam;

    @FXML
    private Button back;

    @FXML
    private TableView<Team> tableViewTeam;

    @FXML
    private TableColumn<Team, Integer> teamID;

    @FXML
    private TableColumn<Team, String> teamName;

    @FXML
    private TableColumn<Team, Integer> teamValue;

    @FXML
    private TableColumn<Team, E_Levels> teamLvl;

    @FXML
    private TableColumn<Team, Model.Coach> coach;

    @FXML
    private TableColumn<Team, Integer> teamStadiumID;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Team
    	teamID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	teamValue.setCellValueFactory(new PropertyValueFactory<>("value"));
    	teamLvl.setCellValueFactory(new PropertyValueFactory<>("level"));
    	teamStadiumID.setCellValueFactory(new PropertyValueFactory<>("stadiumID"));
        teamStadiumID.setCellValueFactory(new Callback<CellDataFeatures<Team, Integer>, ObservableValue<Integer>>() 
        {
            @Override
            public ObservableValue<Integer> call(CellDataFeatures<Team, Integer> param) {
                Team num = param.getValue();
                return new SimpleObjectProperty<Integer>(num.getStadium().getId());
            }
        });
    	coach.setCellValueFactory(new PropertyValueFactory<>("coach"));
        
        // Display row data
        ObservableList<Team> list = FXCollections.observableArrayList(SysData.getInstance().getTeams().values());
        tableViewTeam.setItems(list);
    }// End of modifyTeam Constructor

}
