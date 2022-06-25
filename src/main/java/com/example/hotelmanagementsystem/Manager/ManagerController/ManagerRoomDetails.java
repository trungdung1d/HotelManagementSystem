package com.example.hotelmanagementsystem.Manager.ManagerController;

import com.example.hotelmanagementsystem.Database.DBConnection;
import com.example.hotelmanagementsystem.Database.TableView.ManagerRoomTable;
import com.example.hotelmanagementsystem.Main;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import org.controlsfx.glyphfont.FontAwesome;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.hotelmanagementsystem.Main.xxx;
import static com.example.hotelmanagementsystem.Main.yyy;

public class ManagerRoomDetails implements Initializable {

    @FXML
    private TableColumn actionCol;

    @FXML
    private JFXTextField bedCapacityField;

    @FXML
    private TableColumn<ManagerRoomTable, String> price_DayCol;

    @FXML
    private JFXTextField price_dayField;

    @FXML
    private TableColumn<ManagerRoomTable, String> roomCapacityCol;

    @FXML
    private TableColumn<ManagerRoomTable, String> roomCol;

    @FXML
    private JFXTextField roomNoField;

    @FXML
    private JFXComboBox roomStatusChoiceBox;

    @FXML
    private TableColumn<ManagerRoomTable, String> roomStatusCol;

    @FXML
    private TableView<ManagerRoomTable> roomTable;

    @FXML
    private TableColumn<ManagerRoomTable, String> roomTypeCol;

    @FXML
    private JFXTextField roomTypeField;

    private String[] roomStats = {"Available", "Unavailable"};

    private ObservableList<ManagerRoomTable> TableRow = FXCollections.observableArrayList();

