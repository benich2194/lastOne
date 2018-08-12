package view.queries.controller;

import java.util.ArrayList;
import java.util.Collection;
import Controller.SysData;
import Model.Customer;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class getCSOOTController {

    @FXML
    private AnchorPane addTeam;

    @FXML
    private Button back;

    @FXML
    private Button buttonGETList;
    
    @FXML
    private Label lbl;
    
    @FXML
    private Label lbl2;

    @FXML
    private ComboBox<Stadium> comboBoxChooseSS;

    @FXML
    private ComboBox<Stadium> comboBoxChooseFS;

    @FXML
    private ListView<Customer> listViewCustomers;

    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    public void initialize() {
    	comboBoxChooseFS.getItems().addAll(SysData.getInstance().getStadiums().values());
    	comboBoxChooseSS.setVisible(false);
    	listViewCustomers.setVisible(false);
    	buttonGETList.setVisible(false);
    	lbl2.setVisible(false);
    	lbl.setVisible(false);
    }
    
    @FXML
    void showComp(ActionEvent event) {
    	comboBoxChooseSS.getItems().clear();
    	Collection<Stadium> stSet = SysData.getInstance().getStadiums().values();
    	stSet.remove(comboBoxChooseFS.getSelectionModel().getSelectedItem());
    	comboBoxChooseSS.getItems().addAll(stSet);
    	comboBoxChooseSS.setVisible(true);
    	listViewCustomers.setVisible(true);
    	lbl.setVisible(true);
    	buttonGETList.setVisible(true);
    	lbl2.setVisible(true);
    }
    
    @FXML
    void goToShowList(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Get Customer List");
		alert.setHeaderText("");
		
		boolean isMyComboBoxEmptyFS = comboBoxChooseFS.getSelectionModel().isEmpty();
		boolean isMyComboBoxEmptySS = comboBoxChooseSS.getSelectionModel().isEmpty();
    	//Check if a stadium was selected
    	if(isMyComboBoxEmptyFS || isMyComboBoxEmptySS) {
    		alert.setHeaderText("No Stadium was selected");
    		alert.setContentText("Please select a stadium from the list.");
    		alert.show();
    	}
    	else {
	    	ArrayList<Customer> all = SysData.getInstance().getCustomersStadium1XORStadium2(comboBoxChooseFS.getSelectionModel().getSelectedItem().getId(), comboBoxChooseSS.getSelectionModel().getSelectedItem().getId());
	    	listViewCustomers.getItems().addAll(all);
    	}
    }

}
