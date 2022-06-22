package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Database.TableView.CustomerRoomTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerRoomDetails extends DBConnection implements Initializable {

    @FXML
    private Button CustomerRoomSearchBtn;

    @FXML
    private TextField CustomerSearchRoomStatus;

    @FXML
    private TableColumn<CustomerRoomTable, String> price_DayCol;

    @FXML
    private TableColumn<CustomerRoomTable, String> roomCapacityCol;

    @FXML
    private TableColumn<CustomerRoomTable, String> roomNoCol;

    @FXML
    private TableColumn<CustomerRoomTable, String> roomStatusCol;

    @FXML
    private TableView<CustomerRoomTable> roomTable;

    @FXML
    private TableColumn<CustomerRoomTable, String> roomTypeCol;

    private ObservableList<CustomerRoomTable> TableRow = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomNoCol.setCellValueFactory(new PropertyValueFactory<CustomerRoomTable, String>("ROOMNO"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<CustomerRoomTable, String>("TYPE"));
        roomCapacityCol.setCellValueFactory(new PropertyValueFactory<CustomerRoomTable, String>("CAPACITY"));
        price_DayCol.setCellValueFactory(new PropertyValueFactory<CustomerRoomTable, String>("PRICEDAY"));
        roomStatusCol.setCellValueFactory(new PropertyValueFactory<CustomerRoomTable, String>("STATUS"));
        showRoomTable();
    }

    public void showRoomTable(){
        TableRow.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM ROOMINFO ORDER BY STATUS";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String ROOMNO = resultSet.getString("ROOM_NO"); //SQL COL NAMES NID
                    String TYPE = resultSet.getString("TYPE");
                    String CAPACITY = resultSet.getString("CAPACITY");
                    String PRICEDAY = resultSet.getString("PRICE_DAY");
                    String STATUS = resultSet.getString("STATUS");

                    CustomerRoomTable roomTablee = new CustomerRoomTable(ROOMNO, TYPE, CAPACITY, PRICEDAY, STATUS);

                    TableRow.add(roomTablee);
                }
                roomTable.getItems().setAll(TableRow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CustomerRoomTable> filteredData = new FilteredList<>(TableRow, b -> true);
        CustomerSearchRoomStatus.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(search -> { // here search is a object of CustomerRoomTable class
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (search.getRoom().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches Room No.
                } else if (search.getType().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches Room Type.
                } else if (search.getCapacity().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches Room Capacity Column
                } else if (search.getPriceDay().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches Room Price
                } else if(search.getStatus().toLowerCase().indexOf(searchKeyword) != -1){
                    return true; // Matches room status
                } else {
                    return false;
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<CustomerRoomTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(roomTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        roomTable.setItems(sortedData);
        // show data into table view
//        tableView.getItems().setAll(contacts);
        //contacts.clear(); //contacts arraylist empty hobe so that contacts arraylist e future e data add repeat na hoy



    }
}
