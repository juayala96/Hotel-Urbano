package com.julieta.hotel.modelos;

import com.julieta.hotel.conexion.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {

    private String legajo;
    private String nombre;
    private String apellido;
    private String email;
    private String usuario;
    private String contrasenia;
    private String rol;

    public Usuario() {
    }

    public Usuario(String legajo, String nombre, String apellido, String email, String usuario, String contrasenia, String rol) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }





//--------------------------------------------- Leer Usuarios ----------------------------------------------------

    public static ObservableList<Usuario> listaUsuarios() throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<Usuario> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM usuarios");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new Usuario(rs.getString("legajo"),rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("usuario"), rs.getString("contrasenia"), rs.getString("rol")));
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                System.err.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    //-----------------------Crear usuario----------------------

    public void crearUsuario(TextField txtLegajo, TextField txtNombre, TextField txtApellido, TextField txtEmail, TextField txtUsuario, TextField txtContrasenia, ComboBox<String> cbRol) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String consulta = "INSERT INTO usuarios(legajo,nombre,apellido,email,usuario,contrasenia,rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1,txtLegajo.getText());
            pstm.setString(2,txtNombre.getText());
            pstm.setString(3,txtApellido.getText());
            pstm.setString(4,txtEmail.getText());
            pstm.setString(5,txtUsuario.getText());
            pstm.setString(6,txtContrasenia.getText());
            pstm.setString(7,cbRol.getSelectionModel().getSelectedItem());
            int resultado = pstm.executeUpdate();
            //insert,update,delete -- executeUpdate
            //select --executeQuery
            if(resultado == 1) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Éxito");
                alerta.setContentText("Los datos se han guardado correctamente");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        } finally {
            try {
                if( rs != null) rs.close();
                if( pstm != null) pstm.close();
                if( con != null) con.close();
            } catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }
    }


    public void modificarUsuario(TextField txtLegajo, TextField txtNombre, TextField txtApellido, TextField txtEmail, TextField txtUsuario, TextField txtContrasenia, ComboBox<String> cbRol) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String consulta = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, usuario = ?, contrasenia = ?, rol = ? WHERE legajo = ? ";
            pstm = con.prepareStatement(consulta);

            pstm.setString(1,txtNombre.getText());
            pstm.setString(2,txtApellido.getText());
            pstm.setString(3,txtEmail.getText());
            pstm.setString(4,txtUsuario.getText());
            pstm.setString(5,txtContrasenia.getText());
            pstm.setString(6,cbRol.getSelectionModel().getSelectedItem());
            pstm.setString(7,txtLegajo.getText());
            int resultado = pstm.executeUpdate();

            //insert,update,delete -- executeUpdate
            //select --executeQuery
            if(resultado == 1) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Éxito");
                alerta.setContentText("Los datos se han guardado correctamente");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        } finally {
            try {
                if( rs != null) rs.close();
                if( pstm != null) pstm.close();
                if( con != null) con.close();
            } catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    //-----------Buscar-------------


    public void buscarUsuario(TextField txtLegajo, TextField txtNombre, TextField txtApellido, TextField txtEmail, TextField txtUsuario, TextField txtContrasenia, ComboBox<String> cbRol) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        //ObservableList<Usuario> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM usuarios WHERE legajo = ?");
            pstm.setString(1,txtLegajo.getText());
            rs = pstm.executeQuery();

            if (rs.next()){
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellido"));
                txtEmail.setText(rs.getString("email"));
                txtUsuario.setText(rs.getString("usuario"));
                txtContrasenia.setText(rs.getString("contrasenia"));
            } else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("No existe el usuario con ese legajo");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (Exception ex){
                System.err.println("Error: " + ex.getMessage());
            }
        }

    }

    public void eliminarUsuario(TextField txtLegajo) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String consulta = "DELETE FROM usuarios WHERE legajo = ? ";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1,txtLegajo.getText());
            int resultado = pstm.executeUpdate();
            //insert,update,delete -- executeUpdate
            //select --executeQuery
            if(resultado == 1) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Éxito");
                alerta.setContentText("El usuario fue eliminado");
                alerta.showAndWait();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        } finally {
            try {
                if( rs != null) rs.close();
                if( pstm != null) pstm.close();
                if( con != null) con.close();
            } catch (Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
