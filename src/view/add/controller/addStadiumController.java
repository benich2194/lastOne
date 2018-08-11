package view.add.controller;

import java.io.IOException;
import Controller.SysData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import view.Main;
import view.WindowManager;

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
    	WindowManager.goBack();
    }
    
    public void initialize() {
    	cityList.getItems().addAll(E_Cities.values());
    	id.setEditable(false);
    	id.setDisable(true);

    	Integer idCurrent = SysData.getInstance().getStadiums().size()+1;
    	while(SysData.getInstance().getStadiums().containsKey(idCurrent))
    		idCurrent++;
    	id.setText(idCurrent.toString());
    }
    
    @FXML
    void addStadium(ActionEvent event) throws Exception {
    	Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Add Stadium");
	
		alert.setHeaderText("");
		//ID is size of the map + 1, if exists, it will keep adding 1
    	Integer id = SysData.getInstance().getStadiums().size()+1;
    	while(SysData.getInstance().getStadiums().containsKey(id))
    		id++;
    	String NAME=name.getText();
    	Integer HOUSENUM=Integer.parseInt(houseNumber.getText());
    	Integer CAPACITY=Integer.parseInt(capacity.getText());
    	String PHONE=phone.getText();
    	E_Cities c=cityList.getSelectionModel().getSelectedItem();
    	String STREET=street.getText();
    	String[] phoneArr=new String[1];
    	phoneArr[0]=PHONE;
    	if(SysData.getInstance().getStadiums().containsKey(id)) {
    		alert.setHeaderText("Unable to add stadium");
    		alert.setContentText("Stadium with the same ID already exists.");
    		alert.show();
    	}
    	else {
    		SysData.getInstance().addStadium(id, NAME, CAPACITY, c, STREET, HOUSENUM, phoneArr);
        	if(SysData.getInstance().getStadiums().containsKey(id)) {
        		alert.setHeaderText("Added Stadium");
        		alert.setContentText("Stadium was added succesfully");
        		alert.show();
        	}
        	else {
        		alert.setHeaderText("Unable to add stadium");
        		alert.setContentText("Stadium wasn't added to stadiums.");
        		alert.show();
        	}
    	}
    	
    }

}
