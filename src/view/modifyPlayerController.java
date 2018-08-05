package view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Position;
import Controller.SysData;
import Model.Player;
import Model.Address;
import Model.Employee;

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
        private TableColumn<Player, Address> playerAddress;

        @FXML
        private TableColumn<Player, Date> playerBday;

        @FXML
        private TableColumn<Player, Boolean> playerRLK;

        @FXML
        private TableColumn<Player, E_Position> playerPosition;

        @FXML
        private TableColumn<Player, Integer> playerID;
        
        
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
        playerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
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
    	
    	// === On Cell edit commit (for Birthdate column) =====================
    	playerBday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    	playerBday.setCellFactory(column -> {
    	    TableCell<Player, Date> cell = new TableCell<Player, Date>() {
    	        private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    	        @Override
    	        protected void updateItem(Date item, boolean empty) {
    	            super.updateItem(item, empty);
    	            if(empty) {
    	                setText(null);
    	            }
    	            else {
    	                setText(format.format(item));
    	            }
    	        }
    	    };

    	    return cell;
    	});
    	playerBday.setOnEditCommit((CellEditEvent<Player, Date> event) -> {
            TablePosition<Player, Date> pos = event.getTablePosition();
 
            Date newDate = event.getNewValue();
 
            int row = pos.getRow();
            Player pl = event.getTableView().getItems().get(row);
 
            pl.setBirthdate(newDate);
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
    }//End of Method MAKEEDITABLE
    
	@FXML
    void goBack(ActionEvent event)  throws IOException{
		//Go back to menu button Event
    	Stage stage=(Stage)modifyPlayer.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/playerMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}