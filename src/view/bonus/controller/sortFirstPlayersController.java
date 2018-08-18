package view.bonus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import view.WindowManager;

public class sortFirstPlayersController {

    @FXML
    private ComboBox<Team> cbTeam;

    @FXML
    private Label lblFP;

    @FXML
    private ListView<Player> listplayers;

    @FXML
    private Label lblPL;

    @FXML
    private AnchorPane removeCoach;

    @FXML
    private Button back;

    @FXML
    private ImageView bgimg;

    @FXML
    private Label lblInst;

    @FXML
    private GridPane gridpane;

    /**
     * initializes team list, hides all other components from display
     */
    public void initialize() {
    	cbTeam.getItems().clear();

    	if(!SysData.getInstance().getTeams().isEmpty())
    		cbTeam.getItems().addAll(SysData.getInstance().getTeams().values());
    	
    	listplayers.setVisible(false);
    	lblInst.setVisible(false);
    	lblPL.setVisible(false);
    	lblFP.setVisible(false);
    	bgimg.setVisible(false);
    	lblInst.setVisible(false);
    	gridpane.setVisible(false);
    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException{
    	WindowManager.goBack();
    }

    @FXML
    void droppedPlayerInGrid(ActionEvent event) {

    }

    @FXML
    void showPlayerList(ActionEvent event) {

    	Team chosen = cbTeam.getSelectionModel().getSelectedItem();
    	//Make a list of first team players on selected team
    	ArrayList<Player> plys = new ArrayList<Player>();
    	for(Map.Entry<Player,Boolean> entry: chosen.getPlayers().entrySet()) {
    		if(entry.getValue()) //If he's value is true (then hes a first team player) add him to 'plys'
    			plys.add(entry.getKey());
    	}
    	
    	//Check that there are first team players
    	if(plys.isEmpty())
    		lblInst.setText("There are no First Team Players on Chosen Team.");
    	else {
    		listplayers.getItems().addAll(plys);
    		
	    	listplayers.setVisible(true);
	    	lblInst.setVisible(true);
	    	lblPL.setVisible(true);
	    	lblFP.setVisible(true);
	    	bgimg.setVisible(true);
	    	lblInst.setVisible(true);
	    	gridpane.setVisible(true);
    	}
    }

    @FXML
    void dragPlayer(ActionEvent event) {

    }

}