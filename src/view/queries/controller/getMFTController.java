package view.queries.controller;

import java.io.IOException;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class getMFTController {

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
    	if(SysData.getInstance().getTheMostFavoredTeam()==null) {
    		textFieldMF.setText("No Favoured Team at the moment");
    	}
    	else
    		textFieldMF.setText(SysData.getInstance().getTheMostFavoredTeam().toString());
    }

    
}
