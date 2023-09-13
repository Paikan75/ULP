
package proyectotransversal;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import proyectotransversal.Entidades.Alumno;
import proyectotransversal.accesoADatos.AlumnoData;
import proyectotransversal.accesoADatos.Conexion;


public class ProyectoTrasversal {

   
    public static void main(String[] args) {
       
        Connection conex = Conexion.getConnection();
        
        //Alumno juan = new Alumno(12456876,"Flores","Roberto",LocalDate.of(2012, Month.MARCH, 15),true);
        
        AlumnoData alu = new AlumnoData();
        
        //alu.guardarAlumno(juan);
        
        //alu.modificarAlumno(juan);
        
        //alu.eliminarAlumno(1);
        
        //Alumno alumnoEncontrado=alu.buscarAlumnoPorDni(12456876);
        
        //if(alumnoEncontrado!=null){
        //System.out.println(alumnoEncontrado.toString());
        
    //}
    
        for(Alumno alumno:alu.listarAlumno()){
            System.out.println(alumno);
        }
    }
}
