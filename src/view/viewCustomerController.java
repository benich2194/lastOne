package view;

import java.net.URL;
import java.util.Date;

import Controller.SysData;
import Model.Customer;
import Model.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.E_Cities;
import utils.E_Levels;

public class viewCustomerController {

	@FXML
    private Button back;

    @FXML
    private TableView<Customer> custTableView;
    
    @FXML
    private TableColumn<Customer, Date> custBday;

    @FXML
    private TableColumn<Customer, String> custLN;

    @FXML
    private TableColumn<Customer, String> custFN;
    
    @FXML
    private TableColumn<Customer, URL> custEmail;

    @FXML
    private TableColumn<Customer, Team> custFavTeam;

    @FXML
    private TableColumn<Customer, String> custPhoneNum;

    @FXML
    private AnchorPane modifyCustomer;

    @FXML
    private TableColumn<Customer, Integer> custHouseNum;

    @FXML
    private TableColumn<Customer, E_Cities> custCity;

    @FXML
    private TableColumn<Customer, E_Levels> custLvl;

    @FXML
    private TableColumn<Customer, String> custStreet;

    @FXML
    private TableColumn<Customer, Integer> custID;

    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Player.
        custID.setCellValueFactory(new PropertyValueFactory<>("id"));
        custFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        custLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        custBday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        custLvl.setCellValueFactory(new PropertyValueFactory<>("level"));
        custCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        custPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        custHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        custStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        custEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        custFavTeam.setCellValueFactory(new PropertyValueFactory<>("favoriteTeam"));
        
        // Display row data
        ObservableList<Customer> list = FXCollections.observableArrayList(SysData.getInstance().getCustomers().values());
        custTableView.setItems(list);
       
    }// End of viewPlayer Constructor
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}
