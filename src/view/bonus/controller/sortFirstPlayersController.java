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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

    private ImageView source; 
    
    final GridPane target = gridpane;
    
    Player pl;
    
    /**
     * initializes team list, hides all other components from display
     */
    public void initialize() {
    	//First attempt at drag and drop     
        pl=null;
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
    	
    	source = new ImageView("images/splayer.png");
        source.setFitWidth(45);
        source.setFitHeight(45);   
        
        int numCols = 5;
        int numRows = 7;

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }
    }
    
    private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        
        pane.setOnMouseClicked(e -> {
            System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
            if(pl!=null) { // If a player was chosen
            	source = new ImageView("images/splayer.png");
                source.setFitWidth(45);
                source.setFitHeight(45);  
                source.setUserData(pl);
//                if(gridpane.getChildren().contains(pl))
//                	gridpane.getChildren().remove(pl);
            	switch(pl.getPosition()) {
            	case GOAlKEEPER:  if(colIndex==4)
            							gridpane.add(source, colIndex, 3);
            		break;
            	case ATTACKER: if(colIndex==0 || colIndex==1)
            						gridpane.add(source, colIndex, rowIndex);
            		break;
            	case DEFENDER: if(colIndex==3)
            						gridpane.add(source, colIndex, rowIndex);
            		break;
            	case MIDFIELDER: if(colIndex==2 || colIndex==1)
            						gridpane.add(source, colIndex, rowIndex);
            		break;
            		default: System.out.println("Cannot place player in selected spot.");
            		break;
            	}
            }
        });
        gridpane.add(pane, colIndex, rowIndex);
    }

    
    @FXML
    void goBack(ActionEvent event) throws IOException{
    	WindowManager.goBack();
    }


    @FXML
    void showPlayerList(ActionEvent event) {
    	listplayers.getItems().clear();
    	
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
    void chosenPlayer(MouseEvent event) {
    	pl = listplayers.getSelectionModel().getSelectedItem();
    }


}