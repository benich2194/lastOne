package view.modify.controller;

import java.io.IOException;
import Controller.SysData;
import Exceptions.MissingInputException;
import Model.Stadium;
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
import view.WindowManager;

public class modifyStadiumController {


    @FXML
    private TableView<Stadium> stadiumTableView;

    @FXML
    private AnchorPane modifyStadium;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Stadium, String> stadiumName;

    @FXML
    private TableColumn<Stadium, Integer> stadiumID;

    @FXML
    private TableColumn<Stadium, Integer> stadiumCapacity;

    @FXML
    private TableColumn<Stadium, Integer> stadiumHouseNum;

    @FXML
    private TableColumn<Stadium, String> stadiumPhoneNum;

    @FXML
    private TableColumn<Stadium, String> stadiumStreet;

    @FXML
    private TableColumn<Stadium, E_Cities> stadiumCity;
    
    public void initialize() {
        // Defines how to fill data for each cell.
        // Get value from property of Stadium
    	stadiumID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	stadiumName.setCellValueFactory(new PropertyValueFactory<>("name"));
        stadiumCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        stadiumCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        stadiumPhoneNum.setCellValueFactory(new PropertyValueFactory<>("primaryPhoneNumber"));
        stadiumHouseNum.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        stadiumStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        
        // Display row data
        ObservableList<Stadium> list = FXCollections.observableArrayList(SysData.getInstance().getStadiums().values());
        stadiumTableView.setItems(list);
        MakeEditable();
    }// End of modifyPlayer Constructor
        
    private void MakeEditable() {
    	// Make changes by double clicking the Cell and pressing enter after editing
    	// === On Cell edit commit (for Name column) ===
    	stadiumName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	stadiumName.setCellFactory(TextFieldTableCell.<Stadium> forTableColumn());
    	stadiumName.setOnEditCommit((CellEditEvent<Stadium, String> event) -> {
            TablePosition<Stadium, String> pos = event.getTablePosition();
 
            String newName = event.getNewValue();
            if(!newName.isEmpty() && newName!=null && newName.matches("\\sa-zA-Z*")) {
		            int row = pos.getRow();
		            Stadium ss = event.getTableView().getItems().get(row);
		 
		            ss.setName(newName);
            } else {
				try {
					throw new MissingInputException("Stadium name");
				} catch (MissingInputException e) {
				}
            }
        });
    	
    	// === On Cell edit commit (for Phone Number column) ===
        stadiumPhoneNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stadium , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Stadium , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getPrimaryNumber());

    	    }
    	});
    	stadiumPhoneNum.setCellFactory(TextFieldTableCell.<Stadium> forTableColumn());
    	stadiumPhoneNum.setOnEditCommit((CellEditEvent<Stadium, String> event) -> {
            TablePosition<Stadium, String> pos = event.getTablePosition();
 
            String newPhoneNumber = event.getNewValue();
            try {
        		Integer phone = Integer.parseInt(newPhoneNumber);
        	}catch(NumberFormatException e) {
        		newPhoneNumber="";
    		}
            
            if(!newPhoneNumber.isEmpty() && newPhoneNumber!=null) {
	            int row = pos.getRow();
	            Stadium st = event.getTableView().getItems().get(row);
	            String[] newPhoneArray = {newPhoneNumber};
	            st.getAddress().setPhoneNumber(newPhoneArray);
            } else {
				try {
					throw new MissingInputException("Phone number");
				} catch (MissingInputException e) {
				}
            }
            
        });
    	
    	// === On Cell edit commit (for Stadium Street column) ===
    	stadiumStreet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stadium , String>, ObservableValue<String>>() {

    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Stadium , String> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getStreet());

    	    }
    	});
    	
    	stadiumStreet.setCellFactory(TextFieldTableCell.<Stadium> forTableColumn());
    	stadiumStreet.setOnEditCommit((CellEditEvent<Stadium, String> event) -> {
            TablePosition<Stadium, String> pos = event.getTablePosition();
 
            String newStreet = event.getNewValue();
            if(!newStreet.isEmpty() && newStreet!=null && newStreet.matches("\\sa-zA-Z*")) {
	            int row = pos.getRow();
	            Stadium st = event.getTableView().getItems().get(row);
	 
	            st.getAddress().setStreet(newStreet);
            }
            else
				try {
					throw new MissingInputException("Street");
				} catch (MissingInputException e) {
				}
        });
    	

    	// === On Cell edit commit (for Capacity column) ============
    	stadiumCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
    	stadiumCapacity.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

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
    	
    	stadiumCapacity.setOnEditCommit((CellEditEvent<Stadium, Integer> event) -> {
            TablePosition<Stadium, Integer> pos = event.getTablePosition();
 
            Integer newValue = (event.getNewValue());
            if(newValue!=null && newValue!=0) {
	            int row = pos.getRow();
	            Stadium ss = event.getTableView().getItems().get(row);
	            ss.setCapacity(newValue);
            }
            else
				try {
					throw new MissingInputException("capacity");
				} catch (MissingInputException e) {
				}
        });
    	
    	// === On Cell edit commit (for HouseNum column) ============
    	//TO DO
    	stadiumHouseNum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Stadium , Integer>, ObservableValue<Integer>>() {

    	    @Override
    	    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Stadium , Integer> param) {
    	        return new SimpleObjectProperty<>(param.getValue().getAddress().getHouseNumber());

    	    }
    	});
    	stadiumHouseNum.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

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
    	
    	stadiumHouseNum.setOnEditCommit((CellEditEvent<Stadium, Integer> event) -> {
            TablePosition<Stadium, Integer> pos = event.getTablePosition();
 
            Integer newValue = (event.getNewValue());
            if(newValue!=null && newValue!=0) {
	            int row = pos.getRow();
	            Stadium ss = event.getTableView().getItems().get(row);
	            ss.getAddress().setHouseNumber(newValue);
            }
            else
				try {
					throw new MissingInputException("House number");
				} catch (MissingInputException e) {
				}
        });
        
        // ==== Stadium City (COMBO BOX) ===
        
        ObservableList<E_Cities> cityList = FXCollections.observableArrayList(E_Cities.values());
 
        stadiumCity.setCellValueFactory(new Callback<CellDataFeatures<Stadium, E_Cities>, ObservableValue<E_Cities>>() {
 
            @Override
            public ObservableValue<E_Cities> call(CellDataFeatures<Stadium, E_Cities> param) {
            	Stadium st = param.getValue();

            	E_Cities ct = st.getAddress().getCity();
                return new SimpleObjectProperty<E_Cities>(ct);
            }
        });
 
        stadiumCity.setCellFactory(ComboBoxTableCell.forTableColumn(cityList));
 
        stadiumCity.setOnEditCommit((CellEditEvent<Stadium, E_Cities> event) -> {
            TablePosition<Stadium, E_Cities> pos = event.getTablePosition();
 
            E_Cities newCity = event.getNewValue();
 
            int row = pos.getRow();
            Stadium st = event.getTableView().getItems().get(row);
 
            st.getAddress().setCity(newCity);
        });
 
    }//End of Method MAKEEDITABLE
    
    
    @FXML
    void goBack(ActionEvent event) throws IOException {
    	WindowManager.goBack();
    }

}
