package com.julieta.hotel.controladores;

import com.julieta.hotel.modelos.LoginUsuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorLogin {
    public ControladorLogin(){}

    @FXML
    private TextField campoNombre;
    @FXML
    private PasswordField campoPassword;
    @FXML
    private Button btnAceptar;
    @FXML
    private Label labID;

    //------------------------------------------- Login Botón (Aceptar) -------------------------------------------------
    @FXML
    private void aceptarLogin(ActionEvent event) throws Exception {
        TextField[] campoNomb = {campoNombre};
        PasswordField[] campoPass = {campoPassword};
        Label[] id = {labID};

        if (comprobarValoresNombre(campoNomb) && comprobarValoresPassword(campoPass)){
            LoginUsuario usuarioLogin = new LoginUsuario();
            usuarioLogin.loginUsuario(campoNombre, campoPassword, labID);
            if(comprobarID(id)){
                labID.setText("");
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/principal.fxml"));
                AnchorPane root = loader.load();
                Scene escena = new Scene(root);
                Stage escenario = new Stage();
                escenario.setTitle("Hotel Urbano");
                escenario.setScene(escena);
                escenario.show();


                Stage myEscena = (Stage) this.btnAceptar.getScene().getWindow();
                myEscena.close();


            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Los datos ingresados son incorrectos");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Debe completar todos los datos");
            alerta.showAndWait();
            campoNombre.requestFocus();
        }
    }

    //------------------------------------------- Login Botón (Salir) -------------------------------------------------
    @FXML
    private void salirLogin(){
        Platform.exit();
    }

    //----------------------------------Comprobar los campos que no estén vacíos------------------------------------
    private boolean comprobarValoresNombre(TextField[] campoNomb){
        boolean valido = true;
        for (int i = 0; i < campoNomb.length; i++) {
            String valor = campoNomb[i].getText();
            if(valor == null || valor.trim().isEmpty() || campoNombre.getLength() < 4){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresPassword(PasswordField[] campoPass){
        boolean valido = true;
        for (int i = 0; i < campoPass.length; i++) {
            String valor = campoPass[i].getText();
            if(valor == null || valor.trim().isEmpty() || campoPassword.getLength() < 4){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarID(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
    }
//----------------------------------------------------------------------------------------------------------
}
