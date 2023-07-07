package paquete;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    private Conexion() {

    }

    //Lo primero creamos una variable en la cual vamos a guardar el estado de la conexion a nuestra BD
    private static Connection conexion;

    //Creamos una variable para crear una sola instancia
    private static Conexion instacia;

    //Creamos las variables para poder conectarnos a la BD
    private static final String URL = "jdbc:mysql://localhost:3306/bd_registros";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    //Metodo para conectarnos a la base de Datos
    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            JOptionPane.showMessageDialog(null, "Conexion exitosa!");
            return conexion;

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return conexion;
    }

    //Creamos el metodo para cerrar la conexion
    public void cerrarConexion() throws SQLException {
        try {

            conexion.close();
            JOptionPane.showMessageDialog(null, "Se desconecto de la base de datos!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
            conexion.close();
        } finally {
            conexion.close();
        }
    }

    //Patron singleton
    public static Conexion getInstance() {
        if (instacia == null) {
            instacia = new Conexion();
        }
        return instacia;
    }
}
