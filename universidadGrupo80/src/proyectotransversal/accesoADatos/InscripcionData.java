package proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectotransversal.Entidades.Inscripcion;

public class InscripcionData {

    private Connection con = null;

    public InscripcionData() {
        con = Conexion.getConnection();

    }

    public void guardarInscripcion (Inscripcion insc){
    
        String sql = "INSERT INTO inscripcion(nota, idAlumno, idMateria) VALUES ?,?,?";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
          
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
             insc.setIdInscripcion(rs.getInt(1));
             JOptionPane.showMessageDialog(null,"Inscripcion creada con exito");
            }
           ps.close();
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla Inscripcion");
        }
        
        
    }
    
    
    
    
}
