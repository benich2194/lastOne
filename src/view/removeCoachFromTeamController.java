package view;

import Controller.SysData;
import Model.Coach;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class removeCoachFromTeamController {

    @FXML
    private AnchorPane removeCoach;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Coach> coachList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void removeCoach(ActionEvent event) {
    	Coach c=coachList.getSelectionModel().getSelectedItem();
    	if(c!=null) {
    		Team t=c.getCurrentTeam();
    		c.getTeams().add(t);
    		c.setCurrentTeam(null);
    		t.setCoach(null);
    	}
    }
    public void initialize() {
    	if(SysData.getInstance().getCoachs()!=null) {
    		for(Coach c:SysData.getInstance().getCoachs().values()) {
    			if(c!=null) {
    				if(c.getCurrentTeam()!=null) {
    					coachList.getItems().add(c);
    				}
    			}
    		}
    	}
    }

}
