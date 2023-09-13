
package proyectotrasversal.accesoADatos;

import java.sql.*;
import javax.swing.JOptionPane;
import proyectotrasversal.Entidades.Materia;


public class MateriaData {
    private Connection con = null;
    
    public MateriaData(){
        con=Conexion.getConnection();
    }
    
    public void guardarMateria (Materia materia){
        
       String sql = "INSERT INTO materia (nombre, año, estado) "
                + "VALUES (?,?,?)";
        
        try {
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAnoMateria());
            ps.setBoolean(3,materia.isEstado());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia guardado.");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia.");
        }
       
        }
    }
    
    