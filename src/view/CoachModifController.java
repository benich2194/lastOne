package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;

public class CoachModifController {
	/**
	 * coach choose menu fields
	 */
    @FXML
    private AnchorPane coachModif;

    @FXML
    private Button addCoach;
    
    @FXML
    private Button modCoach;
    
    @FXML
    private Button removeCoachButton;
    
    @FXML
    private Button viewCoachButton;
    
    @FXML
    private Button removeFromTeam;
    
   /**
    * go to add coach window
    * @param event add coach button is pressed
    */
    @FXML
    void goToAddCoach(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.ADD_COACH);
    }
    /**
     * go to modify coach window
     * @param event modify coach button is pressed
     */
    @FXML
    void goToModifyCoach(ActionEvent event){
    	WindowManager.openWindow("modifyCoach");
    }
    /**
     * go to remove coach window
     * @param event remove coach button is pressed
     */
    @FXML
    void goToRemoveCoach(ActionEvent event){
    	WindowManager.openWindow("removeCoach");
    }
    /**
     * go to view coach window
     * @param event view coach button is pressed
     */
    @FXML
    void goToViewCoach(ActionEvent event) {
    	WindowManager.openWindow("viewCoach");
    }
    @FXML
    void goToRemoveCoachFromTeam(ActionEvent event) {
    	WindowManager.openWindow("removeCoachFromTeam");
    }
}
