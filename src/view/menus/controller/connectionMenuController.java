package view.menus.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;


public class connectionMenuController {
	/**
	 * fxml fields
	 */

    @FXML
    private Button playerFirst;

    @FXML
    private Button coachTeam;

    @FXML
    private Button cusMatch;

    @FXML
    private Button playerTeam;

    @FXML
    private Button playerMenuButton;

    @FXML
    private AnchorPane connectionMenu;
    
    @FXML
    void addCoachToTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.CONNECT_COACHTOTEAM);
    }

    @FXML
    void addCustomerToMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.CONNECT_CUSTOMERTOMATCH);
    }

    @FXML
    void addPlayerToFirstTeamPlayers(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.CONNECT_PLAYERTOFIRSTT);
    }

    @FXML
    void addPlayerToTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.CONNECT_PLAYERTOTEAM);
    }

    @FXML
    void addReceptionistToStadium(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.CONNECT_RECEPTOSTADIUM);
    }

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow(NameToWindow.CONNECT_SUBTOCUSTOMER);
    }


}
