package view;

import java.io.IOException;

import Controller.SysData;
import Model.Stadium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.E_Cities;

public class removeStadiumController {

    @FXML
    private AnchorPane removeStadium;

    @FXML
    private Button back;
    
    @FXML
    private ComboBox<Stadium> stadiumList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)removeStadium.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/stadiumMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    @FXML
    void removeStadium(ActionEvent event) {
    	Stadium s=stadiumList.getSelectionModel().getSelectedItem();
    	SysData.getInstance().getStadiums().remove(s.getId());
    }
    public void initialize() {
    	stadiumList.getItems().addAll(SysData.getInstance().getStadiums().values());
    }
}
