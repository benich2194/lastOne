package view.queries.controller;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class getEWMTController {

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button back;

    @FXML
    private TextField textFieldEntity;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    public void initialize() {
    	Object obj = SysData.getInstance().getEntityWithMostTrophies();
    	if(obj==null) {
    		textFieldEntity.setText("None yet");
    	}
    	else
    		textFieldEntity.setText(obj.toString());
    		
    }
}
