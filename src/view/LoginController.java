package view;



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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class LoginController extends Main{


    @FXML
    private AnchorPane login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Button SignIn;

    @FXML
    void GoToMenu(ActionEvent event) throws Exception {
    	String user=email.getText();
    	String pass=password.getText();
    	if(sysData.getAdmins().containsKey(user)) {
    		if(sysData.getAdmins().get(user).equals(pass)) {
    			login.getScene().getWindow().hide();
    	    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/adminMenu.fxml"));
    	    	Stage primaryStage=new Stage();
    	    	Parent root=load.load();
    	    	Scene scene=new Scene(root);
    	    	primaryStage.setScene(scene);
    	    	primaryStage.show();
    		}
    	}
    	else {
    		login.getScene().getWindow().hide();
    		login.setVisible(false);
    		System.out.println("wrong password");
    	}
    	
    }

}


