package view.remove.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import Model.Stadium;
import Model.Subscription;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeMatchController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane removeMatch;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Match> matchList;

    @FXML
    private Button removeButton;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void removeMatch(ActionEvent event) throws ListNotSelectedException{
    	try {
    		if(matchList.getSelectionModel().getSelectedItem()==null) {
        		throw new ListNotSelectedException();
        	}
    		Match m=matchList.getSelectionModel().getSelectedItem();
        	for(Team t:SysData.getInstance().getTeams().values()) {//removes match from teams
        		if(t!=null) {
        			t.removeMatch(m);
        		}
        		
        	}
        	for(Customer c:SysData.getInstance().getCustomers().values()) {//remove match from customers
        		if(c!=null) {
        			c.removeMatch(m);
        		}
        	}
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Remove Match");
    		alert.setHeaderText("");
        	SysData.getInstance().getMatchs().remove(m.getId());
        	if(SysData.getInstance().getMatchs().containsKey(m.getId())) {
        		alert.setHeaderText("Match wasn't removed");
        		alert.setContentText("Match wasn't removed successfully.");
        		alert.show();
        	}
        	else {
        		alert.setHeaderText("Match removed");
        		alert.setContentText("Match was removed successfully.");
        		alert.show();
        	}
    	}catch(ListNotSelectedException e) {
    		
    	}
    	matchList.getItems().removeAll(matchList.getItems());//refreshes list
    	if(SysData.getInstance().getMatchs().size()>0) {
			matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
		}
    }
    /**
     * initializes match list
     */
    public void initialize() {
		if(SysData.getInstance().getMatchs().size()>0) {
			matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
		}
		
}

}