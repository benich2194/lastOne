package view.view.controller;

import java.util.ArrayList;

import Controller.SysData;
import Model.Match;
import Model.Receptionist;
import Model.Stadium;
import Model.Subscription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class viewAllMatchesController {

    @FXML
    private AnchorPane modifyPlayer;

    @FXML
    private Button back;

    @FXML
    private ListView<Match> subListView;
	
    public void initialize() {

    	ArrayList<Match> mm = new ArrayList<Match>();
    	for(Stadium s: SysData.getInstance().getStadiums().values())
    		for(Match ma: s.getMatches())
    			mm.add(ma);
    	
    	if(!mm.isEmpty())
    	{
    		ObservableList<Match> allMatches = FXCollections.observableArrayList(mm);
    		subListView.setItems(allMatches);
    	}
        
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}