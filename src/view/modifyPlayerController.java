package view;

import java.io.IOException;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Position;
import Controller.SysData;
import Model.Player;

public class modifyPlayerController {

    @FXML
    private TableView<Player> playerTableView;

    @FXML
    private AnchorPane modifyPlayer;

    @FXML
    private Button back;

        @FXML
        private TableColumn<Player, String> playerLN;

        @FXML
        private TableColumn<Player, Date> playerSW;

        @FXML
        private TableColumn<Player, String> playerFN;

        @FXML
        private TableColumn<Player, Long> playerVal;

        @FXML
        private TableColumn<Player, E_Levels> playerLvl;

        @FXML
        private TableColumn<Player, Date> playerBday;

        @FXML
        private TableColumn<Player, Boolean> playerRLK;

        @FXML
        private TableColumn<Player, E_Position> playerPosition;

        @FXML
        private TableColumn<Player, Integer> playerID;

        @FXML
        private TableColumn<Player, String> playerPhoneNum;

        @FXML
        private TableColumn<Player, String> playerStreet;

        @FXML
        private TableColumn<Player, E_Cities> playerCity;

        @FXML
        private TableColumn<Player, Integer> playerHouseNum;

        
    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Player.
        playerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        playerFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        playerLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        playerPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        playerRLK.setCellValueFactory(new PropertyValueFactory<>("isRightLegKicker"));
        playerBday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        playerLvl.setCellValueFactory(new PropertyValueFactory<>("playerLvl"));
        playerVal.setCellValueFactory(new PropertyValueFactory<>("value"));
        playerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        playerPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        playerHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        playerStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        playerSW.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        
        // Display row data
        ObservableList<Player> list = FXCollections.observableArrayList(SysData.getInstance().getPlayers().values());
        playerTableView.setItems(list);
        MakeEditable();
    }// End of modifyPlayer Constructor
        
    private void MakeEditable() {
    	// Make changes by double clicking the Cell and pressing enter after editing
    	// === On Cell edit commit (for FirstName column) ===
    	playerFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	playerFN.setCellFactory(TextFieldTableCell.<Player> forTableColumn());
    	playerFN.setOnEditCommit((CellEditEvent<Player, String> event) -> {
            TablePosition<Player, String> pos = event.getTablePosition();
 
            String newFirstName = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.setFirstName(newFirstName);
        });
    	
    	// === On Cell edit commit (for LastName column) ===
    	playerLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	playerLN.setCellFactory(TextFieldTableCell.<Player> forTableColumn());
    	playerLN.setOnEditCommit((CellEditEvent<Player, String> event) -> {
            TablePosition<Player, String> pos = event.getTablePosition();
 
            String newLastName = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.setLastName(newLastName);
        });
    	
    	// === On Cell edit commit (for Value column) ============
    	playerVal.setCellValueFactory(new PropertyValueFactory<>("value"));
    	playerVal.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Long>(){

            @Override
            public String toString(Long object) {
                return object.toString();
            }

            @Override
            public Long fromString(String string) {
                return Long.parseLong(string);
            }

        }));
    	
    	playerVal.setOnEditCommit((CellEditEvent<Player, Long> event) -> {
            TablePosition<Player, Long> pos = event.getTablePosition();
 
            Long newValue = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.setValue(newValue);
        });
 
        // ==== Player Position (COMBO BOX) ===
    	 
        ObservableList<E_Position> positionList = FXCollections.observableArrayList(E_Position.values());
 
        playerPosition.setCellValueFactory(new Callback<CellDataFeatures<Player, E_Position>, ObservableValue<E_Position>>() {
 
            @Override
            public ObservableValue<E_Position> call(CellDataFeatures<Player, E_Position> param) {
                Player pl = param.getValue();

                E_Position pos = pl.getPosition();
                return new SimpleObjectProperty<E_Position>(pos);
            }
        });
 
        playerPosition.setCellFactory(ComboBoxTableCell.forTableColumn(positionList));
 
        playerPosition.setOnEditCommit((CellEditEvent<Player, E_Position> event) -> {
            TablePosition<Player, E_Position> pos = event.getTablePosition();
 
            E_Position newPos = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.setPosition(newPos);
        });
        
        // ==== Player Level (COMBO BOX) ===
        
        ObservableList<E_Levels> levelList = FXCollections.observableArrayList(E_Levels.values());
 
        playerLvl.setCellValueFactory(new Callback<CellDataFeatures<Player, E_Levels>, ObservableValue<E_Levels>>() {
 
            @Override
            public ObservableValue<E_Levels> call(CellDataFeatures<Player, E_Levels> param) {
                Player pl = param.getValue();

                E_Levels lvl = pl.getLevel();
                return new SimpleObjectProperty<E_Levels>(lvl);
            }
        });
 
        playerLvl.setCellFactory(ComboBoxTableCell.forTableColumn(levelList));
 
        playerLvl.setOnEditCommit((CellEditEvent<Player, E_Levels> event) -> {
            TablePosition<Player, E_Levels> pos = event.getTablePosition();
 
            E_Levels newPos = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.setLevel(newPos);
        });
 
        // ==== RIGHT LEG KICKER? (CHECH BOX) ===
        playerRLK.setCellValueFactory(new Callback<CellDataFeatures<Player, Boolean>, ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Player, Boolean> param) {
                Player pl = param.getValue();
 
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(pl.isRightLegKicker());
 
                // When "RLK?" column change.
                booleanProp.addListener(new ChangeListener<Boolean>() {
 
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                            Boolean newValue) {
                        pl.setRightLegKicker(newValue);
                    }
                });
                return booleanProp;
            }
        });
 
        playerRLK.setCellFactory(new Callback<TableColumn<Player, Boolean>, //
        TableCell<Player, Boolean>>() {
            @Override
            public TableCell<Player, Boolean> call(TableColumn<Player, Boolean> p) {
                CheckBoxTableCell<Player, Boolean> cell = new CheckBoxTableCell<Player, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        
    	// === On Cell edit commit (for Phone Number column) ===
        playerPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Player , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        playerPhoneNum.setCellFactory(TextFieldTableCell.<Player> forTableColumn());
        playerPhoneNum.setOnEditCommit((CellEditEvent<Player, String> event) -> {
            TablePosition<Player, String> pos = event.getTablePosition();
 
            String newPhoneNumber = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
            String[] newPhoneArray = {newPhoneNumber};
            pl.getAddress().setPhoneNumber(newPhoneArray);
            
        });
    	
    	// === On Cell edit commit (for Player's Street column) ===
    	playerStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Player , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	playerStreet.setCellFactory(TextFieldTableCell.<Player> forTableColumn());
    	playerStreet.setOnEditCommit((CellEditEvent<Player, String> event) -> {
            TablePosition<Player, String> pos = event.getTablePosition();
 
            String newStreet = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.getAddress().setStreet(newStreet);
        });
    	
    	// === On Cell edit commit (for HouseNum column) ============
    	//TO DO
    	playerHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Player , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});
    	playerHouseNum.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
    	
    	playerHouseNum.setOnEditCommit((CellEditEvent<Player, Integer> event) -> {
            TablePosition<Player, Integer> pos = event.getTablePosition();
 
            Integer newValue = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
            pl.getAddress().setHouseNumber(newValue);
        });
        
        // ==== Stadium City (COMBO BOX) ===
        
        ObservableList<E_Cities> cityList = FXCollections.observableArrayList(E_Cities.values());
 
        playerCity.setCellValueFactory(new Callback<CellDataFeatures<Player, E_Cities>, ObservableValue<E_Cities>>() {
 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Player, E_Cities> param) {
            	Player pl = param.getValue();

            	E_Cities ct = pl.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
 
        playerCity.setCellFactory(ComboBoxTableCell.forTableColumn(cityList));
 
        playerCity.setOnEditCommit((CellEditEvent<Player, E_Cities> event) -> {
            TablePosition<Player, E_Cities> pos = event.getTablePosition();
 
            E_Cities newCity = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.getAddress().setCity(newCity);
        });
    }//End of Method MAKEEDITABLE
    
	@FXML
    void goBack(ActionEvent event)  throws IOException{
		//Go back to menu button Event
    	WindowManager.goBack();
    }

}