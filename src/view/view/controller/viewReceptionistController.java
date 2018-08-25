package view.view.controller;

import java.io.IOException;
import java.util.Date;
import Controller.SysData;
import Model.Receptionist;
import Model.Stadium;
import Model.Subscription;
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
import view.WindowManager;

public class viewReceptionistController {

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
    
    @FXML
    private ListView<Subscription> recepSubs;
    
    @FXML
    private TableColumn<Receptionist, Stadium> recepWorkingStadium;

    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Receptionist
        recepID.setCellValueFactory(new PropertyValueFactory<>("id"));
        recepFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        recepLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        recepBday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        recepCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        recepPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        recepHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        recepStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        recepSW.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        recepWorkingStadium.setCellValueFactory(new PropertyValueFactory<>("workingStadium"));
        
        // Display row data
        ObservableList<Receptionist> list = FXCollections.observableArrayList(SysData.getInstance().getReceptionists().values());
        recepTableView.setItems(list);
        
        recepPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receptionist , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Receptionist , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
        
    	recepStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receptionist , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Receptionist , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	recepHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Receptionist , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Receptionist , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});
    	
        recepCity.setCellValueFactory(new Callback<CellDataFeatures<Receptionist, E_Cities>, ObservableValue<E_Cities>>() {
        	 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Receptionist, E_Cities> param) {
            	Receptionist cc = param.getValue();

            	E_Cities ct = cc.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
        
        recepTableView.setRowFactory(tv -> {
            TableRow<Receptionist> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
                     && event.getClickCount() == 2) {

                	Receptionist clickedRow = row.getItem();
                    showRowDetails(clickedRow);
                }
            });
            return row ;
        });
    }// End of modifyPlayer Constructor
        
    private void showRowDetails(Receptionist item) {
        ObservableList<Subscription> subsOfrec = FXCollections.observableArrayList(item.getSubscriptions());
        recepSubs.setItems(subsOfrec);

    }
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
		//Go back to menu button Event
    	WindowManager.goBack();
    }

}
