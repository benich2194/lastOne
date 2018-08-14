package view;

import java.util.ArrayList;

import Controller.SysData;
import Model.Receptionist;
import Model.Subscription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class viewAllSubsController {

    @FXML
    private AnchorPane modifyPlayer;

    @FXML
    private Button back;

    @FXML
    private ListView<Subscription> subListView;
	
    public void initialize() {
    	//Check if logged in coach is connected to a team
    	ArrayList<Subscription> ss = new ArrayList<Subscription>();
    	for(Receptionist r: SysData.getInstance().getReceptionists().values())
    		for(Subscription s: r.getSubscriptions())
    			ss.add(s);
    	
    	if(!ss.isEmpty())
    	{
    		ObservableList<Subscription> allSubs = FXCollections.observableArrayList(ss);
    		subListView.setItems(allSubs);
    	}
        
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}