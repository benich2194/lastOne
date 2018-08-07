package view;

import java.io.IOException;

import Controller.SysData;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class removeStadiumController {

    @FXML
    private AnchorPane removeStadium;

    @FXML
    private Button back;
    
    @FXML
    private Label success;
    
    @FXML
    private ComboBox<Stadium> stadiumList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    @FXML
    void removeStadium(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("Remove Stadium");
	
		alert.setHeaderText("");
	
    	if(stadiumList.getSelectionModel()==null){
    			alert.setHeaderText("Unable to remove stadium");
    			alert.setContentText("No stadium to remove");
    			
    			alert.show();
    	}
    	else {
    		Stadium s=stadiumList.getSelectionModel().getSelectedItem();
        	if(s!=null) {
        		if(SysData.getInstance().getStadiums().remove(s.getId()) != null) {
        			alert.setHeaderText("Stadium added");
        			alert.setContentText("Stadium added successfully.");
        		
        			alert.show();
        		}
        		else {
        			alert.setHeaderText("Unable to remove stadium");
        			alert.setContentText("No stadium to remove");
        			alert.show();;
        		}
        	}
        	else {
    		
        		alert.setHeaderText("Unable to remove stadium");
    			alert.setContentText("No stadium was selected");
    			alert.show();
        	}
        	
    	}
    	
        
    }
    public void initialize() {
    	stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
    	success=new Label();
    }
}
