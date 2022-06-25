package com.example.hotelmanagementsystem.Manager.ManagerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Database.TableView.ManagerCheckInDetailsTable;
import com.example.hotelmanagementsystem.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ManagerCheckOut implements Initializable {

    @FXML
    private TableColumn<ManagerCheckInDetailsTable, String> addressCol;

    @FXML
    private TableView<ManagerCheckInDetailsTable> checkInInfoTable;

    @FXML
    private DatePicker checkOutDatepicker;

    @FXML
    private TableColumn<ManagerCheckInDetailsTable, String> checkedInCol;

    @FXML
    private Label checkedInField;

    @FXML
    private Label daysTotalField;

    @FXML
    private TableColumn<ManagerCheckInDetailsTable, String> idNoCol;

    @FXML
    private Label idNoField;

    @FXML
    private TableColumn<ManagerCheckInDetailsTable, String> nameCol;

    @FXML
    private Label nameField;

    @FXML
    private TableColumn<ManagerCheckInDetailsTable, String> priceDayCol;

    @FXML
    private Label priceDayField;

    @FXML
    private TableColumn<ManagerCheckInDetailsTable, String> roomNoCol;

    @FXML
    private Label roomNoField;

    @FXML
    private Label totalPriceField;

    private ObservableList<ManagerCheckInDetailsTable> TableRow = FXCollections.observableArrayList();


    @FXML
    void checkOutBtn(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getConnections();
        String checkOutDate = checkOutDatepicker.getValue() + "";
        String daysTotal = daysTotalField.getText();
        String totalPrice = totalPriceField.getText();
        String roomNo = roomNoField.getText();
        String siNo = idNoField.getText();
        if (siNo.equals("") || checkOutDate.equals("null") || daysTotal.isEmpty() || totalPrice.isEmpty()) {
            Main.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "UPDATE CHECKINOUTINFO SET CHECKEDOUT = ?, TOTALDAYS = ?, TOTALPRICE = ? WHERE SI_NO = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, checkOutDate);
            preparedStatement.setString(2, daysTotal);
            preparedStatement.setString(3, totalPrice);
            preparedStatement.setString(4, siNo);
            try {
                preparedStatement.execute();
                Main.showAlert(Alert.AlertType.INFORMATION, "Successful", "Check-Out Successful!");
                String sql1 = "UPDATE ROOMINFO SET STATUS = ? WHERE ROOM_NO = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, "Available");
                preparedStatement1.setString(2, roomNo);
                preparedStatement1.execute();
                showCheckedInTable();
            } catch (SQLException e) {
                Main.showAlert(Alert.AlertType.ERROR, "Error", "Exception detected. Probably Sql!");
            } finally {
                DBConnection.closeConnections();
            }
        }
        clearTextFields();
    }

    @FXML
    void onCheckOutPick(ActionEvent event) throws ParseException {
        LocalDate myDate = checkOutDatepicker.getValue();
        String checkInDate = checkedInField.getText();
        if(!checkInDate.isEmpty()) {
            String checkOutDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long days = (dayDifference(checkOutDate, checkInDate))+1;
            daysTotalField.setText(days+"");
            String priceDay = priceDayField.getText();
            boolean isNumeric = priceDay.chars().allMatch(Character::isDigit);
            if(isNumeric) {
                long pricePerDay = Long.parseLong(priceDay);
                long totalPrice = pricePerDay * days;
                totalPriceField.setText(totalPrice+"");
            }
        } else {
            Main.showAlert(Alert.AlertType.WARNING, "Warning", "Checked-In Date is empty!");
        }
    }

    public int selectIndex = -1;
    @FXML
    void onTableRowSelect(MouseEvent event) {
        selectIndex = checkInInfoTable.getSelectionModel().getSelectedIndex();
        if(selectIndex <= -1){
            return;
        }
        checkedInField.setText(checkedInCol.getCellData(selectIndex).toString());
        nameField.setText(nameCol.getCellData(selectIndex).toString());
        priceDayField.setText(priceDayCol.getCellData(selectIndex).toString());
        roomNoField.setText(roomNoCol.getCellData(selectIndex).toString());
        idNoField.setText(idNoCol.getCellData(selectIndex).toString());
    }

    private long dayDifference(String checkOut, String checkIn) throws ParseException {
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = obj.parse(checkIn);
        Date date2 = obj.parse(checkOut);
        long time_difference = date2.getTime() - date1.getTime();
        long days_difference = (time_difference / (1000*60*60*24)) % 365;
        return days_difference;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableRow.clear();
        nameCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("Name"));
        idNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("ID"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("Room"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("Priceday"));
        addressCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("Address"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckInDetailsTable, String>("Checkedin"));

        showCheckedInTable();
    }

    private void showCheckedInTable() {
        TableRow.clear();
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM checkinoutinfo WHERE CHECKEDOUT IS NULL ORDER BY SI_NO DESC";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String ID = resultSet.getString("SI_NO"); //SQL COL NAMES NID
                    String NAME = resultSet.getString("NAME");
                    String ROOMNO = resultSet.getString("ROOMNO");
                    String PRICEDAY = resultSet.getString("PRICEDAY");
                    String CHECKEDIN = resultSet.getString("CHECKEDIN");
                    String ADDRESS = resultSet.getString("ADDRESS");

                    ManagerCheckInDetailsTable checkInTable = new ManagerCheckInDetailsTable(ID, NAME, ROOMNO, PRICEDAY, CHECKEDIN, ADDRESS);

                    TableRow.add(checkInTable);
                }
                checkInInfoTable.getItems().setAll(TableRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    private void clearTextFields() {
        idNoField.setText("");
        nameField.setText("");
        checkedInField.setText("");
        checkOutDatepicker.getEditor().clear();
        roomNoField.setText("");
        priceDayField.setText("");
        daysTotalField.setText("");
        totalPriceField.setText("");
    }
}
