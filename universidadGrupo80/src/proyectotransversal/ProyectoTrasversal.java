
package proyectotransversal;

import java.sql.Connection;
import proyectotransversal.Entidades.Inscripcion;
import proyectotransversal.Entidades.Materia;
import proyectotransversal.accesoADatos.AlumnoData;
import proyectotransversal.accesoADatos.Conexion;
import proyectotransversal.accesoADatos.InscripcionData;
import proyectotransversal.accesoADatos.MateriaData;


public class ProyectoTrasversal {

   
    public static void main(String[] args) {
       
        Connection conex = Conexion.getConnection();
        
        //Alumno juan = new Alumno(12456876,"Flores","Roberto",LocalDate.of(2012, Month.MARCH, 15),true);
        
        AlumnoData alu = new AlumnoData();
        MateriaData mat = new MateriaData();
        
        //alu.guardarAlumno(juan);
        
        //alu.modificarAlumno(juan);
        
        //alu.eliminarAlumno(1);
        
        //Alumno alumnoEncontrado=alu.buscarAlumnoPorDni(12456876);
        
        //if(alumnoEncontrado!=null){
        //System.out.println(alumnoEncontrado.toString());
        
    //}
    
//        for(Alumno alumno:alu.listarAlumno()){
//            System.out.println(alumno);
//        }
        InscripcionData id= new InscripcionData();

//        for(Materia inscr:id.materiasCursadas(2)){
//            
//            System.out.println(inscr);
//        }

        for(Materia inscr:id.obtenerMateriasCursadas(2)){
            
            System.out.println(inscr);
        }
    }
}
