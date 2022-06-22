module com.example.hotelmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires com.jfoenix;
    requires fontawesomefx;
    requires itextpdf;
    requires java.datatransfer;
    requires java.desktop;

    opens com.example.hotelmanagementsystem to javafx.fxml, javafx.graphics;
    opens com.example.hotelmanagementsystem.Customer.Login to javafx.fxml,javafx.graphics;

}