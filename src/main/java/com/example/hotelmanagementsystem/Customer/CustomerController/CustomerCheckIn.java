package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import static com.example.hotelmanagementsystem.Customer.Login.CustomerLogin.currentCustomerNameID;

public class CustomerCheckIn implements Initializable {

    @FXML
    private Label CustomerAddressField;

    @FXML
    private DatePicker CustomerCheckInDate;

    @FXML
    private AnchorPane CustomerCheckInPane;

    @FXML
    private Label CustomerEmailField;

    @FXML
    private Label CustomerIDField;

    @FXML
    private Label CustomerPhoneField;

    @FXML
    private JFXComboBox CustomerRoomChoicebox;

    @FXML
    private Label CustomerNameField;

    @FXML
    private Label roomCapacityField;

    @FXML
    private Label roomPriceField;

    @FXML
    private Label roomTypeField;

    @FXML
    private StackPane rootPane;

    Connection connection = DBConnection.getConnections();

    @FXML
    void CustomerCheckInSubmitBtn(ActionEvent event) throws IOException, SQLException {
        String name = CustomerNameField.getText();
        String id = CustomerIDField.getText();
        String email = CustomerEmailField.getText();
        String phone = CustomerPhoneField.getText();
        String address = CustomerAddressField.getText();
        String room = CustomerRoomChoicebox.getValue() + "";
        String checkdate = CustomerCheckInDate.getValue() + "";
        String capacity = roomCapacityField.getText() + "";
        String type = roomTypeField.getText() + "";
        String price = roomPriceField.getText() + "";


        if (type.equals("") || price.equals("") || capacity.equals("") || checkdate.equals("null")) {
            Main.showJFXAlert(rootPane, CustomerCheckInPane, "warning", "Warning!", "Field Can't be Empty!", JFXDialog.DialogTransition.CENTER);
        } else {
            String sql = "INSERT INTO CHECKINOUTINFO (NAME, ID, EMAIL, PHONE, ADDRESS, ROOMNO, CHECKEDIN, ROOMTYPE, CAPACITY, PRICEDAY) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, id);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, room);
            preparedStatement.setString(7, checkdate);
            preparedStatement.setString(8, type);
            preparedStatement.setString(9, capacity);
            preparedStatement.setString(10, price);

            try {
                preparedStatement.execute();
                String sql1 = "UPDATE ROOMINFO SET STATUS = 'Booked' WHERE ROOM_NO = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, room);
                preparedStatement1.execute();
                Main.showJFXAlert(rootPane, CustomerCheckInPane, "information", "Successful!", "Check In Successful!", JFXDialog.DialogTransition.CENTER);
            } catch (SQLException e) {
                Main.showJFXAlert(rootPane, CustomerCheckInPane, "information", "Error!", "SQL Exception Happened!", JFXDialog.DialogTransition.CENTER);
            } finally {
                DBConnection.closeConnections();
            }
        }
        updateChoiceBox();
        clearTextFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateChoiceBox();
        setAndShowCustomerInfo();
        CustomerRoomChoicebox.setOnAction(this::setRoomInfo);
    }

    public void setRoomInfo(Event event) {
        String room = CustomerRoomChoicebox.getValue()+"";
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()) {
                String sql = "SELECT * FROM ROOMINFO WHERE ROOM_NO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, room);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String roomCapacity = resultSet.getString("CAPACITY");
                    String roomType = resultSet.getString("TYPE");
                    String roomPriceDay = resultSet.getString("PRICE_DAY");

                    roomCapacityField.setText(roomCapacity);
                    roomPriceField.setText(roomPriceDay);
                    roomTypeField.setText(roomType);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    public void updateChoiceBox(){
        List<String> rooms = new ArrayList<String>();
        Connection connection = DBConnection.getConnections();
        try{
            if(!connection.isClosed()) {
                String sql = "SELECT * FROM ROOMINFO WHERE STATUS = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "Available");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    rooms.add(resultSet.getString("ROOM_NO"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
        CustomerRoomChoicebox.getItems().setAll(rooms);
        CustomerRoomChoicebox.setValue(null);
    }

    public void setAndShowCustomerInfo(){
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNameID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    String customerNID = resultSet.getString("NID");
                    String customerEmail = resultSet.getString("EMAIL");
                    String customerPhone = resultSet.getString("PHONE");
                    String customerAddress = resultSet.getString("ADDRESS");

                    CustomerNameField.setText(customerName);
                    CustomerIDField.setText(customerNID);
                    CustomerEmailField.setText(customerEmail);
                    CustomerPhoneField.setText(customerPhone);
                    CustomerAddressField.setText(customerAddress);
                } else {
                    Main.showAlert(Alert.AlertType.ERROR, "ERROR", "SQL connection Error!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    private void clearTextFields() {
        roomTypeField.setText("");
        roomCapacityField.setText("");
        roomPriceField.setText("");
        CustomerCheckInDate.getEditor().clear();

    }
}
