package com.julieta.hotel.conexion;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    private static Connection con = null;

    public static Connection leerConexion() throws Exception {
        try {
            if(con == null || con.isClosed()){
                String usuario = "root";
                String password = "root";
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost/bd_hotel";
                Class.forName(driver);
                con = DriverManager.getConnection(url,usuario,password);
            }
            return con;
        } catch (Exception ex){
            throw new Exception("Error al crear la conexión:", ex);
        }
    }
}
