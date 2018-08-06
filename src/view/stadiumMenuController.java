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

public class stadiumMenuController {

    @FXML
    private AnchorPane stadiumMenu;

    @FXML
    private Button addStadium;

    @FXML
    private Button removeStadium;

    @FXML
    private Button modStadium;

    @FXML
    private Button back;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)stadiumMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/adminMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToAddStadium(ActionEvent event) throws IOException {
    	WindowManager.openWindow("addStadium");
    }
    @FXML
	    void goToModifyStadium(ActionEvent event) throws IOException {
    	Stage stage=(Stage)stadiumMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/modifyStadium.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

    @FXML
    void goToRemoveStadium(ActionEvent event) throws IOException {
    	Stage stage=(Stage)stadiumMenu.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/removeStadium.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}
