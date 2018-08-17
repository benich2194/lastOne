package view;

import java.io.IOException;
import Controller.SysData;
import Model.Match;
import Model.Receptionist;
import Model.Stadium;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import utils.E_Cities;

public class viewStadiumController {


    @FXML
    private TableView<Stadium> stadiumTableView;

    @FXML
    private AnchorPane modifyStadium;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Stadium, String> stadiumName;

    @FXML
    private TableColumn<Stadium, Integer> stadiumID;

    @FXML
    private TableColumn<Stadium, Integer> stadiumCapacity;

    @FXML
    private TableColumn<Stadium, String> stadiumPhoneNum;

    @FXML
    private TableColumn<Stadium, E_Cities> stadiumCity;
    
    @FXML
    private ListView<Receptionist> stadiumReceps;

    @FXML
    private ListView<Match> stadiumMatches;

    @FXML
    private ListView<Team> stadiumTeams;
    
    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Stadium
    	stadiumID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	stadiumName.setCellValueFactory(new PropertyValueFactory<>("name"));
        stadiumCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        stadiumCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        stadiumPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));

        
        // Display row data
        ObservableList<Stadium> list = FXCollections.observableArrayList(SysData.getInstance().getStadiums().values());
        stadiumTableView.setItems(list);
        
        stadiumPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stadium , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Stadium , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        
        stadiumCity.setCellValueFactory(new Callback<CellDataFeatures<Stadium, E_Cities>, ObservableValue<E_Cities>>() {
        	 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Stadium, E_Cities> param) {
            	Stadium st = param.getValue();

            	E_Cities ct = st.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
  

        stadiumTableView.setRowFactory(tv -> {
            TableRow<Stadium> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {

                	Stadium clickedRow = row.getItem();
                    showRowDetails(clickedRow);
                }
            });
            return row ;
        });
        
    }// End of View stadium Constructor
    
    private void showRowDetails(Stadium item) {
        ObservableList<Receptionist> recepStadium = FXCollections.observableArrayList(item.getReceptionists());
        ObservableList<Team> teamsStadium = FXCollections.observableArrayList(item.getTeams());
        ObservableList<Match> matchesStadium = FXCollections.observableArrayList(item.getMatches());
        
        stadiumReceps.setItems(recepStadium);
        stadiumMatches.setItems(matchesStadium);
        stadiumTeams.setItems(teamsStadium);
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
