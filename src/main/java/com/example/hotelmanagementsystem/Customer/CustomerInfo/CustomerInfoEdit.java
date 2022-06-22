package com.example.hotelmanagementsystem.Customer.CustomerInfo;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.hotelmanagementsystem.Customer.Login.CustomerLogin.currentCustomerNameID;

public class CustomerInfoEdit implements Initializable {

    @FXML
    private JFXTextArea CustomerAddressEdit;

    @FXML
    private Button CustomerConfirm;

    @FXML
    private TextField CustomerEmailEdit;

    @FXML
    private TextField CustomerIdEdit;

    @FXML
    private TextField CustomerNameEdit;

    @FXML
    private TextField CustomerPassEdit;

    @FXML
    private TextField CustomerPhoneEdit;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private StackPane rootPane;

    public static boolean editedFlag = false;

    @FXML
    void BackBtn(ActionEvent event) throws IOException {
//        CommonTask.pageNavigation("/com.example.hotelmanagementsystem/CustomerInfo.fxml", (Stage) CustomerConfirm.getScene().getWindow(),this.getClass(),"Customer Home", 550, 400);

    }

    @FXML
    void CustomerConfirmEdit(ActionEvent event) throws IOException, SQLException {
        Connection connection = DBConnection.getConnections();
        String customerName = CustomerNameEdit.getText();
        String customerID = CustomerIdEdit.getText();
        String customerPassword = CustomerPassEdit.getText();
        String customerEmail = CustomerEmailEdit.getText();
        String customerPhone = CustomerPhoneEdit.getText();
        String customerAddress = CustomerAddressEdit.getText();
        if (customerName.isEmpty() || customerID.isEmpty() || customerPassword.isEmpty() || customerEmail.isEmpty() || customerAddress.isEmpty() || customerPhone.isEmpty()) {
            Main.showJFXAlert(rootPane, rootAnchorPane, "warning", "Warning!", "Text field can't be empty!", JFXDialog.DialogTransition.CENTER);
        } else {
            String sql = "UPDATE CUSTOMERINFO SET NAME = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE NID = ?";
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sql);
            preparedStatementUpdate.setString(1, customerName);
            preparedStatementUpdate.setString(2, customerPassword);
            preparedStatementUpdate.setString(3, customerEmail);
            preparedStatementUpdate.setString(4, customerPhone);
            preparedStatementUpdate.setString(5, customerAddress);
            preparedStatementUpdate.setString(6, currentCustomerNameID);
            try {
                preparedStatementUpdate.execute();
//                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Update Successful!");]
                editedFlag = true;
//                CommonTask.pageNavigation("/com.example.hotelmanagement/CustomerInfo.fxml", (Stage) CustomerConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);

            } catch (SQLException e){
                Main.showJFXAlert(rootPane, rootAnchorPane, "ERROR", "ERROR!", "Connection Problem!", JFXDialog.DialogTransition.CENTER);
            } finally {
                DBConnection.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerInfo();
    }

    public void setCustomerInfo(){
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM customerinfo WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNameID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerNameSet = resultSet.getString("NAME");
                    String customerNIDSet = resultSet.getString("NID");
                    String customerEmailSet = resultSet.getString("EMAIL");
                    String customerPhoneSet = resultSet.getString("PHONE");
                    String customerPasswordSet = resultSet.getString("PASSWORD");
                    String customerAddressSet = resultSet.getString("ADDRESS");

                    CustomerNameEdit.setText(customerNameSet);
                    CustomerIdEdit.setText(customerNIDSet);
                    CustomerIdEdit.setDisable(true);
                    CustomerEmailEdit.setText(customerEmailSet);
                    CustomerPhoneEdit.setText(customerPhoneSet);
                    CustomerPassEdit.setText(customerPasswordSet);
                    CustomerAddressEdit.setText(customerAddressSet);
                } else {
                    Main.showJFXAlert(rootPane, rootAnchorPane, "ERROR", "ERROR!", "Connection Problem!", JFXDialog.DialogTransition.CENTER);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }
}