    @FXML
    void addRoom(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getConnections();
        String roomNo = roomNoField.getText();
        String bedCapacity = bedCapacityField.getText();
        String roomType = roomTypeField.getText();
        String price_day = price_dayField.getText();
        String roomStatus = roomStatusChoiceBox.getValue()+"";

        if (roomNo.isEmpty() || bedCapacity.isEmpty()  || roomType.isEmpty() || price_day.isEmpty() || roomStatus.equals("null")) {
            Main.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
        } else {
            String sql = "INSERT INTO roominfo (ROOM_NO, TYPE, CAPACITY, PRICE_DAY, STATUS) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomNo);
            preparedStatement.setString(2, roomType);
            preparedStatement.setString(3, bedCapacity);
            preparedStatement.setString(4, price_day);
            preparedStatement.setString(5, roomStatus);
            try{
                preparedStatement.execute();
                Main.showAlert(Alert.AlertType.INFORMATION, "Successful", "Room Added Successfully!");
                showRoomTable();
            } catch (SQLException e){
                Main.showAlert(Alert.AlertType.ERROR, "Error", "This Room no. already exists!");
            } finally {
                DBConnection.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomStatusChoiceBox.getItems().setAll(roomStats);
        roomCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("Room"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("Type"));
        roomCapacityCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("Capacity"));
        price_DayCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("Priceday"));
        roomStatusCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("Status"));
        showRoomTable();
        actionButtons();
    }

    public void showRoomTable(){
        TableRow.clear();
        Connection connection = DBConnection.getConnections();
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

                    ManagerRoomTable roomTablee = new ManagerRoomTable(ROOMNO, TYPE, CAPACITY, PRICEDAY, STATUS);

                    TableRow.add(roomTablee);
                }
                roomTable.getItems().setAll(TableRow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    private void actionButtons() {
        Callback<TableColumn<ManagerRoomTable, String>, TableCell<ManagerRoomTable, String>> cellCallback =
                new Callback<TableColumn<ManagerRoomTable, String>, TableCell<ManagerRoomTable, String>>() {
                    @Override
                    public TableCell<ManagerRoomTable, String> call(TableColumn<ManagerRoomTable, String> param) {

                        TableCell<ManagerRoomTable, String> cell = new TableCell<ManagerRoomTable, String>() {

                            FontAwesome.Glyph deleteIcon = FontAwesome.Glyph.TRASH;
                            FontAwesome.Glyph editIcon = FontAwesome.Glyph.EDIT;
//                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

                            public HBox hBox = new HBox(25, editIcon, deleteIcon);

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty){
                                    setGraphic(null);
                                    setText(null);
                                }else{

                                    deleteIcon.(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    deleteIcon.setOnMouseEntered((MouseEvent event) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    deleteIcon.setOnMouseExited((MouseEvent event2) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    deleteIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );

                                        ManagerRoomTable managerRoomTable = getTableView().getItems().get(getIndex());
                                        tableRowDelete(managerRoomTable);

                                    });

                                    editIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    editIcon.setOnMouseEntered((MouseEvent event) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    editIcon.setOnMouseExited((MouseEvent event2) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    editIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );
                                        ManagerRoomTable managerRoomTable = getTableView().getItems().get(getIndex());
//                                        System.out.println(managerRoomTable.getROOMNO());
                                        try {
                                            editTableRowInfo(managerRoomTable);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    });



                                    hBox.setStyle("-fx-alignment:center");
//                                    hBox.setMaxWidth(40);
//                                    HBox.setMargin(editIcon, new Insets(2, 10, 0, 10));
//                                    HBox.setMargin(deleteIcon, new Insets(2, 10, 0, 10));
                                    setGraphic(hBox);
                                }
                            }
                        };

                        return cell;
                    }
                };
        actionCol.setCellFactory(cellCallback);
    }

    public void tableRowDelete(ManagerRoomTable managerRoomTable) {
        String roomStatus = managerRoomTable.getStatus();
        if (!roomStatus.equals("Booked")) {
            Connection connection = DBConnection.getConnections();
            try {
                if (!connection.isClosed()) {
                    String sql = "DELETE FROM RoomInfo where ROOM_NO=?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, managerRoomTable.getRoom());

                    statement.execute();

                    Main.showAlert(Alert.AlertType.INFORMATION, "Delete Operation Successfull", "Room No " + managerRoomTable.getRoom() + " is deleted from database!");

                    roomTable.getItems().remove(managerRoomTable);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DBConnection.closeConnections();
            }
        } else {
            Main.showAlert(Alert.AlertType.WARNING, "Delete Unsuccessful", "Can't delete. It's currently booked by a customer.");
        }
    }

    public void editTableRowInfo(ManagerRoomTable managerRoomTable) throws IOException {
        if (!(managerRoomTable.getStatus()).equals("Booked")) {
            Connection connection = DBConnection.getConnections();
            try {
                if (!connection.isClosed()) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getClassLoader().getResource("/com.example.hotelmanagementsystem/ManagerRoomInfoEdit.fxml"));
                    Parent viewContact = loader.load();
                    Scene scene = new Scene(viewContact);
                    // update information
                    ManagerRoomInfoEdit roomInfoEdit = loader.getController();
                    roomInfoEdit.setRoomInfo(managerRoomTable.getRoom(), managerRoomTable.getType(), managerRoomTable.getCapacity(), managerRoomTable.getPriceday(), managerRoomTable.getStatus());
                    Stage window = new Stage();
                    window.setScene(scene);
                    window.initStyle(StageStyle.UNDECORATED);

                    stagePosition(window, viewContact);

                    window.showAndWait();
                    showRoomTable();
                }

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            } finally {
                DBConnection.closeConnections();
            }
        } else {
            Main.showAlert(Alert.AlertType.WARNING, "Can't Edit!", " Currently booked by a customer.");
        }
    }

    public void stagePosition(Stage primaryStage, Parent root) {
        AtomicReference<Double> x = new AtomicReference<>(primaryStage.getX());
        AtomicReference<Double> y = new AtomicReference<>(primaryStage.getY());
        root.setOnMousePressed(event -> {
            xxx = event.getSceneX();
            yyy = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
//            if(event.getButton() == MouseButton.SECONDARY) {
            primaryStage.setX(event.getScreenX() - xxx);
            primaryStage.setY(event.getScreenY() - yyy);
            x.set(primaryStage.getX());
            y.set(primaryStage.getY());
//            }
        });
    }

}
