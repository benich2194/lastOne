package view.remove.controller;

import java.io.IOException;
import java.util.ArrayList;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import Model.Player;
import Model.Stadium;
import Model.Subscription;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeTeamController {

	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removeTeam;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Team> teamList;

    @FXML
    private Button removeButton;
    
    @FXML
    private Label labelSuccess;
    /**
     * goes to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    
    @FXML
    void removeTeam(ActionEvent event) throws ListNotSelectedException {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Team");
		alert.setHeaderText("");
		try {
			if(teamList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			Team t=teamList.getSelectionModel().getSelectedItem();
			if(t.getCoach()!=null) {//removes coach from team
				t.getCoach().setCurrentTeam(null);
				
			}
			if(t.getPlayers()!=null) {//removes players
				for(Player p:t.getPlayers().keySet()){
					if(p!=null) {	
						p.setCurrentTeam(null);
					}
				}
			}
			ArrayList<Customer> removeCus=new ArrayList<Customer>();
			ArrayList<Subscription> removeSub=new ArrayList<Subscription>();
			if(SysData.getInstance().getCustomers()!=null) {
				for(Customer c:SysData.getInstance().getCustomers().values()) {//removes customers that its their favroite team
					if(c!=null) {
						if(c.getFavoriteTeam()!=null&&c.getFavoriteTeam().equals(t)) {
							if(c.getSubscriptions()!=null) {
								for(Subscription s:c.getSubscriptions()) {
									if(s!=null)
										removeSub.add(s);
								}
								removeCus.add(c);
							}
							
						}
						
					}
				}
			}
			for(Customer c:removeCus) {
				SysData.getInstance().removeCustomer(c);
			}
			for(Subscription s:removeSub) {
				SysData.getInstance().removeSubscription(s.getId());
			}
			if(t.getStadium()!=null) {//removes team from its stadium
				t.getStadium().removeTeam(t);
			}
			if(SysData.getInstance().getTeams().remove(t.getId())!=null) {
				labelSuccess.setText("Team "+t.getId()+" was removed succesfully!");
			}
			else {
				alert.setHeaderText("Failed to remove Team");
        		alert.setContentText("unable to remove Team ");
        		alert.show();
			}
			
	    	
		}catch(ListNotSelectedException e) {
			
		}
		teamList.getItems().removeAll(teamList.getItems());
		if(SysData.getInstance().getTeams().size()>0) {//refreshes list
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
    /**
     * initializes list
     */
    public void initialize() {
    	if(SysData.getInstance().getTeams().size()>0) {
    		teamList.getItems().addAll(SysData.getInstance().getTeams().values());
    	}
    }
}
