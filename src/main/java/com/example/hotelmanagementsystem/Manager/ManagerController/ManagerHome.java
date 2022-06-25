package com.example.hotelmanagementsystem.Manager.ManagerController;

import com.example.hotelmanagementsystem.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ManagerHome {

    Main m = new Main();
    @FXML
    void LogOut(ActionEvent event) throws IOException {
        m.changeScene(event, "Login.fxml", "Hotel Management System");
    }

    @FXML
    void ManagerInfo(ActionEvent event) throws IOException {
        m.changeScene(event, "ManagerInfo.fxml", "Manager Home");
    }

}
