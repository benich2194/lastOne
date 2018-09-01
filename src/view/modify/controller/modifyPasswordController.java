package view.modify.controller;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Exceptions.MissingInputException;
import Exceptions.ObjectExistsException;
import Model.Coach;
import Model.Customer;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class modifyPasswordController {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane modifyPassword;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Object> userList;

    @FXML
    private PasswordField newPass;
    
    @FXML
    private Label labelSuccess;
    
    public void initialize() {
		if (SysData.getInstance().getCustomers() != null) {
			for (Customer c : SysData.getInstance().getCustomers().values()) {
				if (c != null)
					userList.getItems().add(c);
			}
		}
		if (SysData.getInstance().getReceptionists() != null) {
			for (Receptionist r : SysData.getInstance().getReceptionists().values()) {
				if (r != null)
					userList.getItems().add(r);
			}
		}
		if(SysData.getInstance().getCoachs()!=null) {
			for(Coach co:SysData.getInstance().getCoachs().values()) {
				if(co!=null)
					userList.getItems().add(co);
			}
		}
    }
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

    @FXML
    void goToChange(ActionEvent event) throws ListNotSelectedException, MissingInputException, ObjectExistsException{
    	try {
    		Object o=userList.getSelectionModel().getSelectedItem();
        	if(o==null)
        		throw new ListNotSelectedException("please choose a user first");
        	if(newPass.getText().isEmpty()) {
        		throw new MissingInputException("Please enter a password");
        	}
        	String password=newPass.getText();
        	if(o instanceof Receptionist) {
        		if(((Receptionist)o).getPassword().equals(password)){
        			throw new ObjectExistsException("Please enter a different password, this is already the password");
        		}
        		((Receptionist)o).setPassword(password);
        		labelSuccess.setText("Password changed succesfully to Receptionist "+((Receptionist)o).getId());
			}
			else if(o instanceof Customer) {
				if(((Customer)o).getPassword().equals(password)){
        			throw new ObjectExistsException("Please enter a different password, this is already the password");
        		}
				((Customer)o).setPassword(password);
        		labelSuccess.setText("Password changed succesfully to Customer "+((Customer)o).getId());
			}
			else {
				if(((Coach)o).getPassword().equals(password)){
        			throw new ObjectExistsException("Please enter a different password, this is already the password");
        		}
				((Coach)o).setPassword(password);
	    		labelSuccess.setText("Password changed succesfully to Coach "+((Coach)o).getId());
			}
        	
    	}catch(ListNotSelectedException e) {
    		
    	}catch(MissingInputException e) {
    		
    	}catch(ObjectExistsException e) {
    		
    	}
    	
    		
    }

}
