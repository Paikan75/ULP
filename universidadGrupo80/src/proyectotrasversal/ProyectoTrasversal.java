
package proyectotrasversal;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import proyectotrasversal.Entidades.Alumno;
import proyectotrasversal.accesoADatos.AlumnoData;
import proyectotrasversal.accesoADatos.Conexion;


public class ProyectoTrasversal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conex = Conexion.getConnection();
        
        //Alumno juan = new Alumno(12456876,"Flores","Roberto",LocalDate.of(2012, Month.MARCH, 15),true);
        
        //Alumno gomez = new Alumno(420,"Gomez","Roberto",LocalDate.of(2012, Month.MARCH, 15),true);
        //Alumno flores = new Alumno(212,"Flores","Roberto",LocalDate.of(2012, Month.MARCH, 15),true);
        //Alumno perez = new Alumno(999,"Perez","Laura",LocalDate.of(2012, Month.MARCH, 15),true);
        
        AlumnoData alu = new AlumnoData();
        
        for(Alumno alumno:alu.listarAlumno()){
            System.out.println(alumno);
        }
        
        //alu.guardarAlumno(gomez);
        //alu.guardarAlumno(flores);
        //alu.guardarAlumno(perez);
        
        //alu.modificarAlumno(juan);
        
        //alu.eliminarAlumno(1);
        
        //Alumno alumnoEncontrado=alu.buscarAlumnoPorDni(12456876);
        
        //if(alumnoEncontrado!=null){
        //System.out.println(alumnoEncontrado.toString());
        
    //}
    }
}
