package view;

import java.io.IOException;
import java.util.Date;
import Controller.SysData;
import Model.Receptionist;
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

public class modifyReceptionistController {

    @FXML
    private TableView<Receptionist> recepTableView;
    
    @FXML
    private AnchorPane modifyCoach;

    @FXML
    private Button back;
    
    @FXML
    private TableColumn<Receptionist, Integer> recepHouseNum;

    @FXML
    private TableColumn<Receptionist, String> recepLN;

    @FXML
    private TableColumn<Receptionist, Date> recepSW;

    @FXML
    private TableColumn<Receptionist, String> recepPhoneNum;

    @FXML
    private TableColumn<Receptionist, Date> recepBday;

    @FXML
    private TableColumn<Receptionist, Integer> recepID;

    @FXML
    private TableColumn<Receptionist, String> recepStreet;

    @FXML
    private TableColumn<Receptionist, String> recepFN;

    @FXML
    private TableColumn<Receptionist, E_Cities> recepCity;

    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Player.
        recepID.setCellValueFactory(new PropertyValueFactory<>("id"));
        recepFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        recepLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        recepBday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        recepCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        recepPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        recepHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        recepStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        recepSW.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        
        // Display row data
        ObservableList<Receptionist> list = FXCollections.observableArrayList(SysData.getInstance().getReceptionists().values());
        recepTableView.setItems(list);
        MakeEditable();
    }// End of modifyPlayer Constructor
        
    private void MakeEditable() {
    	// Make changes by double clicking the Cell and pressing enter after editing
    	// === On Cell edit commit (for FirstName column) ===
    	recepFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	recepFN.setCellFactory(TextFieldTableCell.<Receptionist> forTableColumn());
    	recepFN.setOnEditCommit((CellEditEvent<Receptionist, String> event) -> {
            TablePosition<Receptionist, String> pos = event.getTablePosition();
 
            String newFirstName = event.getNewValue();
 
            int row = pos.getRow();
            Receptionist rr = event.getTableView().getItems().get(row);
 
            rr.setFirstName(newFirstName);
        });
    	
    	// === On Cell edit commit (for LastName column) ===
    	recepLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	recepLN.setCellFactory(TextFieldTableCell.<Receptionist> forTableColumn());
    	recepLN.setOnEditCommit((CellEditEvent<Receptionist, String> event) -> {
            TablePosition<Receptionist, String> pos = event.getTablePosition();
 
            String newLastName = event.getNewValue();
 
            int row = pos.getRow();
            Receptionist cc = event.getTableView().getItems().get(row);
 
            cc.setLastName(newLastName);
        });
       
    	// === On Cell edit commit (for Phone Number column) ===
        recepPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receptionist , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Receptionist , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        recepPhoneNum.setCellFactory(TextFieldTableCell.<Receptionist> forTableColumn());
        recepPhoneNum.setOnEditCommit((CellEditEvent<Receptionist, String> event) -> {
            TablePosition<Receptionist, String> pos = event.getTablePosition();
 
            String newPhoneNumber = event.getNewValue();
 
            int row = pos.getRow();
            Receptionist cc = event.getTableView().getItems().get(row);
            String[] newPhoneArray = {newPhoneNumber};
            cc.getAddress().setPhoneNumber(newPhoneArray);
            
        });
    	
    	// === On Cell edit commit (for Receptionist's Street column) ===
    	recepStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receptionist , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Receptionist , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	recepStreet.setCellFactory(TextFieldTableCell.<Receptionist> forTableColumn());
    	recepStreet.setOnEditCommit((CellEditEvent<Receptionist, String> event) -> {
            TablePosition<Receptionist, String> pos = event.getTablePosition();
 
            String newStreet = event.getNewValue();
 
            int row = pos.getRow();
            Receptionist cc = event.getTableView().getItems().get(row);
 
            cc.getAddress().setStreet(newStreet);
        });
    	
    	// === On Cell edit commit (for HouseNum column) ============
    	recepHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receptionist , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Receptionist , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});
    	recepHouseNum.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
    	
    	recepHouseNum.setOnEditCommit((CellEditEvent<Receptionist, Integer> event) -> {
            TablePosition<Receptionist, Integer> pos = event.getTablePosition();
 
            Integer newValue = event.getNewValue();
 
            int row = pos.getRow();
            Receptionist cc = event.getTableView().getItems().get(row);
            cc.getAddress().setHouseNumber(newValue);
        });
        
        // ==== Receptionist City (COMBO BOX) ===
        
        ObservableList<E_Cities> cityList = FXCollections.observableArrayList(E_Cities.values());
 
        recepCity.setCellValueFactory(new Callback<CellDataFeatures<Receptionist, E_Cities>, ObservableValue<E_Cities>>() {
 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Receptionist, E_Cities> param) {
            	Receptionist cc = param.getValue();

            	E_Cities ct = cc.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
 
        recepCity.setCellFactory(ComboBoxTableCell.forTableColumn(cityList));
 
        recepCity.setOnEditCommit((CellEditEvent<Receptionist, E_Cities> event) -> {
            TablePosition<Receptionist, E_Cities> pos = event.getTablePosition();
 
            E_Cities newCity = event.getNewValue();
 
            int row = pos.getRow();
            Receptionist cc = event.getTableView().getItems().get(row);
 
            cc.getAddress().setCity(newCity);
        });
    }//End of Method MAKEEDITABLE
    
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
		//Go back to menu button Event
    	WindowManager.goBack();
    }

}
