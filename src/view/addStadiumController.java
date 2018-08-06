package view;

import java.awt.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.omg.CORBA.INITIALIZE;

import Controller.SysData;
import Model.Stadium;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	    
	    private static Integer stadiumCounter = 1; 
	    

	    
	   
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    public void initialize() {
    	cityList.getItems().addAll(E_Cities.values());
    	id.setEditable(false);
    	id.setDisable(true);
    	id.setText(stadiumCounter.toString());
    }
    
    @FXML
    void addStadium(ActionEvent event) throws Exception {
    	Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Add Stadium");
	
		alert.setHeaderText("");
    	Integer ID=addStadiumController.stadiumCounter;
    	String NAME=name.getText();
    	Integer HOUSENUM=Integer.parseInt(houseNumber.getText());
    	Integer CAPACITY=Integer.parseInt(capacity.getText());
    	String PHONE=phone.getText();
    	E_Cities c=cityList.getSelectionModel().getSelectedItem();
    	String STREET=street.getText();
    	String[] phoneArr=new String[1];
    	phoneArr[0]=PHONE;
    	if(SysData.getInstance().getStadiums().containsKey(ID)) {
    		alert.setHeaderText("Unable to add stadium");
    		alert.setContentText("Stadium with the same ID already exists.");
    		alert.show();
    	}
    	else {
    		SysData.getInstance().addStadium(ID, NAME, CAPACITY, c, STREET, HOUSENUM, phoneArr);
        	if(SysData.getInstance().getStadiums().containsKey(ID)) {
        		alert.setHeaderText("Added Stadium");
        		alert.setContentText("Stadium was added succesfully");
        		alert.show();
        		stadiumCounter++;
        	}
        	else {
        		alert.setHeaderText("Unable to add stadium");
        		alert.setContentText("Stadium wasn't added to stadiums.");
        		alert.show();
        	}
    	}
    	
    }

}
