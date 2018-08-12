package view.menus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.NameToWindow;
import view.WindowManager;

public class queriesController {

    @FXML
    private Button buttonGMPP;

    @FXML
    private Button buttonGSPM;

    @FXML
    private Button buttonGASPM;

    @FXML
    private Button buttonGSTCS;

    @FXML
    private Button buttonGEWMT;

    @FXML
    private Button buttonGFPBHT;

    @FXML
    private AnchorPane queries;

    @FXML
    private Button buttonGMFT;

    @FXML
    private Button buttonGMACBS;

    @FXML
    private Button buttonGTWLHC;

    
    //TO DO
    @FXML
    void getSuperPlayerMakerPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETSPM);
    }

    @FXML
    void getASPMPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETALLSPM);
    }

    @FXML
    void getMPPPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETMPP);
    }

    @FXML
    void getMFTPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_MFT);
    }

    @FXML
    void getMACBSPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETMAC);
    }

    @FXML
    void getEWMTPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETEWMT);
    }

    @FXML
    void getTWLHCPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETTWLHC);
    }

    @FXML
    void getSTCSPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETCSOOT);
    }

    @FXML
    void getFPBHTPage(ActionEvent event) {
    	WindowManager.openWindow(NameToWindow.QUERY_GETFPOBHT);
    }

}
