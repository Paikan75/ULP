
package proyectotransversal;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import proyectotransversal.Entidades.Alumno;
import proyectotransversal.Entidades.Inscripcion;
import proyectotransversal.Entidades.Materia;
import proyectotransversal.accesoADatos.Conexion;
import proyectotransversal.accesoADatos.InscripcionData;



public class ProyectoTrasversal {

   
    public static void main(String[] args) {
       
        Connection conex = Conexion.getConnection();
        
        
        Alumno alum = new Alumno (1,12456876,"Flores","Juan Roberto",LocalDate.of(2012, 3, 15),true);
        Materia mat = new Materia (1,"Matematica",1,true);
        
             
        
        Inscripcion inscN =new Inscripcion(alum,mat,10);
        
        InscripcionData inscDATA = new InscripcionData();
        inscDATA.guardarInscripcion(inscN);
        

    }
}
