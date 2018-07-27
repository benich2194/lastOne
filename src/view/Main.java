package view;
	
import javafx.application.Application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.SysData;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	protected static SysData sysData;
	
	public void start(Stage primaryStage) {
		try {
			
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root,640,480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		sysData = SysData.getInstance();
		deserialize("sysData.ser");
		launch(args);
	}

	private static void serialize(String string, SysData sysData2) throws Exception{
		try{
			FileOutputStream fileOut = new FileOutputStream(string);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(sysData);
			out.close();
			fileOut.close();
		}
	catch (Exception e) {
		e.printStackTrace();
	}
	
	}
	private static void deserialize(String fileName) {
		
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
		    ObjectInputStream in = new ObjectInputStream(fileIn);
		    in.close();
		    fileIn.close();
		} catch(IOException i) {
		    i.printStackTrace();
		}
	}
}
