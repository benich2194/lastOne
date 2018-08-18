package view.view.controller;

import Controller.SysData;
import Model.Trophy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class viewTrophiesController {

    @SuppressWarnings("rawtypes")
	@FXML
    private ListView<Trophy> listTrophies;

    @FXML
    private AnchorPane modifyTeam;

    @FXML
    private Button back;

	@SuppressWarnings("rawtypes")
	public void initialize() {
        ObservableList<Trophy> all = FXCollections.observableArrayList(SysData.getInstance().getTrophies());
        listTrophies.setItems(all);
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}
