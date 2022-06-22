package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerMain implements Initializable {

    @FXML
    private AnchorPane CustomerMainPane;

    @FXML
    private BorderPane borderpane;

    @FXML
    private JFXButton checkInID;

    @FXML
    private JFXButton checkInOutID;

    @FXML
    private FontAwesomeIconView closeWindow;

    @FXML
    private JFXButton goHomeID;

    @FXML
    private FontAwesomeIconView maximizeWindow;

    @FXML
    private FontAwesomeIconView minimizeWindow;

    @FXML
    private JFXButton roomDetailsID;

    @FXML
    private StackPane rootPane;

    @FXML
    void GoCheckDetails(ActionEvent event) {
        windowLoad("/com.example.hotelmanagementsystem/CustomerCheckOut.fxml");
    }

    @FXML
    void GoCheckIn(ActionEvent event) {
        windowLoadStackPane("/com.example.hotelmanagementsystem/CustomerCheckIn.fxml");
    }

    @FXML
    void GoHome(ActionEvent event) {
        windowLoadStackPane("/com.example.hotelmanagementsystem/CustomerHome.fxml");
    }

    @FXML
    void GoRoomDetails(ActionEvent event) {
        windowLoad("/com.example.hotelmanagementsystem/CustomerRoomDetails.fxml");
    }

    private void minimizeStageOfNode(Node node) {
        ((Stage) (node).getScene().getWindow()).setIconified(true);
    }

    public void windowLoad(String URL)
    {
        try
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(pane);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
        }
    }

    public void windowLoadStackPane(String URL)
    {
        try
        {
            StackPane pane = FXMLLoader.load(getClass().getResource(URL));
            borderpane.setCenter(pane);
        }
        catch(Exception err)
        {
            System.out.println("Problem : " + err);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        windowLoadStackPane("/com.example.hotelmanagementsystem/CustomerHome.fxml");
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });

        minimizeWindow.setOnMouseClicked(event -> {
            minimizeStageOfNode((Node) event.getSource());
        });

        AtomicInteger maxWindow = new AtomicInteger();
        maximizeWindow.setOnMouseClicked(event -> {
            Stage stage1 = (Stage) CustomerMainPane.getScene().getWindow();
            stage1.setMaximized(!stage1.isMaximized());
        });
    }
}
