package view;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Controller.SysData;
import Model.Player;

public class modifyPlayerController {

    @FXML
    private TableView<List<Player>> playerTableView;

    @FXML
    private AnchorPane modifyPlayer;

    @FXML
    private Button back;

 //     public void initialize(URL url, ResourceBundle rb) {
////    	playerTableView = new TableView<ObservableList<Player>>();
////    	playerTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
////    	playerTableView.getItems().setAll((Collection<? extends ObservableList<Player>>) SysData.getInstance().getPlayers().values());
//   
//    	
//	List<Player> playerList = (List<Player>) (SysData.getInstance().getPlayers().values());
//	playerTableView = new TableView<Player>();
//	for (int i = 0; i < playerList.size(); i++) {
//		playerTableView.getItems().add(playerList.get(i));
//	}
   

	@FXML
    void goBack(ActionEvent event)  throws IOException{
    	Stage stage=(Stage)modifyPlayer.getScene().getWindow();
		stage.close();
    	FXMLLoader load=new FXMLLoader(getClass().getResource("/view/playerMenu.fxml"));
    	Stage primaryStage=new Stage();
    	Parent root=load.load();
    	Scene scene=new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }

}