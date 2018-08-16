package view.modify.controller;

import java.io.IOException;
import java.util.Date;
import Controller.SysData;
import Exceptions.MissingInputException;
import Model.Coach;
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


public class modifyCoachController {

    @FXML
    private TableView<Coach> coachTableView;
    
    @FXML
    private AnchorPane modifyCoach;

    @FXML
    private Button back;
    
    @FXML
    private TableColumn<Coach, Integer> coachHouseNum;

    @FXML
    private TableColumn<Coach, String> coachLN;

    @FXML
    private TableColumn<Coach, Date> coachSW;

    @FXML
    private TableColumn<Coach, String> coachPhoneNum;

    @FXML
    private TableColumn<Coach, Date> coachBday;

    @FXML
    private TableColumn<Coach, Integer> coachID;

    @FXML
    private TableColumn<Coach, String> coachStreet;

    @FXML
    private TableColumn<Coach, E_Levels> coachLvl;

    @FXML
    private TableColumn<Coach, String> coachFN;

    @FXML
    private TableColumn<Coach, E_Cities> coachCity;

    public void initialize() throws MissingInputException {
        // Defines how to fill data for each cell.
        // Get value from property of Player.
        coachID.setCellValueFactory(new PropertyValueFactory<>("id"));
        coachFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        coachLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        coachBday.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        coachLvl.setCellValueFactory(new PropertyValueFactory<>("playerLvl"));
        coachCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        coachPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        coachHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        coachStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        coachSW.setCellValueFactory(new PropertyValueFactory<>("startWorkingDate"));
        
        // Display row data
        if(!SysData.getInstance().getCoachs().isEmpty()) {
	        ObservableList<Coach> list = FXCollections.observableArrayList(SysData.getInstance().getCoachs().values());
	        coachTableView.setItems(list);
	        MakeEditable();
        }
        

    }// End of Initialize
        
