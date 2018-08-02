package view;

import java.io.IOException;

import Controller.SysData;
import Model.Coach;
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
import utils.E_Levels;

public class coachToTeamController {

    @FXML
    private AnchorPane coachToTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Coach> coachList;

    @FXML
    private ListView<Team> teamList;

    @FXML
    private Button connectThem;
    
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getCoachs().values().size()>0) {
    		coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    	}
    
    }
    
    @FXML
    void addCoachToTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Coach To Team");
		alert.setHeaderText("");
    	if(teamList.getSelectionModel().getSelectedItem()!=null&&coachList.getSelectionModel().getSelectedItem()!=null) {
    		if(SysData.getInstance().addCoachToTeam(coachList.getSelectionModel().getSelectedItem().getId(), teamList.getSelectionModel().getSelectedItem().getId())) {
    			alert.setHeaderText("Added coach to team.");
        		alert.setContentText("Coach was added to team successfully.");
        		alert.show();
    		}
    		else {
    			alert.setHeaderText("failed to add coach to team.");
        		alert.setContentText("unable to add coach to team.");
        		alert.show();
    		}
    	}
    	else {
    		alert.setHeaderText("failed to add coach to team.");
    		alert.setContentText("unable to add coach to team.");
    		alert.show();
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
