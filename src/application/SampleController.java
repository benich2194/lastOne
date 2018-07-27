package application;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SampleController {

    @FXML
    private AnchorPane login;

    @FXML
    private TextField email;

    @FXML
    private Button SignIn;

    @FXML
    void GoToMenu(ActionEvent event) throws Exception {
    	login.getScene().getWindow().hide();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/application/menu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}


