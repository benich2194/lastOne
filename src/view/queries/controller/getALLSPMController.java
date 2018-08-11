package view.queries.controller;

import java.io.IOException;
import java.util.ArrayList;

import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;
import javafx.scene.control.Alert.AlertType;
public class getALLSPMController {

    @FXML
    private ListView<Player> playerMakerList;

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button back;


    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	ArrayList<Player> all = SysData.getInstance().getAllSuperPlayerMakers();
    	if(all.isEmpty()) {
    		//playerMakerList.getItems().addAll(null);?????
    	}
    	else
    		playerMakerList.getItems().addAll(all);
    		
    }

    
}
