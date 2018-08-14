package view.recep.controller;

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
import view.WindowManager;

public class receptionistSubViewController {

    @FXML
    private AnchorPane modifyPlayer;

    @FXML
    private Button back;

    @FXML
    private ListView<Subscription> subListView;
	
    //Data members used to save logged-in Receptionist's info
	private Integer recepID = Integer.parseInt(SysData.getInstance().getUserRecep());
	private Receptionist rc = SysData.getInstance().getReceptionists().get(recepID);
	
    public void initialize() {

    	if(!rc.getSubscriptions().isEmpty())
    	{
    		ObservableList<Subscription> allSubs = FXCollections.observableArrayList(rc.getSubscriptions());
    		subListView.setItems(allSubs);
    	}
        
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}