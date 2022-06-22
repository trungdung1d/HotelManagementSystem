package com.example.hotelmanagementsystem.Customer.CustomerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Database.TableView.CustomerCheckOutTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        IDCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("nid"));
        RoomCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("roomNo"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("checkedIndate"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("checkedOutDate"));
        priceDay.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("priceDay"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<CustomerCheckOutTable, String>("totalPrice"));

        showcheckInOutDetails();
        slipDownloadBtn();
    }

    public void showcheckInOutDetails(){
        TableRow.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CHECKINOUTINFO WHERE NID = ? ORDER BY SI_NO DESC";
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
                if (search.getCheckedInDate().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches
//                } else if (search..toLowerCase().indexOf(searchKeyword) != -1 ) {
//                    return true; // Filter matches
                } else if (search.getRoom().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches
                } else if (search.getPriceDay().toLowerCase().indexOf(searchKeyword) != -1 ) {
                    return true; // Filter matches
//                } else if(search.getSTATUS().toLowerCase().indexOf(searchKeyword) != -1){
//                    return true;
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

    private void slipDownloadBtn() {
        Callback<TableColumn<CustomerCheckOutTable, String>, TableCell<CustomerCheckOutTable, String>> cellCallback =
                new Callback<TableColumn<CustomerCheckOutTable, String>, TableCell<CustomerCheckOutTable, String>>() {
                    @Override
                    public TableCell<CustomerCheckOutTable, String> call(TableColumn<CustomerCheckOutTable, String> param) {

                        TableCell<CustomerCheckOutTable, String> cell = new TableCell<CustomerCheckOutTable, String>() {

                            FontAwesomeIconView downloadIcon = new FontAwesomeIconView(FontAwesomeIcon.DOWNLOAD);

                            final HBox hBox = new HBox(downloadIcon);
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty){
                                    setGraphic(null);
                                    setText(null);
                                }else{
                                    downloadIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    downloadIcon.setOnMouseEntered((MouseEvent event) ->{
                                        downloadIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    downloadIcon.setOnMouseExited((MouseEvent event2) ->{
                                        downloadIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    downloadIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        downloadIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );

                                        //PDF generate function
                                        CustomerCheckOutTable customerCheckOutTable = getTableView().getItems().get(getIndex());
                                        try {
                                            genaratePdfSlip(customerCheckOutTable);
                                        } catch (DocumentException | IOException e) {
                                            e.printStackTrace();
                                        }

                                    });

//                                    downloadIcon.setOnMouseClicked((MouseEvent event)->{
//
//                                    });

                                    hBox.setStyle("-fx-alignment:center");
//                                    HBox.setMargin(download, new Insets(2, 7, 0, 2));
//                                    HBox.setMargin(download, new Insets(2, 2, 0, 7));
                                    setGraphic(hBox);
                                }
                            }
                        };
                        return cell;
                    }
                };
        slipCol.setCellFactory(cellCallback);
    }

    private void genaratePdfSlip(CustomerCheckOutTable customerCheckOutTable) throws IOException, DocumentException {

        File currentDirFile = new File("");
        String pathFinder = currentDirFile.getAbsolutePath();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathFinder+"/tempSlip.pdf"));
        document.open();

        String title = "Hotel Management System Slip\n\n";
        String nid = "Customer NID: "+customerCheckOutTable.getID()+"\n";
        String roomNo = "Room No: "+customerCheckOutTable.getRoom()+"\n";
        String checkedIn = "Checked In Date: "+customerCheckOutTable.getCheckedInDate()+"\n";
        String checkedOut = "Checked Out Date: "+customerCheckOutTable.getCheckedOutDate()+"\n";
        String priceDay = "Price per day: "+customerCheckOutTable.getPriceDay()+" taka\n";
        String totalBill = "Total Bill: "+customerCheckOutTable.getTotalPrice()+" taka\n";
        String totalParagraph = title+nid+roomNo+checkedIn+checkedOut+priceDay+totalBill;

        Paragraph para = new Paragraph(totalParagraph);

        document.add(para);
        document.close();

        File file = new File(pathFinder+"/tempSlip.pdf");
        if(file.exists()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("File Doesn't Exists");
        }
    }
}
