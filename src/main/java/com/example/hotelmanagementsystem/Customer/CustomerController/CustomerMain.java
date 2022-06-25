package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMain implements Initializable {

    @FXML
    private AnchorPane CustomerMainPane;

    @FXML
    public BorderPane borderpane;

    @FXML
    private JFXButton checkInID;

    @FXML
    private JFXButton checkInOutID;

    @FXML
    private JFXButton goHomeID;

    @FXML
    private JFXButton roomDetailsID;

    @FXML
    private StackPane rootPane;

    Main m = new Main();

    @FXML
    void GoCheckDetails(ActionEvent event) throws IOException {
        m.changeScene(event, "CustomerCheckOut.fxml", null);
    }

    @FXML
    void GoCheckIn(ActionEvent event) throws IOException {
        m.changeScene(event,"CustomerCheckIn.fxml", null);
    }

    @FXML
    void GoHome(ActionEvent event) throws IOException {
        m.changeScene(event,"CustomerHome.fxml", null);
    }

    @FXML
    void GoRoomDetails(ActionEvent event) throws IOException {
        m.changeScene(event,"CustomerRoomDetails.fxml",null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        windowLoadStackPane("CustomerHome.fxml");
    }

    public void windowLoadStackPane(String fxml){
        try
        {
            StackPane pane = FXMLLoader.load(Main.class.getResource(fxml));
            borderpane.setCenter(pane);
        }
        catch(Exception e)
        {
            System.out.println("Problem : " + e);
        }
    }
}
