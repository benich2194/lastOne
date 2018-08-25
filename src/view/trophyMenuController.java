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

public class trophyMenuController {

    @FXML
    private AnchorPane trophyMenu;

    @FXML
    private Button addTrophy;

    @FXML
    private Button removeTrophy;

    @FXML
    private Button modTrophy;

    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addTrophy.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/adminMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToAddTro(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addTrophy.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/addTrophy.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToModifyTro(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addTrophy.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/modifyTrophy.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToRemoveTrophy(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addTrophy.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/removeTrophy.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
