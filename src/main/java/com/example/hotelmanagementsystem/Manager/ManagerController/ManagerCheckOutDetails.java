package com.example.hotelmanagementsystem.Manager.ManagerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Database.TableView.ManagerCheckOutDetailsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerCheckOutDetails implements Initializable {

    @FXML
    private TableView<ManagerCheckOutDetailsTable> checkInoutTable;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> checkedInCol;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> checkedOutCol;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> idCol;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> priceDayCol;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> roomNoCol;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> roomTypeCol;

    @FXML
    private TableColumn<ManagerCheckOutDetailsTable, String> totalPriceCol;

    private ObservableList<ManagerCheckOutDetailsTable> TableRow = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("ID"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("Room"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("Type"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("Checkedin"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("Checkedout"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("Priceday"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutDetailsTable, String>("Totalprice"));
        showCheckInOutInfo();
    }

    public void showCheckInOutInfo() {
        TableRow.clear();
        Connection connection = DBConnection.getConnections();
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM CHECKINOUTINFO ORDER BY SI_NO DESC";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String ROOM = resultSet.getString("ROOMNO"); //SQL COL NAMES NID
                    String TYPE = resultSet.getString("ROOMTYPE");
                    String CAPACITY = resultSet.getString("CAPACITY");
                    String PRICEDAY = resultSet.getString("PRICEDAY");
                    String TOTALPRICE = resultSet.getString("TOTALPRICE");
                    String CHECKEDIN = resultSet.getString("CHECKEDIN");
                    String CHECKEDOUT = resultSet.getString("CHECKEDOUT");
                    String ID = resultSet.getString("NID");
                    ManagerCheckOutDetailsTable roomTablee = new ManagerCheckOutDetailsTable(ID, ROOM, TYPE, CAPACITY, PRICEDAY, TOTALPRICE, CHECKEDIN, CHECKEDOUT);

                    TableRow.add(roomTablee);
                }
                checkInoutTable.getItems().setAll(TableRow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }
}