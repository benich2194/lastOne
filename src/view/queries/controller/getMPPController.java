package view.queries.controller;

import java.io.IOException;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class getMPPController {

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button back;

    @FXML
    private TextField textFieldMP;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTheMostPopularPosition()==null) {
    		textFieldMP.setText("No Popular Position exists at the moment");
    	}
    	else
    		textFieldMP.setText(SysData.getInstance().getTheMostPopularPosition().toString());
    }

    
}
