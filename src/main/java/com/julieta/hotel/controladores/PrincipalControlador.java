package com.julieta.hotel.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class PrincipalControlador {

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnHabitaciones;

    @FXML
    private Button btnReservas;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnCerrarSesion;
    //@FXML
    //void OnBtnCerrarSesionClick() {

    //}

    @FXML
    void OnBtnClientesClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/clientes/clientes.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Clientes");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnBtnHabitacionesClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/habitaciones/habitaciones.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Habitaciones");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnBtnReservasClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/reservas/reservas.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Reservas");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnBtnUsuariosClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/usuarios/usuarios.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Usuarios");
        stage.setScene(scene);
        stage.show();


    }

    //------------------------------------------ Cerrar Sesión ----------------------------------------------------
    @FXML
    private void OnBtnCerrarSesionClick() throws IOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setContentText("¿Desea Cerrar Sesión?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK){
            closeWindowsLoginPrincipal();
        }
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsLoginPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/login.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Iniciar Sesion");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnCerrarSesion.getScene().getWindow();
        myEscena.close();
    }


}
