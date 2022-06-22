package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerHome implements Initializable {
    public static int welcome = 0;
    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private StackPane rootPane;

    @FXML
    void BackToLoginPage(ActionEvent event) throws IOException {
//        CommonTask.pageNavigation("/com.example.hotelmanagementsystem/Login.fxml", Main.stage,this.getClass(),"Customer Home", 600, 400);
        welcome = 0;
    }

    @FXML
    void CustomerInfo(ActionEvent event) throws IOException {
//        CommonTask.pageNavigation("/com.example.hotelmanagementsystem/CustomerInfo.fxml", null,this.getClass(),"Customer Home", 550, 400);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(welcome == 0) {
            Main.showJFXAlert(rootPane, rootAnchorPane, "information", "Login Success!", "Successfully Logged In!", JFXDialog.DialogTransition.CENTER);
            welcome = 1;
        }
    }

}
