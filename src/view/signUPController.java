package view;

import java.io.IOException;
import java.util.HashMap;

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
import utils.E_Periods;

public class signUPController {

    @FXML
    private AnchorPane register;

    @FXML
    private Button back;

    @FXML
    private TextField userF;

    @FXML
    private TextField pass;

    @FXML
    private ComboBox<String> typeList;

    @FXML
    private Button regButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)regButton.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/login.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void register(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Register to Java League:");
		alert.setHeaderText("");
    	String user=userF.getText();
    	String password=pass.getText();
    	if(SysData.getInstance().getCoach().containsKey(user)||SysData.getInstance().getRecep().containsKey(user)||SysData.getInstance().getCustomer().containsKey(user)) {
    		alert.setHeaderText("Username already exists!");
    		alert.setContentText("Please choose a different username.");
    		alert.show();
    	}
    	else {
    		switch(typeList.getSelectionModel().getSelectedItem()) {
        	case "Coach":
        		SysData.getInstance().getCoach().put(user, password);
        		break;
        	case "Receptionist":
        		SysData.getInstance().getRecep().put(user, password);
        		break;
        	case "Customer":
        		SysData.getInstance().getCustomer().put(user, password);
        		break;
    	}
    	
    		
    	}
    }
    public void initialize() {
    	typeList.getItems().add("Coach");
    	typeList.getItems().add("Customer");
    	typeList.getItems().add("Receptionist");
    }

}
