
package proyectotransversal.vistas;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectotransversal.Entidades.Alumno;
import proyectotransversal.Entidades.Inscripcion;
import proyectotransversal.accesoADatos.AlumnoData;
import proyectotransversal.accesoADatos.InscripcionData;
import proyectotransversal.accesoADatos.MateriaData;


public class CargaDeNotas extends javax.swing.JInternalFrame {

    
    private DefaultTableModel modelo = new DefaultTableModel(){
        
        public boolean isCellEditable(int f,int c){
        
            if(c==2){
                
                return true;
            }
        return false;
        }
    };
    public CargaDeNotas() {
        initComponents();
        armarCabecera();
        cargarAlumnos();
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTNotas = new javax.swing.JTable();
        jBGuardar = new javax.swing.JButton();
        jBSalir = new javax.swing.JButton();

        setTitle("Carga de Notas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Carga de Notas");

        jLabel2.setText("Seleccione un alumno:");

        jCBAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAlumnosActionPerformed(evt);
            }
        });

        jTNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTNotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTNotas);

        jBGuardar.setText("Guardar");
        jBGuardar.setEnabled(false);
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBSalir.setText("Salir");
        jBSalir.setActionCommand("");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jCBAlumnos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBSalir)
                        .addGap(1, 1, 1)))
                .addGap(97, 97, 97))
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBGuardar)
                    .addComponent(jBSalir))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jCBAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAlumnosActionPerformed

        borrarTabla();
        Alumno aluSelec = (Alumno) jCBAlumnos.getSelectedItem();
        cargarDatos(aluSelec.getIdAlumno());
       
    }//GEN-LAST:event_jCBAlumnosActionPerformed

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
       
        try {
            InscripcionData InscD = new InscripcionData();
            Alumno alum = (Alumno) jCBAlumnos.getSelectedItem();

            int filaSeleccionada = jTNotas.getSelectedRow();
            int idMat = (Integer) (jTNotas.getValueAt(filaSeleccionada, 0));

            double nota = Double.parseDouble((String) jTNotas.getValueAt(filaSeleccionada, 2));
            System.out.println(nota);
            InscD.actualizarNota(alum.getIdAlumno(), idMat, nota);

            borrarTabla();
            cargarDatos(alum.getIdAlumno());   
        
        }catch (NumberFormatException x){
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros.");
            
        }catch (ClassCastException x) {
            JOptionPane.showMessageDialog(null, "Recuerde presioanr enter luego de ingresar la nota.");
        }
        
        
        
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jTNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTNotasMouseClicked
      
        int filaSelec = jTNotas.getSelectedRow();
        
        if (filaSelec !=-1){
            
            jBGuardar.setEnabled(true);
            
        }
        
        
        
    }//GEN-LAST:event_jTNotasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<Alumno> jCBAlumnos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTNotas;
    // End of variables declaration//GEN-END:variables

private void armarCabecera (){
    
    modelo.addColumn("ID MATERIA");
    modelo.addColumn("NOMBRE");
    modelo.addColumn("NOTA");
    jTNotas.setModel(modelo);
        
}

private void cargarAlumnos() {

        AlumnoData alum = new AlumnoData();

        for (Alumno alu : alum.listarAlumno()) {
            jCBAlumnos.addItem(alu);
        }

    }


  private void cargarDatos(int idAlum) {

        
        InscripcionData insc = new InscripcionData();

       List<Inscripcion> obtenerInsc = insc.obtenerInscripcionesPorAlumno(idAlum);
        
            for (Inscripcion inscripcion : obtenerInsc) {

                modelo.addRow(new Object[]{inscripcion.getMateria().getIdMateria(),inscripcion.getMateria().getNombre(),inscripcion.getNota()});

            }
            
          
        
            
        }


        
private void borrarTabla(){
    
    int filas = jTNotas.getRowCount()-1;
    
    for(; filas>=0;filas--){
        
        modelo.removeRow(filas);
    }
    
    
    
}

}
