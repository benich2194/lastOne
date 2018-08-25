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

public class AdminController {

	 @FXML
	    private AnchorPane adminMenu;

	    @FXML
	    private Button playerMenuButton;

	    @FXML
	    private Button teamMenuButton;

	    @FXML
	    private Button coachButton;

	    @FXML
	    private Button customerButton;

	    @FXML
	    private Button recepMenu;

	    @FXML
	    private Button connectionButton;

	    @FXML
	    private Button logout;

	    @FXML
	    private Button stadiumButton;

	    @FXML
	    private Button subscriptionButton;

	    @FXML
	    private Button trophyButton;

	    @FXML
	    private Button matchButton;

    @FXML
    void GoToCoachPage(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/coachModif.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/login.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void goToTeamPage(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/teamMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void goToPlayerMenu(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/playerMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void goToCustomerMenu(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/customerMenuAdmin.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void goToRecepMenu(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/receptionistMenuAdmin.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void goToConnectionMenu(ActionEvent event) {

    }
    
    @FXML
    void goToMatchMenu(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/matchMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    @FXML
    void goToStadiumMenu(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/stadiumMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void goToSubscriptionButton(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/subscriptionMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void goToTrophyMenu(ActionEvent event) throws IOException {
    	Stage stage=(Stage)adminMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/trophyMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
}

