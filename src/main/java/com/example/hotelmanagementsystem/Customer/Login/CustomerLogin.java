package com.example.hotelmanagementsystem.Customer.Login;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class    CustomerLogin implements Initializable {

    Main m = new Main();
    public static String currentCustomerNameID;
    @FXML
    private ImageView closeWindow;

    @FXML
    private JFXTextField customerIDField;

    @FXML
    private JFXPasswordField customerPassField;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private StackPane rootPane;

    @FXML
    void BackToMain(ActionEvent mouseEvent) throws IOException {
        m.changeScene(mouseEvent,"Login.fxml", "Hotel Management System");
    }

    @FXML
    void CustomerLogin(ActionEvent actionEvent) throws IOException, SQLException {
        Connection connection = DBConnection.getConnections();
        String customerNameID = customerIDField.getText();
        currentCustomerNameID = customerNameID;
        String customerPass = customerPassField.getText();
        try{
            if(customerNameID.isEmpty() == true || customerPass.isEmpty() == true){
                Main.showJFXAlert(rootPane, rootAnchorPane, "warning", "Warning!","Username or Password can not be empty!", JFXDialog.DialogTransition.CENTER);
            }else {
                String sql = "SELECT * FROM customerinfo WHERE NID = ? AND PASSWORD = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, customerNameID);
                preparedStatement.setString(2, customerPass);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    m.changeScene(actionEvent, "CustomerMain.fxml", "Customer Dashboard");
                }else {
                    Main.showJFXAlert(rootPane, rootAnchorPane, "warning", "Login Failed!", "Wrong Username or Password !", JFXDialog.DialogTransition.CENTER);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    @FXML
    void CustomerSignup(ActionEvent mouseEvent) throws IOException {
        m.changeScene(mouseEvent ,"CustomerSignup.fxml", "Customer Signup");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        closeWindow.setOnMouseClicked(event ->{
            System.exit(0);
        } );
    }
}
