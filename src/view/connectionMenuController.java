package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private Button logout;

    @FXML
    private Button cusMatch;

    @FXML
    void addCoachToTeam(ActionEvent event) {
    	
    }

    @FXML
    void addCustomerToMatch(ActionEvent event) {

    }

    @FXML
    void addMatch(ActionEvent event) {

    }

    @FXML
    void addPlayerToFirstTeamPlayers(ActionEvent event) {

    }

    @FXML
    void addPlayerToTeam(ActionEvent event) {

    }

    @FXML
    void addReceptionistToStadium(ActionEvent event) {

    }

    @FXML
    void addSubscriptionToCustomer(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)cusMatch.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/adminMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
