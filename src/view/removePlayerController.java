package view;

import java.io.IOException;

import Controller.SysData;
import Model.Coach;
import Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class removePlayerController {

    @FXML
    private AnchorPane removePlayer;

    @FXML
    private Button back;

    @FXML
    private ComboBox<Player> playerList;

    @FXML
    private Button removeButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
    	Stage stage=(Stage)removePlayer.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/playerMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    public void initialize() {
    	if(SysData.getInstance().getPlayers().size()>0) {
    		playerList.getItems().addAll(SysData.getInstance().getPlayers().values());
    	}
}
    @FXML
    void removePlayer(ActionEvent event) {
        	Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Remove Player");
    		alert.setHeaderText("");
        	Coach c=playerList.getSelectionModel().getSelectedItem();
        	SysData.getInstance().getPlayers().remove(c.getId());
        	if(!SysData.getInstance().getPlayers().containsKey(c.getId())) {
        		alert.setHeaderText("Removed Player");
        		alert.setContentText("Removed Player successfully.");
        		alert.show();
        	}
        	else {
        		alert.setHeaderText("Unable to remove Player.");
        		alert.setContentText("Cannot remove Player from database.");
        		alert.show();
        	}
    }
    

}
