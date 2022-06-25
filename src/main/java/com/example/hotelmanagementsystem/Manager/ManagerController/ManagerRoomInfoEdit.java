package com.example.hotelmanagementsystem.Manager.ManagerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerRoomInfoEdit implements Initializable {

    @FXML
    private Button UserConfirm;

    @FXML
    private TextField capacityField;

    @FXML
    private TextField priceDayField;

    @FXML
    private TextField roomField;

    @FXML
    private TextField roomTypeField;

    @FXML
    private JFXComboBox statusCbox;

    private String[] roomStats = {"Available", "Unavailable"};

    @FXML
    void closeBtn(ActionEvent event) {
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveBtn(ActionEvent event) {
        Connection connection = DBConnection.getConnections();
        try {
            if (!connection.isClosed()){
                String sql = "UPDATE RoomInfo SET TYPE = ?, CAPACITY = ?, PRICE_DAY = ?, STATUS = ? where ROOM_NO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomTypeField.getText());
                statement.setString(2, capacityField.getText());
                statement.setString(3, priceDayField.getText());
                statement.setString(4, statusCbox.getValue()+"");
                statement.setString(5, roomField.getText());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBConnection.closeConnections();
        }
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusCbox.getItems().setAll(roomStats);
    }

    public void setRoomInfo(String roomNo, String type, String capacity, String priceDay, String status) {
        roomField.setText(roomNo);
        roomField.setDisable(true);
        roomTypeField.setText(type);
        capacityField.setText(capacity);
        priceDayField.setText(priceDay);
        statusCbox.setValue(status);
    }
}
