package view;

import java.io.IOException;

import Controller.SysData;
import Model.Address;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addMatchController {

    @FXML
    private AnchorPane addMatch;

    @FXML
    private Button back;

    @FXML
    private TextField matchId;

    @FXML
    private TextField Extra;

    @FXML
    private ComboBox<Team> Home;

    @FXML
    private ComboBox<Team> Away;

    @FXML
    private TextField homeS;

    @FXML
    private TextField awayS;

    @FXML
    private Button addButton;
    

    @FXML
    private DatePicker matchDate;

    @FXML
    void addMatch(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Add Match");
		alert.setHeaderText("");
    	Integer id=Integer.parseInt(matchId.getText());
    	Integer extra=Integer.parseInt(Extra.getText());
    	java.sql.Date date=java.sql.Date.valueOf(matchDate.getValue());
    	if(SysData.getInstance().getMatchs().containsKey(id)) {
    		alert.setHeaderText("Unable to add Match.");
    		alert.setContentText("Match already exists.");
    		alert.show();
    	}
    	else {
    		SysData.getInstance().addMatch(id, date, extra, Home.getSelectionModel().getSelectedItem().getId(), Away.getSelectionModel().getSelectedItem().getId(), Integer.parseInt(homeS.getText()), Integer.parseInt(awayS.getText()));
	    	if(SysData.getInstance().getMatchs().containsKey(id)) {
	    		alert.setHeaderText("Added Match");
	    		alert.setContentText("Match added successfully.");
	    		alert.show();
	    	}
	    	else {
	    		alert.setHeaderText("Unable to add Match.");
	    		alert.setContentText("Match wasn't added.");
	    		alert.show();
	    	}
    	}
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addButton.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/AdminMenuSidebar.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    public void initialize() {
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		Home.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    	if(SysData.getInstance().getTeams().values().size()>0) {
    		Away.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    
    }
}
