package view.queries.controller;

import java.io.IOException;

import Controller.SysData;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import view.WindowManager;
import javafx.scene.control.Alert.AlertType;

public class getMACController {

    @FXML
    private TextField textFieldMAC;

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button buttonGETMAC;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Stadium> comboBoxChooseStadium;
    

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTeams().size()>0) {
    		comboBoxChooseStadium.getItems().addAll(SysData.getInstance().getStadiums().values());
    	}
    }
    

    @FXML
    void goToShowMAC(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Get Most Active City");
		alert.setHeaderText("");
		
    	boolean isMyComboBoxEmpty = comboBoxChooseStadium.getSelectionModel().isEmpty();
    	//Check if anything was selected
    	if(isMyComboBoxEmpty) {
    		alert.setHeaderText("No stadium was Selected");
    		alert.setContentText("Please select a stadium from the list.");
    		alert.show();
    	}
    	else {
    		Stadium st = comboBoxChooseStadium.getSelectionModel().getSelectedItem();
    		E_Cities cc = SysData.getInstance().getTheMostActiveCity(st.getId());
    		if(cc==null) // No city was found
    			textFieldMAC.setText("This stadium doesn't have a most active city.");
    		else
    			textFieldMAC.setText(cc.toString());
    	}
    }

    
}
