package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Database.TableView.CustomerCheckOutTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import static com.example.hotelmanagementsystem.Customer.Login.CustomerLogin.currentCustomerNameID;


public class CustomerCheckOut extends DBConnection implements Initializable {

    @FXML
    public TextField CustomerCheckOutDetailsSearch;

    @FXML
    public TableView<CustomerCheckOutTable> CustomerCheckOutDetailsTable;

    @FXML
    public TableColumn<CustomerCheckOutTable, String> IDCol;

    @FXML
    public TableColumn<CustomerCheckOutTable, String> RoomCol;

    @FXML
    public TableColumn<CustomerCheckOutTable, String> checkedInCol;

    @FXML
    public TableColumn<CustomerCheckOutTable, String> checkedOutCol;

    @FXML
    public TableColumn<CustomerCheckOutTable, String> priceDay;

    @FXML
    public TableColumn slipCol;

    @FXML
    public TableColumn<CustomerCheckOutTable, String> totalPriceCol;

    private ObservableList<CustomerCheckOutTable> TableRow = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IDCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("ID"));
        RoomCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("Room"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("Checkindate"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("Checkoutdate"));
        priceDay.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("Priceday"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("Totalprice"));
        showcheckInOutDetails();
    }

    public void showcheckInOutDetails(){
        TableRow.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM checkinoutinfo WHERE NID = ? ORDER BY SI_NO DESC";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNameID);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String Id = resultSet.getString("NID");
                    String Room = resultSet.getString("ROOMNO");
                    String CheckedInDate = resultSet.getString("CHECKEDIN");
                    String CheckedOutDate = resultSet.getString("CHECKEDOUT");
                    String PriceDay = resultSet.getString("PRICEDAY");
                    String TotalPrice = resultSet.getString("TOTALPRICE");

                    CustomerCheckOutTable roomTablee = new CustomerCheckOutTable(Id, Room, CheckedInDate, CheckedOutDate, PriceDay, TotalPrice);

                    TableRow.add(roomTablee);
                }
                CustomerCheckOutDetailsTable.getItems().setAll(TableRow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CustomerCheckOutTable> filteredData = new FilteredList<>(TableRow, b -> true);
        CustomerCheckOutDetailsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(search -> { // here search is a object of CustomerRoomTable class
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if (search.getCheckindate().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches
                } else if (search.getRoom().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches
                } else if (search.getPriceday().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches
                } else {
                    return false;
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<CustomerCheckOutTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(CustomerCheckOutDetailsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        CustomerCheckOutDetailsTable.setItems(sortedData);
    }


}
