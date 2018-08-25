package view.view.controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Position;
import view.WindowManager;
import Controller.SysData;
import Model.Player;
import Model.Team;

public class viewPlayerController {

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
        private TableColumn<Player, E_Cities> playerCity;

        @FXML
        private TableColumn<Player, Team> playerCT;

        
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
        playerCT.setCellValueFactory(new PropertyValueFactory<>("currentTeam"));
        playerSW.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        
        // Display row data
        ObservableList<Player> list = FXCollections.observableArrayList(SysData.getInstance().getPlayers().values());
        playerTableView.setItems(list);
        
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
    	
        playerPosition.setCellValueFactory(new Callback<CellDataFeatures<Player, E_Position>, ObservableValue<E_Position>>() {
        	 
            @Override
            public ObservableValue<E_Position> call(CellDataFeatures<Player, E_Position> param) {
                Player pl = param.getValue();

                E_Position pos = pl.getPosition();
                return new SimpleObjectProperty<E_Position>(pos);
            }
        });
        
        playerLvl.setCellValueFactory(new Callback<CellDataFeatures<Player, E_Levels>, ObservableValue<E_Levels>>() {
        	 
            @Override
            public ObservableValue<E_Levels> call(CellDataFeatures<Player, E_Levels> param) {
                Player pl = param.getValue();

                E_Levels lvl = pl.getLevel();
                return new SimpleObjectProperty<E_Levels>(lvl);
            }
        });
        
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
        
        playerPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Player , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        
        playerCity.setCellValueFactory(new Callback<CellDataFeatures<Player, E_Cities>, ObservableValue<E_Cities>>() {
        	 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Player, E_Cities> param) {
            	Player pl = param.getValue();

            	E_Cities ct = pl.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
        
      	playerCT.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Player , Team>, ObservableValue<Team>>() {

    	    @Override
    	    public ObservableValue<Team> call(TableColumn.CellDataFeatures<Player , Team> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getCurrentTeam());

    	    }
    	});
    }// End of View Player Constructor

    
	@FXML
    void goBack(ActionEvent event)  throws IOException{
		//Go back to menu button Event
    	WindowManager.goBack();
    }

}