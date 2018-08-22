package view.modify.controller;

import java.io.IOException;
import java.util.ArrayList;
import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Model.Receptionist;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import utils.E_Periods;
import view.WindowManager;

public class modifySubscriptionController {

    @FXML
    private AnchorPane modifyTrophy;

    @FXML
    private Label lblMessage;

	@FXML
    private ComboBox<Subscription> subList;

    @FXML
    private Button back;

    @FXML
    private DatePicker datepicker;

    @FXML
    private Button buttonModify;

    @FXML
    private ComboBox<E_Periods> periodpicker;

    
    /**
     * initializes subscription list
     */
    public void initialize() {
    	subList.getItems().clear();
    	periodpicker.getItems().clear();
    	periodpicker.getItems().addAll(E_Periods.values());
    	ArrayList<Subscription> subsAll = new ArrayList<Subscription>();

    	if(SysData.getInstance().getReceptionists().size()>0) {
    		for(Receptionist r: SysData.getInstance().getReceptionists().values())
    			for(Subscription s: r.getSubscriptions())
    				if(s!=null)
    					subsAll.add(s);
    	}
    	if(!subsAll.isEmpty())
    		subList.getItems().addAll(subsAll);
    	buttonModify.setVisible(false);
    	datepicker.setVisible(false);
    	periodpicker.setVisible(false);
    }
    
    @FXML
    void showDP(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Modify Subscription");
		alert.setHeaderText("");
    	buttonModify.setVisible(true);
    	datepicker.setVisible(true);
    	periodpicker.setVisible(true);
    }
    
    @FXML
    void hidelbl(ActionEvent event) {
    	lblMessage.setText("");
    }
    
    @FXML
    void modifyPressed(ActionEvent event) {
		try {
			if(subList.getSelectionModel().getSelectedItem()==null) {
				throw new ListNotSelectedException();
			}
			else {
					if(datepicker.getValue()==null && periodpicker.getSelectionModel().isEmpty())
						throw new MissingInputException("Date And/Or Period");
					else {
						if (datepicker.getValue()!=null && periodpicker.getSelectionModel().isEmpty()){
						java.sql.Date datepick = java.sql.Date.valueOf(datepicker.getValue());
						subList.getSelectionModel().getSelectedItem().setStartDate(datepick);
						lblMessage.setText("Successfully modified Subscriptions Date!");
						initialize();
						}
						else if(datepicker.getValue()!=null && !periodpicker.getSelectionModel().isEmpty()){
							java.sql.Date datepick = java.sql.Date.valueOf(datepicker.getValue());
							E_Periods p = periodpicker.getSelectionModel().getSelectedItem();
							subList.getSelectionModel().getSelectedItem().setStartDate(datepick);
							subList.getSelectionModel().getSelectedItem().setPeriod(p);
							lblMessage.setText("Successfully modified Subscription Date & Period!");
							initialize();
						}
						else if(datepicker.getValue()==null && !periodpicker.getSelectionModel().isEmpty()){
							E_Periods p = periodpicker.getSelectionModel().getSelectedItem();
							subList.getSelectionModel().getSelectedItem().setPeriod(p);
							lblMessage.setText("Successfully modified Subscription Period!");
							initialize();
						}
					} //else
			}
		}catch(ListNotSelectedException e) {
			
		}
		catch(MissingInputException e) {
					
		}
    	
    } //End of modifyPressed Method

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
