package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import utils.NameToWindow;

public class CoachMenuSidebarController implements Initializable {

	@FXML
	private FlowPane menupane;

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WindowManager.setContentPane(this.menupane);

	}

    public void loadUI(NameToWindow ui)
    {
    	WindowManager.openWindow(ui);
    }
    
	@FXML
	void goToAddPlayerToTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_ADDPLTOTEAM);
	}

	@FXML
	void goToChangeFirstTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_REPLACEPL);
	}

	@FXML
	void goToModifyPlayerInTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_MODIFPLAYERS);
	}


	@FXML
	void goToViewPlayers(ActionEvent event) {
		loadUI(NameToWindow.COACH_VIEWPL);
	}

	@FXML
	void removePlayerFromTeam(ActionEvent event) {
		loadUI(NameToWindow.COACH_REMOVEPL);
	}
	
	@FXML
    void goToGSPM(ActionEvent event) {
		loadUI(NameToWindow.QUERY_GETSPM);
    }

    @FXML
    void goToASPM(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETALLSPM);
    }

    @FXML
    void goToMPP(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETMPP);
    }

    @FXML
    void goToMFT(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_MFT);
    }

    @FXML
    void goToTWLHC(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETTWLHC);
    }

    @FXML
    void goToFPOBHT(ActionEvent event) {
    	loadUI(NameToWindow.QUERY_GETFPOBHT);
    }

}
