package view;

import Controller.SysData;
import Model.Customer;
import Model.Subscription;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class receptionistRemoveCustomerController {

    @FXML
    private AnchorPane removeCustomer;

    @FXML
    private ComboBox<Customer> cusList;

    @FXML
    private Button removeButton;

    @FXML
	void removeCustomer(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Remove Customer");
		alert.setHeaderText("");
		Customer c = cusList.getSelectionModel().getSelectedItem();
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
		cusList.getItems().removeAll(cusList.getItems());
		cusList.getItems().addAll(SysData.getInstance().getCustomers().values());
	}

	public void initialize() {
		if (SysData.getInstance().getCustomers().size() > 0) {
			cusList.getItems().addAll(SysData.getInstance().getCustomers().values());
		}

	}

}
