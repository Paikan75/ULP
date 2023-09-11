
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
        
        Alumno juan = new Alumno(12456876,"Flores","Roberto",LocalDate.of(2012, Month.MARCH, 15),true);
        
        AlumnoData alu = new AlumnoData();
        
        alu.guardarAlumno(juan);
        
    }
    
}
