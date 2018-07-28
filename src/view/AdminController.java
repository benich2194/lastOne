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
    private Button coachButton;
   
    
    @FXML
    private Button logout;
    
    @FXML
    private Button teamMenuButton;
    
    @FXML
    private Button playerMenuButton;

    @FXML
    private Button customerButton;

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
}

