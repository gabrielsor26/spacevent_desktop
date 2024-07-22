
package com.spacevent.spacevent_desktop.Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    Connection con = null;

    String base = "spacevent";
    String url = "jdbc:mysql://localhost:3306/" + base;
    String user = "userProyect";
    String password = "integrador2024";

    public Connection getConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println(con);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }
}
    

