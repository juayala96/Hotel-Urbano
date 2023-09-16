package com.julieta.hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Hotel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Hotel.class.getResource("/vistas/principal.fxml"));
        Scene escena = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Login");
        primaryStage.setScene(escena);
        primaryStage.show();

    }
}
