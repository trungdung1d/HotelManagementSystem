package com.example.hotelmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements  Initializable {

    @FXML
    private Button adminLoginBtn;

    @FXML
    private ImageView closeWindow;

    @FXML
    private Button customerLoginBtn;

    @FXML
    private Button managerLoginBtn;

    @FXML
    void Admin_Login(ActionEvent event) throws  Exception{
        try {
            Stage stage = (Stage) adminLoginBtn.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
            primaryStage.setTitle("Admin Login");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void Customer_Login(ActionEvent event) throws Exception {
        try {
            Stage stage = (Stage) customerLoginBtn.getScene().getWindow();
            Main m = new Main();
            m.changeScene(event,"CustomerLogin.fxml", "Customer Login");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void Manager_Login(ActionEvent event) {
        try {
            Stage stage = (Stage) managerLoginBtn.getScene().getWindow();
            Main m = new Main();
            m.changeScene(event,"ManagerLogin.fxml", "Manager Login");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeWindow.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
}
