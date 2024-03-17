package org.example.finalclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClockApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClockApplication.class.getResource("clock-view.fxml"));
        Parent root = fxmlLoader.load();
        ClockController clock = fxmlLoader.getController();
        clock.initialize();
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("CLOCK!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}