
package proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "ulp";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static Connection con;
    
    private Conexion (){}
    
    public static Connection getConnection(){
        if(con == null){
            
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection(URL + DB,USUARIO,PASSWORD);
                
            } catch (ClassNotFoundException ex) {
                
                JOptionPane.showMessageDialog(null, "Error al cargar el driver.");
                
            } catch (SQLException ex) {
                
                JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos.");
            }
        }
        
        return con;
    }
}
