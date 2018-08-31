package view.bonus.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import Controller.SysData;
import Model.Player;
import Model.Team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.NameToWindow;
import view.WindowManager;

public class sortFirstPlayersController{

	//-------------------------Data Members-----------------------------------------------
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
    private Button saveButton;

    @FXML
    private Button clear;

    @FXML
    private ImageView bgimg;

    @FXML
    private Label lblInst;
    
    @FXML
    private TextArea instText;

    @FXML
    private Label instLbl;
    
    @FXML
    private GridPane gridpane;

    private ImageView source; 

    private Player pl;
    
    //Player and his position
    private HashMap<Player, Integer> onChosenTeam;
    
  //Make a list of first team players on selected team
	private ArrayList<Player> plys;
    
    /**
     * initializes team list, hides all other components from display
     */
    public void initialize() {    
        pl=null;
    	cbTeam.getItems().clear();	
    	
    	//Saves players on current team that were placed on the board & their place: row|column
    	onChosenTeam = new HashMap<Player, Integer>();   	
    	
    	plys = new ArrayList<Player>();
    	
    	if(!SysData.getInstance().getTeams().isEmpty())
    		cbTeam.getItems().addAll(SysData.getInstance().getTeams().values());
    	
    	listplayers.setVisible(false);
    	lblInst.setVisible(false);
    	lblPL.setVisible(false);
    	lblFP.setVisible(false);
    	bgimg.setVisible(false);
    	lblInst.setVisible(false);
    	gridpane.setVisible(false);
    	instLbl.setVisible(false);
    	instText.setVisible(false);
    	saveButton.setVisible(false);
    	
    	source = new ImageView("images/splayer.png");
        source.setFitWidth(45);
        source.setFitHeight(45);   
        
        int numCols = 10;
        int numRows = 7;

        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPane(i, j);
            }
        }	
    }
    
    private void loadGrid(Team chosen, HashMap<Player, Integer> copy) {
		// Loads a team who's players positioning was saved on the field
    	source = new ImageView("images/splayer.png");
        source.setFitWidth(45);
        source.setFitHeight(45);  
        source.setUserData(pl);       
        Text tx = new Text();
        int row, column;
        StackPane st;
        
        //Literate over players that need to be manually added to the grid
    	for(Map.Entry<Player,Integer> hm: copy.entrySet()) {
    		if(hm!=null) {
    			Player pl = hm.getKey();
    		    column = hm.getValue()/10;
    		    row = hm.getValue()%10;
    			tx.setText(Integer.toString(pl.getId()));
    			tx.setFont(Font.font(null, 9.7));

	           	 Node result = null;
	           	 ObservableList<Node> childrens = gridpane.getChildren();
	           	 for(Node node : childrens) {
	           	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	           	            result = node;
	           	            break;
	           	        }
	           	  }
	           	st = (StackPane) result;  	           	
    		    st.getChildren().clear();
    		    st.getChildren().add(tx);
    			st.getChildren().add(source);
    			st.setAlignment(Pos.BOTTOM_RIGHT);
				String place = Integer.toString(column) + Integer.toString(row);
				onChosenTeam.put(pl, Integer.parseInt(place));
				
    		} //if
    	}	//for
    }


	private void addPane(int colIndex, int rowIndex) {
    	StackPane pane = new StackPane();
    	
        pane.setOnMousePressed(e -> {
        	// System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
            //Text field that displays the player's id
        	Text tx = new Text();
            
            if(pl!=null) { // If a player was chosen
                
            	source = new ImageView("images/splayer.png");
                source.setFitWidth(45);
                source.setFitHeight(45);  
                source.setUserData(pl);
                
               //If the player is on the grid
             if(onChosenTeam.containsKey(pl)) {
                	int column = onChosenTeam.get(pl)/10;
                	int row = onChosenTeam.get(pl)%10;
                	
                	 Node result = null;
                	 ObservableList<Node> childrens = gridpane.getChildren();
                	 for(Node node : childrens) {
                	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                	            result = node;
                	            break;
                	        }
                	  }
                	  if(result!=null) {
                		  if(colIndex == column && rowIndex == row)
                			  result.setVisible(true);
                		  else {
                		    StackPane st = (StackPane) result;  
                		    st.getChildren().clear();
                		    onChosenTeam.remove(pl);
                		  }
                	}
                	 
                } // end of if player is on the grid
               
			//Defines how the players can be sorted on the grid, according to their position	
            switch(pl.getPosition()) {
            	case GOAlKEEPER:  if(colIndex==0 && rowIndex==3) {
									tx.setText(Integer.toString(pl.getId()));
									tx.setFont(Font.font(null, 9.7));
									pane.getChildren().clear();
									pane.getChildren().add(tx);
									pane.getChildren().add(source);
									pane.setAlignment(Pos.BOTTOM_RIGHT);
									String place = Integer.toString(colIndex) + Integer.toString(rowIndex);
									onChosenTeam.put(pl, Integer.parseInt(place));
            						}
            		break;
            		
            	case ATTACKER: if(colIndex>=5 && colIndex<=9) {
									tx.setText(Integer.toString(pl.getId()));
									tx.setFont(Font.font(null, 9.7));
									pane.getChildren().clear();
									pane.getChildren().add(tx);
									pane.getChildren().add(source);
									pane.setAlignment(Pos.BOTTOM_RIGHT);
									String place = Integer.toString(colIndex) + Integer.toString(rowIndex);
									onChosenTeam.put(pl, Integer.parseInt(place));
            					}				
            		break;
            	case DEFENDER: if(colIndex>=0 && colIndex<=3) {
									tx.setText(Integer.toString(pl.getId()));
									tx.setFont(Font.font(null, 9.7));
									pane.getChildren().clear();
									pane.getChildren().add(tx);
									pane.getChildren().add(source);
									pane.setAlignment(Pos.BOTTOM_RIGHT);
									String place = Integer.toString(colIndex) + Integer.toString(rowIndex);
									onChosenTeam.put(pl, Integer.parseInt(place));
            					}
            		break;
            	case MIDFIELDER: if(colIndex>=4 && colIndex<=6) {
									tx.setText(Integer.toString(pl.getId()));
									tx.setFont(Font.font(null, 9.7));
									pane.getChildren().clear();
									pane.getChildren().add(tx);
									pane.getChildren().add(source);
									pane.setAlignment(Pos.BOTTOM_RIGHT);
									String place = Integer.toString(colIndex) + Integer.toString(rowIndex);
									onChosenTeam.put(pl, Integer.parseInt(place));
            					}
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
    void clearField(ActionEvent event) throws IOException{
    	WindowManager.openWindow(NameToWindow.SORT_FTPADMIN);
    	lblInst.setVisible(false);
    }

    @FXML
    void saveField(ActionEvent event) throws IOException{
    	//Check if there are players on the field
    	if(!onChosenTeam.isEmpty()) {
    		//Chosen team in combobox
    		Team chosen = cbTeam.getSelectionModel().getSelectedItem();
    		
    		//Saves player positioning on field for the specific team
    		if(chosen!=null && onChosenTeam!=null) {
    				if(SysData.getInstance().getTeamGridsSaved()==null) {
    					HashMap<Team,HashMap<Player,Integer>> hm=new HashMap<Team,HashMap<Player,Integer>>();
    					hm.put(chosen, onChosenTeam);
    					SysData.getInstance().setTeamGridsSaved(hm);
    					
    				}
    				else {
    					SysData.getInstance().getTeamGridsSaved().put(chosen, onChosenTeam);
    				}
    				lblInst.setText("Field was saved for Team ID "+chosen.getId());
    			
    		}
    	}
    	else
    	{
    		lblInst.setText("No players on field. Please place the players before saving.");
    	}
    }
    
    @FXML
    void showPlayerList(ActionEvent event) {
    	listplayers.getItems().clear();
    	Team chosen = cbTeam.getSelectionModel().getSelectedItem();
    	for(Map.Entry<Player,Boolean> entry: chosen.getPlayers().entrySet()) {
    		if(entry.getValue()) //If he's value is true (then hes a first team player) add him to 'plys'
    			plys.add(entry.getKey());
    	}
    	
        //If the chosen team has been saved in the past, load the grid
    	if(SysData.getInstance().getTeamGridsSaved()!=null) {
	        if(SysData.getInstance().getTeamGridsSaved().containsKey(chosen)) {
	        	//First check that all the previously placed players still exist
	        	
	        	//Make a copy of the teams inner hashmap, which holds players and positions on the grid
	        	LinkedHashMap<Player, Integer> copy = new LinkedHashMap<Player,Integer>();
	        	for(HashMap<Player,Integer> hm:SysData.getInstance().getTeamGridsSaved().values()) {
	        		if(hm!=null) {
	        			for(Player p:hm.keySet()) {
	        				if(p!=null) {
	        					copy.put(p, hm.get(p));
	        				}
	        			}
	        		}
	        	}
	        	
	        	ArrayList<Player> playerToRemove=new ArrayList<Player>();
	        	for(Player p:copy.keySet()) {
	        		if(!plys.contains(p)) {
	        			playerToRemove.add(p);
	        		}
	        	}
	        	for(Player p:playerToRemove) {
	        		copy.remove(p);
	        	}
	    		
	    		while(!copy.isEmpty()) {
	    			loadGrid(chosen, copy);
	    			//Remove the last player in copy
	    			Player ent = (Player) copy.keySet().toArray()[copy.size() -1];
	    			copy.remove(ent);
	    		}
	    		
	    		SysData.getInstance().getTeamGridsSaved().put(chosen, onChosenTeam); // updated grid to be loaded

        	}
        }
        
    	cbTeam.setVisible(false); //Don't display team combobox
       
    	
    	//Check that there are first team players
    	if(plys.isEmpty()) {
    		lblInst.setText("There are no First Team Players on Chosen Team.");
    		lblInst.setVisible(true);
    	}
    	else {
    		listplayers.getItems().addAll(plys);
    		
	    	listplayers.setVisible(true);
	    	lblInst.setVisible(true);
	    	lblPL.setVisible(true);
	    	lblFP.setVisible(true);
	    	bgimg.setVisible(true);
	    	lblInst.setVisible(true);
	    	gridpane.setVisible(true);
	    	instLbl.setVisible(true);
	    	instText.setVisible(true);
	    	saveButton.setVisible(true);
	    	
    	}
    	
    }

    @FXML
    void chosenPlayer(MouseEvent event) {
    	pl = listplayers.getSelectionModel().getSelectedItem();	

    }


}