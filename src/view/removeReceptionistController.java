package view;

import java.io.IOException;
import Controller.SysData;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class removeReceptionistController {

    @FXML
    private AnchorPane removeReceptionist;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Receptionist> recepList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }
    
    @FXML
    void removeReceptionist(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Receptionist");
		alert.setHeaderText("");
    	Receptionist c=recepList.getSelectionModel().getSelectedItem();
    	SysData.getInstance().getReceptionists().remove(c.getId());
    	if(!SysData.getInstance().getReceptionists().containsKey(c.getId())) {
    		alert.setHeaderText("Removed Receptionist");
    		alert.setContentText("Removed Receptionist successfully.");
    		alert.show();
    	}
    	else {
    		alert.setHeaderText("Unable to remove Receptionist.");
    		alert.setContentText("Cannot remove Receptionist from database.");
    		alert.show();
    	}
    }
    public void initialize() {
		if(SysData.getInstance().getReceptionists().size()>0) {
			recepList.getItems().addAll(SysData.getInstance().getReceptionists().values());
		}
		
}
}
