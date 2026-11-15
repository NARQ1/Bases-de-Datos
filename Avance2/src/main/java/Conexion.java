/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nesto
 */
public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/nucleo_de_diagnostico";
    private static String user;
    private static String password;
    
    
    public static void setUser(String user) {
        Conexion.user = user;
    }
    
    public static void setPassword(String password) {
        Conexion.password = password;
    }
    
    public static Connection conectar(){
        
        Connection conexion=null;
        try{
            conexion=DriverManager.getConnection(URL,user,password);
            System.out.println("Conexion exitosa con la base de datos!");
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
        return conexion;
    }
}
