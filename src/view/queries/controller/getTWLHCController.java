package view.queries.controller;

import java.io.IOException;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class getTWLHCController {

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button back;

    @FXML
    private TextField textFieldMF;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTeamWithLargestHomeCrowd()==null) {
    		textFieldMF.setText("None at the moment");
    	}
    	else
    		textFieldMF.setText(SysData.getInstance().getTeamWithLargestHomeCrowd().toString());
    }

    
}
