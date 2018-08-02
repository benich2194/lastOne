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

public class CoachModifController {

    @FXML
    private AnchorPane coachModif;

    @FXML
    private Button addCoach;
    
    @FXML
    private Button modCoach;
    
    @FXML
    private Button removeCoachButton;


    @FXML
    void goToAddCoach(ActionEvent event) throws IOException {
    	Stage stage=(Stage)coachModif.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/addCoach.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	/*try {
			AdminMenuController ad = new AdminMenuController();
			ad.loadUI("/view/addCoach");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    }
    
    @FXML
    void goToModifyCoach(ActionEvent event) throws IOException {
    	Stage stage=(Stage)coachModif.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/modifyCoach.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void goToRemoveCoach(ActionEvent event) throws IOException {
    	Stage stage=(Stage)coachModif.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/removeCoach.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
}
