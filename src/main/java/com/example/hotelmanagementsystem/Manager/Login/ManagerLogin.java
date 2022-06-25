package com.example.hotelmanagementsystem.Manager.Login;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerLogin implements Initializable {

    Main m = new Main();
    public static String currentEmployeeID;
    @FXML
    private ImageView closeWindow;

    @FXML
    private JFXTextField employeeIDField;

    @FXML
    private JFXPasswordField employeePassField;

    @FXML
    void BackToMain(ActionEvent event) throws IOException {
        m.changeScene(event, "Login.fxml", "Hotel Management System");
    }

    @FXML
    void ManagerLogin(ActionEvent event) throws IOException {
        String employeeNID = employeeIDField.getText();
        currentEmployeeID = employeeNID;
        String employeePass = employeePassField.getText();
        try {
            Connection connection = DBConnection.getConnections();
            if (employeeNID.isEmpty() || employeePass.isEmpty()) {
                Main    .showAlert(Alert.AlertType.WARNING, "Error", "Field Can't be Empty!");
            } else {
                String sql = "SELECT * FROM EMPLOYEEINFO WHERE NID = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, employeeNID);
                preparedStatement.setString(2, employeePass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Main.showAlert(Alert.AlertType.INFORMATION, "Login Success!", "Successfully Logged In!");
                    m.changeScene(event, "ManagerMain.fxml", "Manager Dashboard");
                } else {
                    Main.showAlert(Alert.AlertType.ERROR, "Login Failed!", "Incorrect NID or Password!");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}

