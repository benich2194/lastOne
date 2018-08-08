package view;

import Controller.SysData;
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

public class removeMatchController {

    @FXML
    private AnchorPane removeMatch;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Match> matchList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void removeMatch(ActionEvent event) {
    	for(Team t:SysData.getInstance().getTeams().values()) {
    		if(t!=null) {
    			for(Match m:t.getMatches()) {
        			if(m!=null) {
        				if(m.getId()==matchList.getSelectionModel().getSelectedItem().getId()) {
        					t.getMatches().remove(m);
        				}
        			}
        		}
    		}
    		
    	}
    	for(Customer c:SysData.getInstance().getCustomers().values()) {
    		if(c!=null) {
    			for(Subscription s:c.getSubscriptions()) {
    				if(s!=null) {
    					for(Match m:s.getMatches()) {
    						if(m.getId()==matchList.getSelectionModel().getSelectedItem().getId()) {
    							s.getMatches().remove(m);
    						}
    					}
    				}
    			}
    		}
    	}
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Match");
		alert.setHeaderText("");
    	Match m=matchList.getSelectionModel().getSelectedItem();
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
    }
    public void initialize() {
		if(SysData.getInstance().getMatchs().size()>0) {
			matchList.getItems().addAll(SysData.getInstance().getMatchs().values());
		}
		
}

}
