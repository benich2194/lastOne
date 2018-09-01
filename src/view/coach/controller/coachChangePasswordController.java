package view.coach.controller;

import Controller.SysData;
import Exceptions.MissingInputException;
import Exceptions.ObjectExistsException;
import Model.Coach;
import Model.Customer;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class coachChangePasswordController   {
	/**
	 * fx fields
	 */
    @FXML
    private AnchorPane changePassword;

    @FXML
    private Button changeButton;

    @FXML
    private Label labelSuccess;

    @FXML
    private PasswordField newPass;

    /**
     * changes customer password
     * @param event submit button is pressed
     * @throws ObjectExistsException 
     */
    @FXML
    void goToChange(ActionEvent event) throws MissingInputException, ObjectExistsException{
    	try {
    		if(newPass.getText().isEmpty()) {
    			throw new MissingInputException("please enter a password");
    		}
    		Coach c=SysData.getInstance().getCoachs().get(Integer.parseInt(SysData.getInstance().getUserCoach()));
    		if(c.getPassword().equals(newPass.getText())) {
    			throw new ObjectExistsException("this is already the password, please enter a different password");
    		}
    		c.setPassword(newPass.getText());
    		labelSuccess.setText("Password changed succesfully");
    	}catch(MissingInputException e) {
    		
    	}catch(ObjectExistsException e) {
    		
    	}
    }

}
