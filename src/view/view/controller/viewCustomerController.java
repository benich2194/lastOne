package view.view.controller;

import java.net.URL;
import java.util.Date;

import Controller.SysData;
import Model.Customer;
import Model.Subscription;
import Model.Team;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import utils.E_Cities;
import utils.E_Levels;
import view.WindowManager;

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
    
    @FXML
    private ListView<Subscription> custSubs;

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
       
        custFavTeam.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , Team>, ObservableValue<Team>>() {

    	    @Override
    	    public ObservableValue<Team> call(TableColumn.CellDataFeatures<Customer , Team> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getFavoriteTeam());

    	    }
    	});
        
        custPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        
    	custStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	custHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Customer , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});
    	
        custCity.setCellValueFactory(new Callback<CellDataFeatures<Customer, E_Cities>, ObservableValue<E_Cities>>() {
        	 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Customer, E_Cities> param) {
            	Customer cc = param.getValue();

            	E_Cities ct = cc.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
        
        custTableView.setRowFactory(tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {

                	Customer clickedRow = row.getItem();
                    showRowDetails(clickedRow);
                }
            });
            return row ;
        });
        
    }// End of viewCustomer Constructor
    
    private void showRowDetails(Customer item) {
        ObservableList<Subscription> subsOfCustomer = FXCollections.observableArrayList(item.getSubscriptions());
        custSubs.setItems(subsOfCustomer);

    }
    
    @FXML
    void goBack(ActionEvent event) {
    	WindowManager.goBack();
    }

}
