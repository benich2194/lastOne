package view;

import Controller.SysData;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class viewTeamsController {

    @FXML
    private AnchorPane modifyTeam;

    @FXML
    private Button back;

    @FXML
    private ListView<Team> listViewTeams;

	
    public void initialize() {
    	if(!SysData.getInstance().getTeams().isEmpty())
    	{
    		ObservableList<Team> allTeams = FXCollections.observableArrayList(SysData.getInstance().getTeams().values());
    		listViewTeams.setItems(allTeams);
    	}
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
}
