package view;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Stadium, Receptionist> stadiumReceps;

    @FXML
    private TableColumn<Stadium, Match> stadiumMatches;

    @FXML
    private TableColumn<Stadium, Team> stadiumTeams;
    
    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Stadium
    	stadiumID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	stadiumName.setCellValueFactory(new PropertyValueFactory<>("name"));
        stadiumCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        stadiumCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        stadiumPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        stadiumMatches.setCellValueFactory(new PropertyValueFactory<>("matches"));
        stadiumTeams.setCellValueFactory(new PropertyValueFactory<>("teams"));
        stadiumReceps.setCellValueFactory(new PropertyValueFactory<>("receptionists"));
        
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
        

        TableColumn<Stadium, Set<Team>> thirdCol = new TableColumn<>("Teams");
        PropertyValueFactory<Stadium, Set<Team>> thirdColFactory = new PropertyValueFactory<>("teams");
        thirdCol.setCellValueFactory(thirdColFactory);

        thirdCol.setCellFactory(col -> new TableCell<Stadium, Set<Team>>() {
            @Override
            public void updateItem(Set<Team> friends, boolean empty) {
                super.updateItem(friends, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(friends.stream().map(Team::getName)
                        .collect(Collectors.joining(", ")));
                }
            }
        });
        
        ObservableList<Receptionist> recepStadium = FXCollections.observableArrayList(SysData.getInstance().getReceptionists().values());
        stadiumReceps.setCellFactory(ComboBoxTableCell.forTableColumn(recepStadium));
        
        ObservableList<Match> matchesStadium = FXCollections.observableArrayList(SysData.getInstance().getMatchs().values());
        stadiumMatches.setCellFactory(ComboBoxTableCell.forTableColumn(matchesStadium));
        
        
    }// End of View stadium Constructor
        
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
