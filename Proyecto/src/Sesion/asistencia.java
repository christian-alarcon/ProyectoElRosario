/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sesion;

import Conexion.Conexion;
import Multas.Metodos_Multas;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author PC
 */
public class asistencia extends javax.swing.JInternalFrame {
private Connection conexion;
    private String query;
    private java.sql.Statement st;
    private PreparedStatement ps;
    /**
     * Creates new form asistencia
     */
    public asistencia() {
        initComponents();
    }
    public String selectMaxMulta(){
            
        String id_multa="";
            
        try {
            conexion=Conexion.GetConnection();
            query="SELECT SUBSTRING(MAX(Id_Multa),3) FROM MULTAS";
            st=conexion.createStatement();
            
            ResultSet rs=st.executeQuery(query);
                while(rs.next()){
                    id_multa=rs.getString(1);
                }
            st.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(id_multa==null)
            id_multa="0";
            
        return id_multa;
    }
    public String obtenerNuevoIdMulta(){
        String id_nuevo="";
        String max=selectMaxMulta();
        if(!(max=="0")){
            int ultima=Integer.valueOf(max)+1;
            String ultima_s=String.valueOf(ultima);
            String ceros="";
            for(int i=ultima_s.length();i<6;i++){
                ceros+="0";
            }
            id_nuevo="MU"+ceros+ultima_s;
        }
        else{
            id_nuevo="MU000001";
        }
        
        return id_nuevo;
    }
    public asistencia(String codigo,String motivo,String fecha) throws Exception {
        initComponents();
        carTabla("");
        txtCodigo.setEnabled(false);
        txtMotivo.setEnabled(false);
        jDateChooser1.setEnabled(false);
        txtCodigo.setText(codigo);
        txtMotivo.setText(motivo);
        Calendar c2 = new GregorianCalendar();
        jDateChooser1.setCalendar(c2);
        //jDateChooser1.setDateFormatString(fecha);
        
        //TableColumn check = jTable1.getColumnModel().getColumn(3);
        //JCheckBox checkbox = new JCheckBox();
        //check.setCellEditor(new DefaultCellEditor(checkbox));
        JComboBox asistencia;
        TableColumn col=jTable1.getColumnModel().getColumn(3);
        String op[]={"Asiste","Falta","Atraso"};
        asistencia=new JComboBox(op);
        col.setCellEditor(new DefaultCellEditor(asistencia));
    }
    
    
    DefaultTableModel model;
    public void carTabla(String Dato) throws Exception{
        //String [] titulos={"MODULO","CEDULA PRESIDENTE","NOMBRE PRESIDENTE","ASISTIO","NO ASISTIO","ATRASO"};
        String [] registros=new String[3];
        
        model=new DefaultTableModel();
        model = (DefaultTableModel) jTable1.getModel();
        java.sql.Connection cn =  Conexion.GetConnection();
        String sql="";
        sql="select * from socio where Ced_Socio LIKE '%"+Dato+"%'";
        
        try {
            Statement psd=(Statement) cn.createStatement();
            ResultSet rs=psd.executeQuery(sql);
            
            while(rs.next()){
                registros[0]=rs.getString("Mod_Per");
                registros[1]=rs.getString("Ced_Socio");
                String apellido=rs.getString("Apellido_Socio");
                String nombre=rs.getString("Nombre_Socio");
                registros[2]=apellido+" "+nombre;
                //JOptionPane.showMessageDialog(null, registros[0]);
                model.addRow(registros);
            }
            jTable1.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public static String fechaMySQL(JDateChooser miJDate){ 

        DecimalFormat sdf = new DecimalFormat("00"); 
        int anio = miJDate.getCalendar().get(Calendar. YEAR); 
        int mes = miJDate.getCalendar().get(Calendar. MONTH) + 1; 
        int dia = miJDate.getCalendar().get(Calendar. DAY_OF_MONTH); 

        return anio+"/"+sdf.format(mes)+"/"+sdf.format(dia); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnCliGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION ASISTENCIA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        btnCliGuardar.setText("GUARDAR");
        btnCliGuardar.setMaximumSize(new java.awt.Dimension(95, 23));
        btnCliGuardar.setMinimumSize(new java.awt.Dimension(95, 23));
        btnCliGuardar.setName(""); // NOI18N
        btnCliGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCancelar1.setText("IMPRIMIR");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCliGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS SESION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel5.setText("MOTIVO:");

        jLabel7.setText("FECHA:");

        jLabel6.setText("CODIGO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txtCodigo))
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ASISTENCIA SESION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MODULO", "CEDULA ", "APELLIDO NOMBRE", "ASISTENCIA", "REPRESENTANTE"
            }
        ));
        jTable1.setFocusable(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(0, 51, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente2.png"))); // NOI18N
        jLabel11.setText("ASISTENCIA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliGuardarActionPerformed
        TableModel tableModel = jTable1.getModel();
        int cols = tableModel.getColumnCount();
        int fils = tableModel.getRowCount();
        for (int i = 0; i < fils; i++) {
            String asiste = tableModel.getValueAt(i, 3).toString();
            String modulo = tableModel.getValueAt(i, 0).toString();
            String razon = "Asiste";
            //Date fecha = jDateChooser1.getDate();
            String socio = tableModel.getValueAt(i, 1).toString();
            ////////////////////////////////////////////////////////////
            String Id = obtenerNuevoIdMulta();
            if (asiste.equals("Falta")) {
                int valor = 10;
                razon = "Falta";
                /****************************************/
                query = "INSERT INTO MULTAS(Id_Multa,Nom_Multa,Val_Multa,Fec_Multa,Id_Socio)"
                + "VALUES(?,?,?,?,?)";
                try {

                    conexion = Conexion.GetConnection();
                    ps = conexion.prepareStatement(query);

                    ps.setString(1, Id);
                    ps.setString(2, razon);
                    ps.setDouble(3, valor);
                    ps.setString(4, fechaMySQL(jDateChooser1));
                    ps.setString(5, socio);

                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "Se insertó el dato Multa Falta correctamente...");
                    }

                    ps.close();
                    conexion.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
                }
                /****************************************/
                ////////////////////////////////////////////////////////////
            }
            if (asiste.equals("Atraso")) {
                int valor = 5;
                razon = "Atraso";
                /****************************************/
                query = "INSERT INTO MULTAS(Id_Multa,Nom_Multa,Val_Multa,Fec_Multa,Id_Socio)"
                + "VALUES(?,?,?,?,?)";
                try {

                    conexion = Conexion.GetConnection();
                    ps = conexion.prepareStatement(query);

                    ps.setString(1, Id);
                    ps.setString(2, razon);
                    ps.setDouble(3, valor);
                    ps.setString(4, fechaMySQL(jDateChooser1));
                    ps.setString(5, socio);

                    if (ps.executeUpdate() >0) {
                        JOptionPane.showMessageDialog(null, "Se insertó el dato Multa Atraso correctamente...");
                    }

                    ps.close();
                    conexion.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
                }
                /****************************************/
                ////////////////////////////////////////////////////////////
            }
            /****************************************/
            query = "INSERT INTO Detalle_Sesion(Rep_Det_Sesion,Obs_Det_Sesion,Id_Sesion,Id_Modulo)"
            + "VALUES(?,?,?,?)";
            String Rep_Det_Sesion,Obs_Det_Sesion,Id_Sesion,Id_Modulo;
            Id_Sesion = txtCodigo.getText();
            Id_Modulo = modulo;
            Rep_Det_Sesion = socio;
            Obs_Det_Sesion = asiste;
            try {

                conexion = Conexion.GetConnection();
                ps = conexion.prepareStatement(query);

                ps.setString(1, Rep_Det_Sesion);
                ps.setString(2, Obs_Det_Sesion);
                ps.setString(3, Id_Sesion);
                ps.setString(4, Id_Modulo);

                if (ps.executeUpdate() >  0) {
                    JOptionPane.showMessageDialog(null, "Se insertó el dato en Detalle Sesión correctamente...");
                }

                ps.close();
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
            }

            /****************************************/
            /******Holaaaa*****/

            //for (int j = 0; j < cols; j++) {
                //System.out.print(tableModel.getValueAt(i, j);

                    //}
                //System.out.println();
            }
    }//GEN-LAST:event_btnCliGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
        MessageFormat header=new MessageFormat("Sesión");
        MessageFormat footer=new MessageFormat("Página");

        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        }
        catch(PrinterException pe) {
            JOptionPane.showMessageDialog(null, "No se pudo imprimir");
        }
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnCliGuardar;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMotivo;
    // End of variables declaration//GEN-END:variables
}
