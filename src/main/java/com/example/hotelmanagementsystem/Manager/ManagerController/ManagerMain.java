package com.example.hotelmanagementsystem.Manager.ManagerController;

import com.example.hotelmanagementsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMain implements Initializable {

    @FXML
    private BorderPane borderpane;

    @FXML
    void CheckOutDetails(ActionEvent event) {
        windowLoad("ManagerCheckOutDetails.fxml");
    }

    @FXML
    void ManageRoom(ActionEvent event) {
        windowLoad("ManagerRoomDetails.fxml");
    }

    @FXML
    void ManagerCheckIn(ActionEvent event) {
        windowLoad("ManagerCheckIn.fxml");
    }

    @FXML
    void ManagerCheckOut(ActionEvent event) {
        windowLoad("ManagerCheckOut.fxml");
    }

    @FXML
    void ManagerHome(ActionEvent event) {
        windowLoad("ManagerHome.fxml");
    }
    public void windowLoad(String fxml){
        try
        {
            Pane pane = FXMLLoader.load(Main.class.getResource(fxml));
            borderpane.setCenter(pane);
        }
        catch(Exception e)
        {
            System.out.println("Problem : " + e);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        windowLoad("ManagerHome.fxml");
    }

}
