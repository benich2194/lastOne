package view;

import java.io.IOException;

import Controller.SysData;
import Model.Coach;
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
import utils.E_Levels;

public class removeCoachController {

    @FXML
    private AnchorPane removeCoach;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Coach> coachList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)removeCoach.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/coachModif.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void removeCoach(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Coach");
		alert.setHeaderText("");
    	Coach c=coachList.getSelectionModel().getSelectedItem();
    	SysData.getInstance().getCoachs().remove(c.getId());
    	if(!SysData.getInstance().getCoachs().containsKey(c.getId())) {
    		alert.setHeaderText("Removed coach");
    		alert.setContentText("Removed coach successfully.");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Unable to remove coach.");
    		alert.setContentText("Cannot remove coach from database.");
    		alert.show();
    	}
    }
    public void initialize() {
    		coachList.getItems().addAll(SysData.getInstance().getCoachs().values());
    }
}
