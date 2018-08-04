package view;


import java.io.IOException;

import Controller.SysData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;


public class LoginController extends Main{

	public static Boolean override = true;

    @FXML
    private AnchorPane login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    private Button SignIn;
    
    @FXML
    private Button SignUp;

    @FXML // fx:id="exitImage"
    private ImageView exitImage; // Value injected by FXMLLoader

    @FXML
    void ExitProgram(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
        	System.exit(0);
    	}
    	else
    	{
    		alert = new Alert(AlertType.INFORMATION, "Good choice!");
        	alert.showAndWait();
    	}
    }
    
    @FXML
    void GoToMenu(ActionEvent event) throws Exception {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		alert.setHeaderText("");
    	String user=email.getText();
    	String pass=password.getText();
    	
    	if(override)
    	{
    		user = "admin";
    		pass = "admin";
    	}
    		if(user.equals("admin")&&pass.equals("admin")) {
    			
    			Stage stage=(Stage)login.getScene().getWindow();
    			stage.close();
    	    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/AdminMenuSidebar.fxml"));
    	    	System.out.println("Want to load css -> " + trophyMenuController.class.getResource("/view/application.css").toString());
    	    	
    	    	Stage primaryStage=new Stage();
    	    	Parent root=load.load();
    	    	Scene scene=new Scene(root);
    	    	
    			scene.getStylesheets()
    					.add(trophyMenuController.class.getResource("/view/application.css").toExternalForm());

    	    	primaryStage.setScene(scene);
    	    	primaryStage.show();
    		}
    		else if(SysData.getInstance().getCoach().containsKey(user)) {
    			if(SysData.getInstance().getCoach().get(user).equals(pass)) {
    				Stage stage=(Stage)login.getScene().getWindow();
        			stage.close();
        	    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/AdminMenuSidebar.fxml"));
        	    	System.out.println("Want to load css -> " + trophyMenuController.class.getResource("/view/application.css").toString());
        	    	
        	    	Stage primaryStage=new Stage();
        	    	Parent root=load.load();
        	    	Scene scene=new Scene(root);
        	    	
        			scene.getStylesheets()
        					.add(trophyMenuController.class.getResource("/view/application.css").toExternalForm());

        	    	primaryStage.setScene(scene);
        	    	primaryStage.show();
    				
    			}
    			else {
    				alert.setHeaderText("failed to logim.");
            		alert.setContentText("wrong username/password");
            		alert.show();
    			}
    			
    		}
    		else if(SysData.getInstance().getRecep().containsKey(user)) {
    			if(SysData.getInstance().getRecep().get(user).equals(pass)) {
    				Stage stage=(Stage)login.getScene().getWindow();
        			stage.close();
        	    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/AdminMenuSidebar.fxml"));
        	    	System.out.println("Want to load css -> " + trophyMenuController.class.getResource("/view/application.css").toString());
        	    	
        	    	Stage primaryStage=new Stage();
        	    	Parent root=load.load();
        	    	Scene scene=new Scene(root);
        	    	
        			scene.getStylesheets()
        					.add(trophyMenuController.class.getResource("/view/application.css").toExternalForm());

        	    	primaryStage.setScene(scene);
        	    	primaryStage.show();
    				
    			}
    			else {
    				alert.setHeaderText("failed to logim.");
            		alert.setContentText("wrong username/password");
            		alert.show();
    			}
    			
    		}
    		else if(SysData.getInstance().getCustomer().containsKey(user)) {
    			if(SysData.getInstance().getCustomer().get(user).equals(pass)) {
    				Stage stage=(Stage)login.getScene().getWindow();
        			stage.close();
        	    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/AdminMenuSidebar.fxml"));
        	    	System.out.println("Want to load css -> " + trophyMenuController.class.getResource("/view/application.css").toString());
        	    	
        	    	Stage primaryStage=new Stage();
        	    	Parent root=load.load();
        	    	Scene scene=new Scene(root);
        	    	
        			scene.getStylesheets()
        					.add(trophyMenuController.class.getResource("/view/application.css").toExternalForm());

        	    	primaryStage.setScene(scene);
        	    	primaryStage.show();
    				
    			}
    			else {
    				alert.setHeaderText("failed to logim.");
            		alert.setContentText("wrong username/password");
            		alert.show();
    			}
    			
    		}
    		else {
    			alert.setHeaderText("failed to logim.");
        		alert.setContentText("wrong username/password");
        		alert.show();
    		}
    	
    }
    @FXML
    void register(ActionEvent event) throws IOException {
    	Stage stage=(Stage)SignIn.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/signUP.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}


