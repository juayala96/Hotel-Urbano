module com.julieta.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.julieta.hotel to javafx.fxml;
    exports com.julieta.hotel;

    opens com.julieta.hotel.controladores to javafx.fxml;
    exports com.julieta.hotel.controladores;


    opens com.julieta.hotel.modelos to javafx.fxml;
    exports com.julieta.hotel.modelos;

    opens com.julieta.hotel.conexion to javafx.fxml;
    exports com.julieta.hotel.conexion;


}