package view.modify.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import Controller.SysData;
import Exceptions.MissingInputException;
import Model.Customer;
import Model.Team;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import utils.E_Cities;
import utils.E_Levels;
import view.WindowManager;


public class modifyCustomerController {

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

    public void initialize() throws MissingInputException{
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
        if(!SysData.getInstance().getCustomers().isEmpty()) {
	        ObservableList<Customer> list = FXCollections.observableArrayList(SysData.getInstance().getCustomers().values());
	        custTableView.setItems(list);
	        MakeEditable();
        }
    }// End of modifyPlayer Constructor
        
    private void MakeEditable() throws MissingInputException{
    	// Make changes by double clicking the Cell and pressing enter after editing
    	// === On Cell edit commit (for FirstName column) ===
    	custFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	custFN.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());
    	custFN.setOnEditCommit((CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
 
            String newFirstName = event.getNewValue();
            if(!newFirstName.isEmpty() && newFirstName!=null && newFirstName.matches("\\sa-zA-Z*")) {
	            int row = pos.getRow();
	            Customer cc = event.getTableView().getItems().get(row);
	 
	            cc.setFirstName(newFirstName);
            }else
				try {
					throw new MissingInputException("First name");
				} catch (MissingInputException e) {
				}    
        });
    	
    	// === On Cell edit commit (for LastName column) ===
    	custLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	custLN.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());
    	custLN.setOnEditCommit((CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
 
            String newLastName = event.getNewValue();
            if(!newLastName.isEmpty() && newLastName!=null && newLastName.matches("\\sa-zA-Z*")) {
	            int row = pos.getRow();
	            Customer cc = event.getTableView().getItems().get(row);
	 
	            cc.setLastName(newLastName);
            }try {
				throw new MissingInputException("Last name");
			} catch (MissingInputException e) {
			}
        });
    	
    	// === On Cell edit commit (for Email column) ===
    	custEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    	//custEmail.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());
    	custEmail.setOnEditCommit((CellEditEvent<Customer, URL> event) -> {
            TablePosition<Customer, URL> pos = event.getTablePosition();
 
            URL newemail = event.getNewValue();
 
            int row = pos.getRow();
            Customer cc = event.getTableView().getItems().get(row);
 
            cc.setEmail(newemail);
        });
  
        // ==== Customer Level (COMBO BOX) ===
        
        ObservableList<E_Levels> levelList = FXCollections.observableArrayList(E_Levels.values());
 
        custLvl.setCellValueFactory(new Callback<CellDataFeatures<Customer, E_Levels>, ObservableValue<E_Levels>>() {
 
            @Override
            public ObservableValue<E_Levels> call(CellDataFeatures<Customer, E_Levels> param) {
            	Customer cc = param.getValue();

                E_Levels lvl = cc.getLevel();
                return new SimpleObjectProperty<E_Levels>(lvl);
            }
        });
 
        custLvl.setCellFactory(ComboBoxTableCell.forTableColumn(levelList));
 
        custLvl.setOnEditCommit((CellEditEvent<Customer, E_Levels> event) -> {
            TablePosition<Customer, E_Levels> pos = event.getTablePosition();
 
            E_Levels newPos = event.getNewValue();
 
            int row = pos.getRow();
            Customer cc = event.getTableView().getItems().get(row);
 
            cc.setLevel(newPos);
        });
 
        
    	// === On Cell edit commit (for Phone Number column) ===
        custPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        custPhoneNum.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());
        custPhoneNum.setOnEditCommit((CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
 
            String newPhoneNumber = event.getNewValue();
            try {
        		Integer phone = Integer.parseInt(newPhoneNumber);
        	}catch(NumberFormatException e) {
        		newPhoneNumber="";
    		}
            
            if(!newPhoneNumber.isEmpty() && newPhoneNumber!=null) {
	            int row = pos.getRow();
	            Customer cc = event.getTableView().getItems().get(row);
	            String[] newPhoneArray = {newPhoneNumber};
	            cc.getAddress().setPhoneNumber(newPhoneArray);
            }else
				try {
					throw new MissingInputException("Phone number");
				} catch (MissingInputException e) {
				}
            
        });
    	
    	// === On Cell edit commit (for Customer's Street column) ===
    	custStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	custStreet.setCellFactory(TextFieldTableCell.<Customer> forTableColumn());
    	custStreet.setOnEditCommit((CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
 
            String newStreet = event.getNewValue();
            if(!newStreet.isEmpty() && newStreet!=null && newStreet.matches("\\sa-zA-Z*")) {
	            int row = pos.getRow();
	            Customer cc = event.getTableView().getItems().get(row);
	 
	            cc.getAddress().setStreet(newStreet);
            }
            else
				try {
					throw new MissingInputException("Street");
				} catch (MissingInputException e) {
				}
        });
    	
    	// === On Cell edit commit (for HouseNum column) ============
    	//TO DO
    	custHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Customer , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});
    	custHouseNum.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

            @Override
            public String toString(Integer object) {
            	if(object!=null)
            		return object.toString();
            	return "";
            }

            @Override
            public Integer fromString(String string) {
            	try {
            		return Integer.parseInt(string);
            	} catch (NumberFormatException e) {
            		return 0;
            	}
            }

        }));
    	
    	custHouseNum.setOnEditCommit((CellEditEvent<Customer, Integer> event) -> {
            TablePosition<Customer, Integer> pos = event.getTablePosition();
 
            Integer newValue = event.getNewValue();
            if(newValue!=null && newValue!=0) {
	            int row = pos.getRow();
	            Customer cc = event.getTableView().getItems().get(row);
	            cc.getAddress().setHouseNumber(newValue);
            } else
				try {
					throw new MissingInputException("House number");
				} catch (MissingInputException e) {
				}
        });
        
   // ==== Customer City (COMBO BOX) ===
        
        ObservableList<E_Cities> cityList = FXCollections.observableArrayList(E_Cities.values());
 
        custCity.setCellValueFactory(new Callback<CellDataFeatures<Customer, E_Cities>, ObservableValue<E_Cities>>() {
 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Customer, E_Cities> param) {
            	Customer cc = param.getValue();

            	E_Cities ct = cc.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
 
        custCity.setCellFactory(ComboBoxTableCell.forTableColumn(cityList));
 
        custCity.setOnEditCommit((CellEditEvent<Customer, E_Cities> event) -> {
            TablePosition<Customer, E_Cities> pos = event.getTablePosition();
 
            E_Cities newCity = event.getNewValue();
 
            int row = pos.getRow();
            Customer cc = event.getTableView().getItems().get(row);
 
            cc.getAddress().setCity(newCity);
        });
        
   // ==== Customer's favorite team (COMBO BOX) ===
        
        ObservableList<Team> teamList = FXCollections.observableArrayList(SysData.getInstance().getTeams().values());
 
        custFavTeam.setCellValueFactory(new Callback<CellDataFeatures<Customer, Team>, ObservableValue<Team>>() {
 
            @Override
            public ObservableValue<Team> call(CellDataFeatures<Customer, Team> param) {
            	Customer cc = param.getValue();
            	Team tm = cc.getFavoriteTeam();
                return new SimpleObjectProperty<Team>(tm);
            }
        });
 
        custFavTeam.setCellFactory(ComboBoxTableCell.forTableColumn(teamList));
 
        custFavTeam.setOnEditCommit((CellEditEvent<Customer, Team> event) -> {
            TablePosition<Customer, Team> pos = event.getTablePosition();
 
            Team newtm = event.getNewValue();
 
            int row = pos.getRow();
            Customer cc = event.getTableView().getItems().get(row);
 
            cc.setFavoriteTeam(newtm);
        });
    }//End of Method MAKEEDITABLE
    
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();	
    }

}
