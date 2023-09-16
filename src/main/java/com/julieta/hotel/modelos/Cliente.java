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

public class Cliente {

    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String tipoCliente;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String apellido, String direccion, String telefono, String email, String tipoCliente) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.tipoCliente = tipoCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    //--------------------------------------------- Leer Usuarios ----------------------------------------------------

    public static ObservableList<Cliente> listaClientes() throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ObservableList<Cliente> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM clientes");
            rs = pstm.executeQuery();

            while (rs.next()){
                lista.add(new Cliente(rs.getString("dni"),rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email"), rs.getString("tipoCliente")));
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

    public void crearCliente(TextField txtDni, TextField txtNombre, TextField txtApellido, TextField txtDireccion, TextField txtTelefono, TextField txtEmail, ComboBox<String> cbTipoCliente) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String consulta = "INSERT INTO clientes(dni,nombre,apellido,direccion,telefono,email,tipoCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1,txtDni.getText());
            pstm.setString(2,txtNombre.getText());
            pstm.setString(3,txtApellido.getText());
            pstm.setString(4,txtDireccion.getText());
            pstm.setString(5,txtTelefono.getText());
            pstm.setString(6,txtEmail.getText());
            pstm.setString(7,cbTipoCliente.getSelectionModel().getSelectedItem());
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


    public void modificarCliente(TextField txtDni, TextField txtNombre, TextField txtApellido, TextField txtDireccion, TextField txtTelefono, TextField txtEmail, ComboBox<String> cbTipoCliente) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String consulta = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, email = ?, tipoCliente = ? WHERE dni = ? ";
            pstm = con.prepareStatement(consulta);

            pstm.setString(1,txtNombre.getText());
            pstm.setString(2,txtApellido.getText());
            pstm.setString(3,txtDireccion.getText());
            pstm.setString(4,txtTelefono.getText());
            pstm.setString(5,txtEmail.getText());
            pstm.setString(6,cbTipoCliente.getSelectionModel().getSelectedItem());
            pstm.setString(7,txtDni.getText());
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


    public void buscarCliente(TextField txtDni, TextField txtNombre, TextField txtApellido, TextField txtDireccion, TextField txtTelefono, TextField txtEmail, ComboBox<String> cbTipoCliente) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        //ObservableList<Usuario> lista = FXCollections.observableArrayList();

        try {
            pstm = con.prepareStatement("SELECT * FROM clientes WHERE dni = ?");
            pstm.setString(1,txtDni.getText());
            rs = pstm.executeQuery();

            if (rs.next()){
                txtNombre.setText(rs.getString("nombre"));
                txtApellido.setText(rs.getString("apellido"));
                txtDireccion.setText(rs.getString("direccion"));
                txtTelefono.setText(rs.getString("telefono"));
                txtEmail.setText(rs.getString("email"));
            } else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setContentText("No existe el cliente con ese DNI");
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

    public void eliminarCliente(TextField txtDni) throws Exception {
        Connection con = Conexion.leerConexion();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            String consulta = "DELETE FROM clientes WHERE dni = ? ";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1,txtDni.getText());
            int resultado = pstm.executeUpdate();
            //insert,update,delete -- executeUpdate
            //select --executeQuery
            if(resultado == 1) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Éxito");
                alerta.setContentText("El cliente fue eliminado");
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

