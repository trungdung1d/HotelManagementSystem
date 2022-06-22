package com.example.hotelmanagementsystem.Customer.CustomerInfo;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static com.example.hotelmanagementsystem.Customer.CustomerInfo.CustomerInfoEdit.editedFlag;
import static com.example.hotelmanagementsystem.Customer.Login.CustomerLogin.currentCustomerNameID;

public class CustomerInfo implements Initializable {

//    public Button CustomerBackToHome;
//    public StackPane rootPane;
//    public AnchorPane rootAnchorPane;

    @FXML
    private Label CustomerAddressLabel;

    @FXML
    private Button CustomerBackToHome;

    @FXML
    private Label CustomerEmailLabel;

    @FXML
    private Label CustomerIDlabel;

    @FXML
    private Label CustomerNameLabel;

    @FXML
    private Label CustomerPasswordLabel;

    @FXML
    private Label CustomerPhoneLabel;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private StackPane rootPane;

    @FXML
    void CustomerBackToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) CustomerBackToHome.getScene().getWindow();
        stage.close();
    }

    @FXML
    void CustomerInfoEdit(ActionEvent event) throws IOException {
        editedFlag = false;
//        CommonTask.pageNavigation("/com.example.hotelmanagementsystem/CustomerInfoEdit.fxml", (Stage) CustomerBackToHome.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCustomerInfo();
        if (editedFlag == true) {
            Main.showJFXAlert(rootPane, rootAnchorPane, "information", "Updated!", "Successfully Updated!", JFXDialog.DialogTransition.CENTER);

        }
    }

    public void showCustomerInfo(){
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM customerinfo WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNameID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    String customerID = resultSet.getString("NID");
                    String customerEmail = resultSet.getString("EMAIL");
                    String customerPhone = resultSet.getString("PHONE");
                    String customerPassword = resultSet.getString("PASSWORD");
                    String customerAddress = resultSet.getString("ADDRESS");

                    CustomerNameLabel.setText(customerName);
                    CustomerIDlabel.setText(customerID);
                    CustomerEmailLabel.setText(customerEmail);
                    CustomerPhoneLabel.setText(customerPhone);
                    CustomerPasswordLabel.setText(customerPassword);
                    CustomerAddressLabel.setText(customerAddress);
                } else {
                    Main.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }
}
