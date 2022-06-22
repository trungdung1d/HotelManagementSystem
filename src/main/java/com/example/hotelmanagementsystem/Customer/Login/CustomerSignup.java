package com.example.hotelmanagementsystem.Customer.Login;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerSignup implements Initializable {
    Main m = new Main();
    @FXML
    private JFXTextField CustomerEmailField;

    @FXML
    private JFXTextField CustomerNameField;

    @FXML
    private JFXTextField CustomerNameIDField;

    @FXML
    private JFXTextField CustomerPassField;

    @FXML
    private JFXTextField CustomerPhoneField;

    @FXML
    private JFXTextField CustomerAddressField;

    @FXML
    private ImageView closeWindow;

    Connection connection = DBConnection.getConnections();

    @FXML
    void BackToCustomerLogin(ActionEvent event) throws Exception {
        m.changeScene("CustomerLogin.fxml", "Customer Login");
    }

    @FXML
    void CustomerSignUp(ActionEvent event) throws Exception, SQLException {
        String CustomerName = CustomerNameField.getText();
        String CustomerNameID = CustomerNameIDField.getText();
        String CustomerPassword = CustomerPassField.getText();
        String CustomerEmail = CustomerEmailField.getText();
        String CustomerPhone = CustomerPhoneField.getText();
        String CustomerAddress = CustomerAddressField.getText();

        if (CustomerName.isEmpty() || CustomerNameID.isEmpty() || CustomerPassword.isEmpty() || CustomerEmail.isEmpty() || CustomerPhone.isEmpty() || CustomerAddress.isEmpty()) {
            Main.showAlert(Alert.AlertType.WARNING, "Error", "Field can not be empty!");
        } else {
            String sql = "INSERT INTO customerinfo(NAME, NID, PASSWORD, EMAIL, PHONE, ADDRESS) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, CustomerName);
            preparedStatement.setString(2, CustomerNameID);
            preparedStatement.setString(3, CustomerPassword);
            preparedStatement.setString(4, CustomerEmail);
            preparedStatement.setString(5, CustomerPhone);
            preparedStatement.setString(6, CustomerAddress);
            try {
                preparedStatement.execute();
                Main.showAlert(Alert.AlertType.INFORMATION, "Successful", "Sign up Successful!");
                m.changeScene("CustomerLogin.fxml", "Customer Login");
            } catch (SQLException e) {
                Main.showAlert(Alert.AlertType.ERROR, "Error", "Account already exists with this ID!");
            } finally {
                DBConnection.closeConnections();
            }
        }
    }

    private static final String IDLE_BUTTON_STYLE = "-fx-scale-x: 1; -fx-scale-y: 1; -fx-opacity: 0.8";
    private static final String HOVERED_BUTTON_STYLE = "-fx-scale-x: 1.2; -fx-scale-y: 1.2; -fx-opacity: 1";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });
        closeWindow.setOnMouseEntered(e -> closeWindow.setStyle(HOVERED_BUTTON_STYLE));
        closeWindow.setOnMouseExited(e -> closeWindow.setStyle(IDLE_BUTTON_STYLE));
    }
}
