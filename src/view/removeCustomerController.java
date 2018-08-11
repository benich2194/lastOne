package view;

import java.io.IOException;

import Controller.SysData;
import Exceptions.ListNotSelectedException;
import Model.Customer;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class removeCustomerController {
	/**
	 * fxml fields
	 */
	@FXML
	private AnchorPane removeCustomer;

	@FXML
	private Button back;

	@FXML
	private ComboBox<Customer> cusList;

	@FXML
	private Button removeButton;
	/**
	 * goes back to previous screen
	 * @param event back button is pressed
	 */
	@FXML
	void goBack(ActionEvent event){
		WindowManager.goBack();
	}
	/**
	 * removes customer from data base and its subscriptions
	 * @param event remove button is pressed
	 * @throws ListNotSelectedException
	 */
	@FXML
	void removeCustomer(ActionEvent event) throws ListNotSelectedException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Customer");
		alert.setHeaderText("");
		try {
			Customer c = cusList.getSelectionModel().getSelectedItem();
			if(c==null)
				throw new ListNotSelectedException();
			for (Subscription s : c.getSubscriptions()) {
				SysData.getInstance().removeSubscription(s.getId());
			}
			SysData.getInstance().getCustomers().remove(c.getId());
			if (!SysData.getInstance().getCustomers().containsKey(c.getId())) {
				alert.setHeaderText("Removed Customer");
				alert.setContentText("Removed Customer successfully.");
				alert.show();
			} else {
				alert.setHeaderText("Unable to remove Customer.");
				alert.setContentText("Cannot remove Customer from database.");
				alert.show();
			}
		}catch(ListNotSelectedException e) {
			
		}
		cusList.getItems().removeAll(cusList.getItems());
		if (SysData.getInstance().getCustomers().size() > 0) {
			cusList.getItems().addAll(SysData.getInstance().getCustomers().values());
		}
	}
	/**
	 * initializes customer list
	 */
	public void initialize() {
		if (SysData.getInstance().getCustomers().size() > 0) {
			cusList.getItems().addAll(SysData.getInstance().getCustomers().values());
		}

	}
}
