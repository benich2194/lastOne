package view;

import java.io.IOException;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.E_Cities;
import utils.E_Levels;

public class addTeamController {

	 @FXML
	    private AnchorPane addTeam;

	    @FXML
	    private Button back;

	    @FXML
	    private TextField teamId;

	    @FXML
	    private TextField teamName;

	    @FXML
	    private TextField teamValue;

	    @FXML
	    private TextField teamStadiumId;

	    @FXML
	    private ComboBox<E_Levels> teamLevel;

	    @FXML
	    private Button pushToAdd;

	    @FXML
	    void addTeam(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Add Stadium");
			alert.setHeaderText("");
			if(teamId.getText()==null||teamValue.getText()==null||teamStadiumId.getText()==null) {
				alert.setHeaderText("Unable to add team");
	    		alert.setContentText("Invalid input.");
	    		alert.show();
			}
			else {
				Integer id=Integer.parseInt(teamId.getText());
		    	String name=teamName.getText();
		    	Integer value=Integer.parseInt(teamValue.getText());
		    	Integer stadium=Integer.parseInt(teamStadiumId.getText());
				if(id==null||name==null||value==null||stadium==null) {
		    		alert.setHeaderText("Unable to add team");
		    		alert.setContentText("Invalid input.");
		    		alert.show();
		    	}
		    	else {
		    		if(SysData.getInstance().getTeams().containsKey(id)) {
			    		alert.setHeaderText("Unable to add team");
			    		alert.setContentText("Team with the same id already exists.");
			    		alert.show();
			    	}
			    	else {
			    		SysData.getInstance().addTeam(id, name, value, teamLevel.getSelectionModel().getSelectedItem(), stadium);
			    		if(SysData.getInstance().getTeams().containsKey(id)) {
			        		alert.setHeaderText("Added Team");
			        		alert.setContentText("Team was added succesfully");
			        		alert.show();
			        	}
			        	else {
			        		alert.setHeaderText("Unable to add Team");
			        		alert.setContentText("Team wasn't added to stadiums.");
			        		alert.show();
			        	}
			    	}
		    	}
			}
			
	    	
	    	
	    	
	    	
	    }
	    public void initialize() {
	    	teamLevel.getItems().addAll(E_Levels.values());
	    
	    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addTeam.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/teamMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
