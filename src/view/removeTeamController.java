package view;

import java.io.IOException;

import Controller.SysData;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.E_Cities;

public class removeTeamController {


    @FXML
    private AnchorPane removeTeam;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Team> teamList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)removeTeam.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/teamMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void removeTeam(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Team");
		alert.setHeaderText("");
    	Team t=teamList.getSelectionModel().getSelectedItem();
    	SysData.getInstance().getTeams().remove(t.getId());
    	if(SysData.getInstance().getTeams().containsKey(t.getId())) {
    		alert.setHeaderText("Team wasn't removed");
    		alert.setContentText("Team wasn't removed successfully.");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Team removed");
    		alert.setContentText("Team was removed successfully.");
    		alert.show();
    	}
    }
    public void initialize() {
    	if(SysData.getInstance().getTeams().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
}
