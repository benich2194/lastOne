package view;

import java.io.IOException;

import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;

public class getSPMController {

    @FXML
    private TextField textFieldSP;

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button buttonGETSP;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Team> comboBoxChooseTeam;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	if(SysData.getInstance().getTeams().size()>0) {
    		comboBoxChooseTeam.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
    

    @FXML
    void goToShowSP(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Get Super Player Maker");
		alert.setHeaderText("");
		
    	boolean isMyComboBoxEmpty = comboBoxChooseTeam.getSelectionModel().isEmpty();
    	//Check if anything was selected
    	if(isMyComboBoxEmpty) {
    		alert.setHeaderText("No team was Selected");
    		alert.setContentText("Please select a team from the list.");
    		alert.show();
    	}
    	else {
    		Team t=comboBoxChooseTeam.getSelectionModel().getSelectedItem();
    		Player p = SysData.getInstance().getSuperPlayerMaker(t.getId());
    		if(p==null) // No super player was found
    			textFieldSP.setText("No Super Player Maker was found on this team.");
    		else
    			textFieldSP.setText(p.toString());
    	}
    }

    
}
