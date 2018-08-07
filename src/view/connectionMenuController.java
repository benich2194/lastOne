package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class connectionMenuController {

    @FXML
    private AnchorPane connectionMenu;

    @FXML
    private Button coachTeam;

    @FXML
    private Button playerMenuButton;

    @FXML
    private Button playerFirst;

    @FXML
    private Button playerTeam;

    @FXML
    private Button cusSub;

    @FXML
    private Button addMatch;

    @FXML
    private Button cusMatch;

    @FXML
    void addCoachToTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow("coachToTeam");
    }

    @FXML
    void addCustomerToMatch(ActionEvent event) throws IOException {
    	WindowManager.openWindow("cusToMatch");
    }

    @FXML
    void addMatch(ActionEvent event) {
    	WindowManager.openWindow("addMatch");
    }

    @FXML
    void addPlayerToFirstTeamPlayers(ActionEvent event) throws IOException {
    	WindowManager.openWindow("playerToFirstTeam");
    }

    @FXML
    void addPlayerToTeam(ActionEvent event) throws IOException {
    	WindowManager.openWindow("playerToTeam");
    }

    @FXML
    void addReceptionistToStadium(ActionEvent event) throws IOException {
    	WindowManager.openWindow("recepToStadium");
    }

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) throws IOException {
    	WindowManager.openWindow("SubToCustomer");
    }

}