    private void MakeEditable() throws MissingInputException{
    	// Make changes by double clicking the Cell and pressing enter after editing
    	// === On Cell edit commit (for FirstName column) ===
	    	coachFN.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	    	coachFN.setCellFactory(TextFieldTableCell.<Coach> forTableColumn());
	    	coachFN.setOnEditCommit((CellEditEvent<Coach, String> event) -> {
	            TablePosition<Coach, String> pos = event.getTablePosition();
	 
	            String newFirstName = event.getNewValue();
	            if(!newFirstName.isEmpty() && newFirstName!=null && newFirstName.matches("\\sa-zA-Z*")) {
			            int row = pos.getRow();
			            Coach cc = event.getTableView().getItems().get(row);
			 
			            cc.setFirstName(newFirstName);
	            } else
					try {
						throw new MissingInputException("First name");
					} catch (MissingInputException e) {
					}
	        });
	    	
	    	// === On Cell edit commit (for LastName column) ===
	    	coachLN.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	    	coachLN.setCellFactory(TextFieldTableCell.<Coach> forTableColumn());
	    	coachLN.setOnEditCommit((CellEditEvent<Coach, String> event) -> {
	            TablePosition<Coach, String> pos = event.getTablePosition();
	 
	            
	            String newLastName = event.getNewValue();
	            if(!newLastName.isEmpty() && newLastName!=null && newLastName.matches("\\sa-zA-Z*")) {
		            int row = pos.getRow();
		            Coach cc = event.getTableView().getItems().get(row);
		 
		            cc.setLastName(newLastName);
	            } else
					try {
						throw new MissingInputException("Last name");
					} catch (MissingInputException e) {
					}
	        });
	    	
	        // ==== Coach Level (COMBO BOX) ===
	        
	        ObservableList<E_Levels> levelList = FXCollections.observableArrayList(E_Levels.values());
	 
	        coachLvl.setCellValueFactory(new Callback<CellDataFeatures<Coach, E_Levels>, ObservableValue<E_Levels>>() {
	 
	            @Override
	            public ObservableValue<E_Levels> call(CellDataFeatures<Coach, E_Levels> param) {
	            	Coach cc = param.getValue();
	
	                E_Levels lvl = cc.getLevel();
	                return new SimpleObjectProperty<E_Levels>(lvl);
	            }
	        });
	 
	        coachLvl.setCellFactory(ComboBoxTableCell.forTableColumn(levelList));
	 
	        coachLvl.setOnEditCommit((CellEditEvent<Coach, E_Levels> event) -> {
	            TablePosition<Coach, E_Levels> pos = event.getTablePosition();
	 
	            E_Levels newPos = event.getNewValue();
	 
	            int row = pos.getRow();
	            Coach cc = event.getTableView().getItems().get(row);
	 
	            cc.setLevel(newPos);
	        });
	 
	       
	    	// === On Cell edit commit (for Phone Number column) ===
	        coachPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , String>, ObservableValue<String>>() {
	
	    	    @Override
	    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Coach , String> param) {
	    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());
	
	    	    }
	    	});
	        coachPhoneNum.setCellFactory(TextFieldTableCell.<Coach> forTableColumn());
	        coachPhoneNum.setOnEditCommit((CellEditEvent<Coach, String> event) -> {
	            TablePosition<Coach, String> pos = event.getTablePosition();
	 
	            String newPhoneNumber = event.getNewValue();
	            try {
            		Integer phone = Integer.parseInt(newPhoneNumber);
            	}catch(NumberFormatException e) {
	        		newPhoneNumber="";
        		}
	            
	            if(!newPhoneNumber.isEmpty() && newPhoneNumber!=null) {
		            int row = pos.getRow();
		            Coach cc = event.getTableView().getItems().get(row);
		            String[] newPhoneArray = {newPhoneNumber};
		            cc.getAddress().setPhoneNumber(newPhoneArray);
	            } else {
					try {
						throw new MissingInputException("Phone number");
					} catch (MissingInputException e) {
					}
	            }
	        });
	    	
	    	// === On Cell edit commit (for Coach's Street column) ===
	    	coachStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , String>, ObservableValue<String>>() {
	    		
	    	    @Override
	    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Coach , String> param) {
	    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());
	
	    	    }
	    	});
	    	
	    	coachStreet.setCellFactory(TextFieldTableCell.<Coach> forTableColumn());
	    	coachStreet.setOnEditCommit((CellEditEvent<Coach, String> event) -> {
	            TablePosition<Coach, String> pos = event.getTablePosition();
	 
	            String newStreet = event.getNewValue();
	            if(!newStreet.isEmpty() && newStreet!=null && newStreet.matches("\\sa-zA-Z*")) {
		            int row = pos.getRow();
		            Coach cc = event.getTableView().getItems().get(row);
		 
		            cc.getAddress().setStreet(newStreet);
	            }
	            else
					try {
						throw new MissingInputException("Street");
					} catch (MissingInputException e) {
					}
	        });
	    	
	    	// === On Cell edit commit (for HouseNum column) ============
	    	coachHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Coach , Integer>, ObservableValue<Integer>>() {
	
	    	    @Override
	    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Coach , Integer> param) {
	    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());
	
	    	    }
	    	});
	    	coachHouseNum.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
	
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
	    
	      
	    	coachHouseNum.setOnEditCommit((CellEditEvent<Coach, Integer> event) -> {
	            TablePosition<Coach, Integer> pos = event.getTablePosition();
	            
	            
	            Integer newValue = (event.getNewValue());
	            if(newValue!=null && newValue!=0) {
		            int row = pos.getRow();
		            Coach cc = event.getTableView().getItems().get(row);
		            cc.getAddress().setHouseNumber(newValue);
	            }
	            else
					try {
						throw new MissingInputException("House number");
					} catch (MissingInputException e) {
					}
	        });
	        
	        // ==== Coach City (COMBO BOX) ===
	        
	        ObservableList<E_Cities> cityList = FXCollections.observableArrayList(E_Cities.values());
	 
	        coachCity.setCellValueFactory(new Callback<CellDataFeatures<Coach, E_Cities>, ObservableValue<E_Cities>>() {
	 
	            @Override
	            public ObservableValue<E_Cities> call(CellDataFeatures<Coach, E_Cities> param) {
	            	Coach cc = param.getValue();
	
	            	E_Cities ct = cc.getAddress().getCity();
	                return new SimpleObjectProperty<E_Cities>(ct);
	            }
	        });
	 
	        coachCity.setCellFactory(ComboBoxTableCell.forTableColumn(cityList));
	 
	        coachCity.setOnEditCommit((CellEditEvent<Coach, E_Cities> event) -> {
	            TablePosition<Coach, E_Cities> pos = event.getTablePosition();
	 
	            E_Cities newCity = event.getNewValue();
	 
	            int row = pos.getRow();
	            Coach cc = event.getTableView().getItems().get(row);
	 
	            cc.getAddress().setCity(newCity);
	        });
    }//End of Method MAKEEDITABLE
    
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
		//Go back to menu button Event
    	WindowManager.goBack();
    }

}
