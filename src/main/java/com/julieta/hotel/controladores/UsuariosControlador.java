package com.julieta.hotel.controladores;

import com.julieta.hotel.Hotel;
import com.julieta.hotel.modelos.Usuario;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuariosControlador {

    //Principal - Usuarios
    @FXML
    private Button btnCargarDatos;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminarPrincipal;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnVolverPrincipal;

    @FXML
    private TableColumn<Usuario, String> colApellido;

    @FXML
    private TableColumn<Usuario, String> colContrasenia;

    @FXML
    private TableColumn<Usuario, String> colEmail;

    @FXML
    private TableColumn<Usuario, String> colLegajo;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colRol;

    @FXML
    private TableColumn<Usuario, String> colUsuario;

    @FXML
    private TableView<Usuario> tablaUsuarios;

    ObservableList<Usuario> listU;
    int index = -1;

    //Crear Usuario
    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<String> cbRol;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtContrasenia;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLegajo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;

    //Modificar Usuario
    @FXML
    private Button btnGuardarModificar;

    //Eliminar Usuario
    @FXML
    private Button btnEliminar;



    // -------------------------------------------- Inicialización ----------------------------------------------
    @FXML
    public void OnBtnCargarDatosClick() throws Exception {
        inicializarTabla();
    }

   /*public void initialize() throws Exception {


        inicializarTabla();

    }*/



    public void inicializarTabla() throws Exception {
        colLegajo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("legajo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Usuario, String>("email"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usuario"));
        colContrasenia.setCellValueFactory(new PropertyValueFactory<Usuario, String>("contrasenia"));
        colRol.setCellValueFactory(new PropertyValueFactory<Usuario, String>("rol"));


        listU = Usuario.listaUsuarios();
        tablaUsuarios.getColumns().setAll(colLegajo, colNombre, colApellido, colEmail, colUsuario, colContrasenia, colRol);
        tablaUsuarios.getItems().setAll(listU);
    }


    @FXML
    public void OnBtnEditarClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/usuarios/modificar-usuario.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Modificar usuarios");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void OnBtnEliminarPrincipalClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/usuarios/eliminar-usuario.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Eliminar usuario");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void OnBtnNuevoClick() throws IOException {


        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/usuarios/crear-usuario.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Nuevo usuario");
        stage.setScene(scene);
        stage.show();

        /*String[] rol = {"admin", "empleado"};
        cbRol.getItems().setAll(rol);
        System.out.println(cbRol);*/

    }

    @FXML
    public void OnBtnVolverPrincipalClick() throws Exception {
        Stage myEscena = (Stage) this.btnVolverPrincipal.getScene().getWindow();
        myEscena.close();
    }


    //Crear usuario - Eventos

    @FXML
    public void OnBtnGuardarClick() throws Exception {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.crearUsuario(txtLegajo,txtNombre,txtApellido,txtEmail,txtUsuario,txtContrasenia,cbRol);
        limpiarCampos();
    }



    @FXML
    void OnBtnVolverClick() throws Exception {
        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }


    //Modificar Usuario - Eventos
    @FXML
    void OnBtnBuscarClick() throws Exception {
        Usuario usuarioEncontrado = new Usuario();
        usuarioEncontrado.buscarUsuario(txtLegajo,txtNombre,txtApellido,txtEmail,txtUsuario,txtContrasenia,cbRol);

    }

    @FXML
    void OnBtnGuardarModificarClick() throws Exception {
        Usuario usuarioModificado = new Usuario();
        usuarioModificado.modificarUsuario(txtLegajo,txtNombre,txtApellido,txtEmail,txtUsuario,txtContrasenia,cbRol);
        limpiarCampos();

    }

    //Eliminar usuario - Eventos
    @FXML
    void OnBtnEliminarClick(ActionEvent event) throws Exception {
        Usuario usuarioEliminado = new Usuario();
        usuarioEliminado.eliminarUsuario(txtLegajo);
        limpiarCampos();

    }
    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCampos(){
        txtLegajo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        cbRol.getSelectionModel().selectFirst();

    }

}

