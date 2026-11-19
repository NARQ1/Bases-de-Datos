/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nesto
 */
public class Conexion {
    //Atributos de nuestra clase conexion
    private static final String URL = "jdbc:postgresql://localhost:5432/nucleo_de_diagnostico";
    private static String user="postgres";
    private static String password="1234";
    
    
    //Metodos getter y setter para acceder a los atributos
    public static void setUser(String user) {
        Conexion.user = user;
    }
    
    public static void setPassword(String password) {
        Conexion.password = password;
    }
    
    public static String getUser(){
        return user;
    }
    public static String getPassword(){
        return password;
    }
    
    //Funcion para conectar a la base de datos
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
    
    
    
    public static String obtenerNombreUsuario(int id, String tipoUsuario) {
        String sql = "";
        
        if (tipoUsuario.equalsIgnoreCase("doctor")) {
            sql = "SELECT nombre FROM doctor WHERE id = ?";
        } else {
            sql = "SELECT nombre FROM empleado WHERE id = ?";
        }

        String nombre = "";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombre");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar el nombre: " + e.getMessage());
        }

        return nombre;
    }
    
}
