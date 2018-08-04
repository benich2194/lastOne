package view;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
        playerLvl.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        playerVal.setCellValueFactory(new PropertyValueFactory<>("value"));
        playerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        playerSW.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        
        // Display row data
        ObservableList<Player> list = FXCollections.observableArrayList(SysData.getInstance().getPlayers().values());
        playerTableView.setItems(list);
    }// End of modifyPlayer Constructor
        
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