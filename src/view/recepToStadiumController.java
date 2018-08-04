package view;

import java.io.IOException;

import Controller.SysData;
import Model.Receptionist;
import Model.Stadium;
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

public class recepToStadiumController {

    @FXML
    private AnchorPane recepToStadium;

    @FXML
    private Button back;

    @FXML
    private ListView<Receptionist> recepList;

    @FXML
    private ListView<Stadium> stadiumList;

    @FXML
    private Button connectThem;

    @FXML
    void addRecepToStadium(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Receptionist To stadium");
		alert.setHeaderText("");
    	if(recepList.getSelectionModel().getSelectedItem()!=null&&stadiumList.getSelectionModel().getSelectedItem()!=null) {
    		if(SysData.getInstance().addReceptionistToStadium(recepList.getSelectionModel().getSelectedItem().getId(), stadiumList.getSelectionModel().getSelectedItem().getId())) {
    			alert.setHeaderText("Added Receptionist to stadium.");
        		alert.setContentText("Receptionist was added to stadium successfully.");
        		alert.show();
    		}
    		else {
    			alert.setHeaderText("failed to add Receptionist to team.");
        		alert.setContentText("unable to add Receptionist to team, select a Receptionist and a stadium please.");
        		alert.show();
    		}
    	}
    	else {
    		alert.setHeaderText("failed to add Receptionist to stadium.");
    		alert.setContentText("unable to add Receptionist to stadium.");
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
    public void initialize() {
    	if(SysData.getInstance().getReceptionists().values().size()>0) {
    		recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
    	}
    	if(SysData.getInstance().getStadiums().values().size()>0) {
    		stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
    	}
    
    }

}
