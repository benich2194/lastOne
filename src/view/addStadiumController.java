package view;

import java.awt.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.omg.CORBA.INITIALIZE;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.E_Cities;

public class addStadiumController extends Main {

	  @FXML
	    private AnchorPane addStadium;

	    @FXML
	    private Button back;

	    @FXML
	    private TextField id;

	    @FXML
	    private TextField name;
	    
	    @FXML
	    private ComboBox<E_Cities> cityList;
	    

	    @FXML
	    private TextField phone;

	    @FXML
	    private TextField street;

	    @FXML
	    private TextField capacity;

	    @FXML
	    private TextField houseNumber;

	    @FXML
	    private Button submit;
	    

	    
	   
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)addStadium.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/stadiumMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    public void initialize() {
    	System.out.println("Im here");
    	cityList.getItems().addAll(E_Cities.values());
    }
    
    @FXML
    void addStadium(ActionEvent event) {

    }

}
