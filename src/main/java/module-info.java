module com.example.hotelmanagementsystem {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.web;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires com.jfoenix;
    requires itextpdf;
    requires java.datatransfer;
    requires java.desktop;
    requires org.controlsfx.controls;

    opens com.example.hotelmanagementsystem to javafx.fxml, javafx.graphics;
    opens com.example.hotelmanagementsystem.Customer.Login to javafx.fxml,javafx.graphics;
    opens com.example.hotelmanagementsystem.Customer.CustomerController to javafx.fxml, javafx.graphics;
    opens com.example.hotelmanagementsystem.Manager.Login to javafx.fxml, javafx.graphics;
    opens com.example.hotelmanagementsystem.Database.TableView to javafx.base;
    opens com.example.hotelmanagementsystem.Manager.ManagerController to javafx.fxml, javafx.graphics;
}