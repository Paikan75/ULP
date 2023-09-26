package proyectotransversal.accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import proyectotransversal.Entidades.Alumno;
import proyectotransversal.Entidades.Inscripcion;
import proyectotransversal.Entidades.Materia;

public class InscripcionData {

    private Connection con = null;
    private AlumnoData ad = new AlumnoData();
    private MateriaData md = new MateriaData();

    public InscripcionData() {
        con = Conexion.getConnection();

    }

    public void guardarInscripcion (Inscripcion insc){
    
        String sql = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES (?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
          
            ps.setDouble(1,insc.getNota());
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
    
    public void actualizarNota(int idAlumno, int idMateria, double nota) {

        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ? ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);

            int fila = ps.executeUpdate();

            if (fila == 1) {

                JOptionPane.showMessageDialog(null, "Nota actualizada correctamente");

            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla Inscripcion");
        }

    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ? ";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1,idAlumno);
            ps.setInt(2,idMateria);
            
            int fila = ps.executeUpdate();
            
            if (fila == 1){
                
                JOptionPane.showMessageDialog(null,"Incripción eliminada con éxito");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla Inscripcion");
        }
        
        
    }
  
    public List<Inscripcion> listarInscripciones(){
         
         ArrayList<Inscripcion> inscripciones= new ArrayList<>();
         
         String sql= "SELECT * FROM inscripcion ";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs= ps.executeQuery();
            
            while (rs.next()){
                
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));

                inscripciones.add(insc);
                
            }
            
             ps.close();
             
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion.");
        }
        
        return inscripciones;
         
     }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        
        ArrayList<Inscripcion> inscripciones= new ArrayList<>();
         
         String sql= "SELECT * FROM inscripcion WHERE idAlumno = ?";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                Inscripcion insc = new Inscripcion();
                
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                              
                insc.setAlumno(alu);
                insc.setMateria(mat);
                insc.setNota(rs.getDouble("nota"));
                
                inscripciones.add(insc);
                
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones");
        }
         
        return inscripciones;
    }
    

    public List<Materia> obtenerMateriasCursadas(int idAlumno){
        
        ArrayList<Materia> inscripciones= new ArrayList<>();
         
         String sql= "SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
                 + " materia WHERE inscripcion.idMateria = materia.IdMateria "
                 + "AND inscripcion.idAlumno = ?;";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                
                Materia mat = new  Materia();
                mat.setIdMateria(rs.getInt("idMateria"));
                mat.setNombre(rs.getString("nombre"));
                mat.setAnoMateria(rs.getInt("año"));
               
                inscripciones.add(mat);
                
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones");
        }
         
        return inscripciones;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int idAlumno){
        
        ArrayList<Materia> inscripciones= new ArrayList<>();
         // el primer Select indica o devuelve todo lo que no estee incluido en el segundo Select.
         String sql="SELECT * FROM materia WHERE materia.estado=1 And materia.idMateria "
                 + "NOT IN (SELECT materia.idMateria from materia,inscripcion where "
                 + "materia.idMateria = inscripcion.IdMateria AND inscripcion.idAlumno = 1)";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                
                Materia mat = new  Materia();
                mat.setIdMateria(rs.getInt("idMateria"));
                mat.setNombre(rs.getString("nombre"));
                mat.setAnoMateria(rs.getInt("año"));
               
                inscripciones.add(mat);
                
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones");
        }
         
        return inscripciones;
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria){
        ArrayList<Alumno> alumnos = new ArrayList<>();
        
        String sql = "SELECT inscripcion.idAlumno, dni, apellido, nombre FROM inscripcion,"
                + "alumno WHERE inscripcion.idAlumno = alumno.idAlumno "
                + "AND inscripcion.idMateria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Alumno alu = new Alumno();
                
                alu.setIdAlumno(rs.getInt("idAlumno"));
                alu.setDni(rs.getInt("dni"));
                alu.setApellido(rs.getString("apellido"));
                alu.setNombre(rs.getString("nombre"));
                
                alumnos.add(alu);
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones");
        }
        
        return alumnos;
    }
    
}
