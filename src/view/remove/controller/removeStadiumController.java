package view.remove.controller;

import java.io.IOException;
import java.util.ArrayList;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Match;
import Model.Player;
import Model.Receptionist;
import Model.Stadium;
import Model.Subscription;
import Model.Team;
import Model.Trophy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class removeStadiumController {
	/**
	 * fxml fields
	 */
    @FXML
    private AnchorPane removeStadium;

    @FXML
    private Button back;
    
    @FXML
    private Label success;
    
    @FXML
    private ComboBox<Stadium> stadiumList;

    @FXML
    private Button removeButton;
    
    @FXML
    private Label labelSuccess;
    /**
     * goes back to previous screen
     * @param event back button is pressed
     */
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }
    /**
     * removes stadium and disconnects its connection to every entity
     * @param event remove button is pressed
     * @throws ListNotSelectedException
     */
    @FXML
    void removeStadium(ActionEvent event) throws ListNotSelectedException{
    	Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Remove Stadium");
	
		alert.setHeaderText("");
		try {
			if(stadiumList.getSelectionModel().getSelectedItem()==null){
    			alert.setHeaderText("Unable to remove stadium");
    			alert.setContentText("No stadium to remove");
    			alert.show();
    			throw new ListNotSelectedException();
    	}
    	else {
    		Stadium s=stadiumList.getSelectionModel().getSelectedItem();
    		if(s.getReceptionists()!=null) {
    			for(Receptionist r:s.getReceptionists()) {
    				if(r!=null) {
    					r.setWorkingStadium(null);
    				}
    			}
    		}
    		for(Team t:s.getTeams()) {//remove matches that happened in this stadium in team and players
    			if(t!=null) {
    				for(Player p:t.getPlayers().keySet()) {
    					if(p!=null) {
    						for(Match m:p.getMatches()) {
    							if(m!=null&&m.getHomeTeam()!=null&&m.getHomeTeam().getStadium().equals(s)) {
    								p.removeMatch(m);
    								t.removeMatch(m);
    								SysData.getInstance().getMatchs().remove(m.getId());
    							}
    						}
    					}
    				}
    			}
    		}
    		ArrayList<Match> matchDel=new ArrayList<Match>();
    		for(Match m:SysData.getInstance().getMatchs().values()) {
    			if(m!=null&&m.getHomeTeam()!=null&&m.getHomeTeam().getStadium()!=null&&m.getHomeTeam().getStadium().equals(s)) {
    				matchDel.add(m);
    			}
    		}
    		for(Match m:matchDel) {
    			SysData.getInstance().getMatchs().remove(m.getId());
    		}
			for(Team t:s.getTeams()) {
				if(t!=null) {
					ArrayList<Match> matchDell=new ArrayList<Match>();
					for(Match m:SysData.getInstance().getMatchs().values()) {//delete matches with this team
						if(m!=null&&m.getHomeTeam().equals(t)||m.getAwayTeam().equals(t)) {
							matchDel.add(m);
						}
					}
					for(Match m:matchDell) {
						if(m!=null) {
							SysData.getInstance().getMatchs().remove(m.getId());
						}
					}
				}
			}
    		if(s.getMatches()!=null) {//remove matches from team that belongs to stadium
    			for(Match m:s.getMatches()) {
    				SysData.getInstance().getMatchs().remove(m.getId());
    				for(Team t:SysData.getInstance().getTeams().values()) {
    					if(t!=null) {
    						t.removeMatch(m);
    					}
    				}
    			}
    		}
    		for(Receptionist r:s.getReceptionists()) {//remove receptionist and subs sold by her
    			if(r!=null) {
    				for(Subscription sub:r.getSubscriptions()) {
    					if(sub!=null) {
    						SysData.getInstance().removeSubscription(sub.getId());
    					}
    				}
    				s.removeReceptionist(r);
    			}
    		}
    		for(Match m:s.getMatches()) {
    			if(m!=null) {
    				for(Customer c:SysData.getInstance().getCustomers().values()) {
    					if(c!=null) {
    						c.removeMatch(m);
    					}
    				}
    			}
    		}
    		ArrayList<Trophy> troDel=new ArrayList<Trophy>();
    		for(Trophy tr:SysData.getInstance().getTrophies()) {
    			if(tr!=null&&tr.getOwner() instanceof Stadium&&tr.getOwner().equals(s)) {
    				troDel.add(tr);
    			}
    		}
    		for(Trophy tr:troDel) {
    			SysData.getInstance().getTrophies().remove(tr);
    		}
    		for(Team t:s.getTeams()) {
    			if(t!=null) {
    				SysData.getInstance().getTeams().remove(t.getId());
    			}
    		}
        	if(s!=null) {
        		SysData.getInstance().getStadiums().remove(s.getId());
        		if(!SysData.getInstance().getStadiums().containsKey(s.getId())) {
        			labelSuccess.setText("stadium "+s.getId()+" was removed succesfully!");
        		}
        		else {
        			alert.setHeaderText("Unable to remove stadium");
        			alert.setContentText("No stadium to remove");
        			alert.show();
        		}
        	}
    	}
			stadiumList.getItems().removeAll(stadiumList.getItems());
			if(SysData.getInstance().getStadiums().size()>0) {
	    		stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
	    	}
		}catch(ListNotSelectedException e) {
			
		}
    	    
    }
    /**
     * initializes list of stadiums to remvoe
     */
    public void initialize() {
    	if(SysData.getInstance().getStadiums().size()>0) {
    		stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
    	}
    	success=new Label();
    }
}
