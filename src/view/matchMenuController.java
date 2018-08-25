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

public class matchMenuController {

    @FXML
    private AnchorPane matchMenu;

    @FXML
    private Button addMatch;

    @FXML
    private Button removeMatch;

    @FXML
    private Button modMatch;

    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)matchMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/adminMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToAddMatch(ActionEvent event) throws IOException {
    	Stage stage=(Stage)matchMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/addMatch.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToModifyMatch(ActionEvent event) throws IOException {
    	Stage stage=(Stage)matchMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/modifyMatch.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToRemoveMatch(ActionEvent event) throws IOException {
    	Stage stage=(Stage)matchMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/removeMatch.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
