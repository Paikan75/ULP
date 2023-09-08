
package proyectotrasversal.entidades;


public class Inscripcion {
    private int idDescripcion;
    private alumno alumno;
    private Materia materia;
    private double nota;

    public Inscripcion() {
    }

    public Inscripcion(int idDescripcion, alumno alumno, Materia materia, double nota) {
        this.idDescripcion = idDescripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(alumno alumno, Materia materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(double nota) {
        this.nota = nota;
    }

    public int getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(int idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
}
