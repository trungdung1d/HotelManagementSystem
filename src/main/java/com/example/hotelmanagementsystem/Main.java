package com.example.hotelmanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;


public class Main extends Application {
    public static Stage stage;
    public static double x, y;
    public static double xxx, yyy;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("Hotel Management System");
            primaryStage.setScene(new Scene(root, 600, 400));

            primaryStage.setX(140);
            primaryStage.setY(130);
            x = primaryStage.getX();
            y = primaryStage.getY();
            root.setOnMousePressed(event -> {
                xxx = event.getSceneX();
                yyy = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xxx);
                primaryStage.setY(event.getScreenY() - yyy);
                x = primaryStage.getX();
                y = primaryStage.getY();
            });
            primaryStage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void showJFXAlert(StackPane rootPane, AnchorPane rootAnchorPane, String type, String heading, String message, JFXDialog.DialogTransition transition){
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setMaxWidth(230);
        layout.setMaxHeight(100);

        JFXButton okayBtn = new JFXButton("Okay");
        okayBtn.setStyle("-fx-background-color:#4A4A4A; -fx-text-fill: white; -fx-border-color: white");
        layout.getStylesheets().add("/CSS/dialogPane.css");
        JFXDialog dialog = new JFXDialog(rootPane, layout, transition);
        okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
            dialog.close();
        });
        if(type.toLowerCase(Locale.ROOT).equals("information")) {
            layout.setHeading(new Label("✅  "+heading));
        } else if(type.toLowerCase(Locale.ROOT).equals("warning")) {
            layout.setHeading(new Label("💢  "+heading));
        } else if(type.toLowerCase(Locale.ROOT).equals("error")) {
            layout.setHeading(new Label("❌ "+heading));
        } else {
            layout.setHeading(new Label("⚠ "+heading));
        }
        layout.setBody(new Label(""+message));
        layout.setActions(okayBtn);
        layout.setPadding(new Insets(0, 15, 13, 0));
        dialog.show();

        BoxBlur blur = new BoxBlur(5, 5, 4);
        rootAnchorPane.setEffect(blur);
        dialog.setOnDialogClosed((JFXDialogEvent event) -> {
            rootAnchorPane.setEffect(null);
        });
    }


    public void changeScene(String url, String title) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource(url));
            stage.setTitle(title);
            stage.getScene().setRoot(root);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void showAlert(Alert.AlertType type, String title, String header){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}