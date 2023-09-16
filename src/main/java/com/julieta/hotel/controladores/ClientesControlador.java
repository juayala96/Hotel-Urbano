package com.julieta.hotel.controladores;

import com.julieta.hotel.modelos.Cliente;
import com.julieta.hotel.modelos.Usuario;
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

public class ClientesControlador {

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
    private TableColumn<Cliente, String> colApellido;

    @FXML
    private TableColumn<Cliente, Integer> colDNI;

    @FXML
    private TableColumn<Cliente, String> colDireccion;

    @FXML
    private TableColumn<Cliente, String> colEmail;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colTipo;

    @FXML
    private TableView<Cliente> tablaClientes;

    ObservableList<Cliente> listC;
    int index = -1;

    //Crear cliente
    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnVolver;

    @FXML
    private ComboBox<String> cbTipoCliente;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    //Modificar cliente
    @FXML
    private Button btnGuardarModificar;


    //Eliminar cliente

    @FXML
    private Button btnEliminar;



    // -------------------------------------------- Inicialización ----------------------------------------------
    @FXML
    public void OnBtnCargarDatosClick() throws Exception {
        inicializarTabla();
    }

   /*public void initialize(){

        ObservableList<String> items = FXCollections.observableArrayList();
        items.add("admin");
        cbRol.setItems(items);

    }*/



    public void inicializarTabla() throws Exception {
        colDNI.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tipoCliente"));


        listC = Cliente.listaClientes();
        tablaClientes.getColumns().setAll(colDNI, colNombre, colApellido, colDireccion, colTelefono, colEmail,colTipo);
        tablaClientes.getItems().setAll(listC);
    }


    @FXML
    public void OnBtnEditarClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/clientes/modificar-cliente.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Modificar cliente");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void OnBtnEliminarPrincipalClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/clientes/eliminar-cliente.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Eliminar cliente");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void OnBtnNuevoClick() throws IOException {
        //ObservableList<String> items = FXCollections.observableArrayList();
        //items.add("admin");
        //cbRol.setItems(items);

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/clientes/crear-cliente.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Nuevo cliente");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void OnBtnVolverPrincipalClick() throws Exception {
        Stage myEscena = (Stage) this.btnVolverPrincipal.getScene().getWindow();
        myEscena.close();
    }



    // Nuevo cliente - Eventos
    @FXML
    public void OnBtnGuardarClick() throws Exception {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.crearCliente(txtDni,txtNombre,txtApellido,txtDireccion, txtTelefono, txtEmail, cbTipoCliente);
        limpiarCampos();
    }



    @FXML
    void OnBtnVolverClick() throws Exception {
        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }

    //Modificar cliente - Eventos
    @FXML
    void OnBtnBuscarClick() throws Exception {
        Cliente clienteEncontrado = new Cliente();
        clienteEncontrado.buscarCliente(txtDni,txtNombre,txtApellido,txtDireccion, txtTelefono, txtEmail, cbTipoCliente);


    }

    @FXML
    void OnBtnGuardarModificarClick() throws Exception {
        Cliente clienteModificado = new Cliente();
        clienteModificado.modificarCliente(txtDni,txtNombre,txtApellido,txtDireccion, txtTelefono, txtEmail, cbTipoCliente);
        limpiarCampos();

    }


    //Eliminar cliente - Eventos


    @FXML
    void OnBtnEliminarClick(ActionEvent event) throws Exception {
        Cliente clienteEliminado = new Cliente();
        clienteEliminado.eliminarCliente(txtDni);
        limpiarCampos();

    }
    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCampos(){
        txtDni.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        cbTipoCliente.getSelectionModel().selectFirst();

    }



}

