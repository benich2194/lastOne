package view;

import java.io.IOException;

import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class playerToFirstTeamController {

    @FXML
    private AnchorPane playerToFirstTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Player> playerList;

    @FXML
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;

    @FXML
    void addPlayerToFirstTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Player To First Team Players");
		alert.setHeaderText("");
    	if(teamList.getSelectionModel().getSelectedItem()!=null&&playerList.getSelectionModel().getSelectedItem()!=null) {
    		if(SysData.getInstance().addPlayerToTeamFirstPlayers(playerList.getSelectionModel().getSelectedItem().getId(),teamList.getSelectionModel().getSelectedItem().getId())){
    			alert.setHeaderText("Added Player to First Team Players.");
        		alert.setContentText("Player was added to First Team Players successfully.");
        		alert.show();
    		}
    		else {
    			alert.setHeaderText("failed to add Player to First Team Players.");
        		alert.setContentText("unable to add Player to First Team Players, select a player and a team please.");
        		alert.show();
    		}
    	}
    	else {
    		alert.setHeaderText("failed to add Player to First Team Players.");
    		alert.setContentText("unable to add Player to First Team Players.");
    		alert.show();
    	}
    }
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getPlayers().values().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)connectThem.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/connectionMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
