package view.view.controller;

import Controller.SysData;
import Model.Coach;
import Model.Customer;
import Model.Employee;
import Model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.WindowManager;

public class viewPasswordsController {
	/**
	 * fx fields
	 */
	@FXML
	private AnchorPane viewPasswords;

	@FXML
	private Button back;

	@FXML
	private ComboBox<Object> userList;
	
    @FXML
    private TextField pass;

	/**
	 * goes back to previous screen
	 * 
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event) {
		WindowManager.goBack();
	}

	/**
	 * initializes list of users
	 */
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
		pass.setEditable(false);
	}
	/**
	 * this function shows passwords of selected user
	 * @param event user is selected from list
	 */
	@FXML
	void showPassword(ActionEvent event) {
		if(userList.getSelectionModel().getSelectedItem()!=null) {
			Object o=userList.getSelectionModel().getSelectedItem();
			String password=null;
			if(o instanceof Receptionist) {
				password=((Receptionist)o).getPassword();
			}
			else if(o instanceof Customer) {
				password=((Customer)o).getPassword();
			}
			else
				password=((Coach)o).getPassword();
			pass.setText(password);
		}
	}
}
