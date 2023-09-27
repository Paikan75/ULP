
package proyectotransversal.Entidades;


public class Materia {
    
    private int idMateria;
    private String nombre;
    private int anoMateria;
    private boolean activo;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, int anoMateria, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.anoMateria = anoMateria;
        this.activo = estado;
    }

    public Materia(String nombre, int anoMateria, boolean estado) {
        this.nombre = nombre;
        this.anoMateria = anoMateria;
        this.activo = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnoMateria() {
        return anoMateria;
    }

    public void setAnoMateria(int anoMateria) {
        this.anoMateria = anoMateria;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
    
}
