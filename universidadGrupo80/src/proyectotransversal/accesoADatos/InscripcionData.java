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
                insc.setNota(id);
                
                inscripciones.add(insc);
                
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones");
        }
         
        return inscripciones;
    }
    
//    public List<Materia> materiasCursadas (int id){
//        
//        ArrayList<Materia> materias = new ArrayList();
//        
//        String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?";
//        
//        
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id); 
//            ResultSet rs = ps.executeQuery();
//            
//            while(rs.next()){
//                
//                Materia mat = new Materia ();
//                mat=md.buscarMateria(rs.getInt("IdMateria"));
//                
//                materias.add(mat);           
//                
//            }
//            
//            ps.close();
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones");
//        }
//                
//        
//        return materias;
//        
//    }
    public List<Materia> obtenerMateriasCursadas(int id){
        
        ArrayList<Materia> inscripciones= new ArrayList<>();
         
         String sql= "SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
                 + " materia WHERE inscripcion.idMateria = materia.IdMateria "
                 + "AND inscripcion.idAlumno = ?;";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
    
    
}
