/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socios;
import Conexion.Conexion;
import Multas.Multas;
import Terrenos.Terrenos;
import Terrenos.TrasnTerrenos;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Chris
 */
public class Buscar_Socio extends javax.swing.JInternalFrame {
    private String nombresSocio="";
    private String apellidosSocio="";
    private String cedulaSocio="";
    
    private String clase;
    DefaultTableModel m;
    
    /**
     * Creates new form Buscar_Socio
     */
    public Buscar_Socio(String clase) {
        initComponents();
                lblErrorcb.setText("");
        this.clase=clase;
    }
       public void TablaSocios(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"Cédula", "Apellidos", "Nombres", "Dirección", "Teléfono"};
        String reg[] = new String[6];
        String sentence = "";
        m = new DefaultTableModel(null, titles){
        
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
        String variable = (String) cbDatoDeBusqueda.getSelectedItem();
         if(variable == "CEDULA")
        {
            sentence = "CALL SP_SociosBusCed('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("Ced_Socio");
                reg[1] = rs.getString("Apellido_Socio");
                reg[2] = rs.getString("Nombre_Socio");
                reg[3] = rs.getString("Dir_Socio");
                reg[4] = rs.getString("Tel_Socio");
                m.addRow(reg);//agrega el registro a la tabla
            }
            tblBusqueda.setModel(m);//asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        }       
    }
       
       
    public void TablaSociosA(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"Cédula", "Apellidos", "Nombres", "Dirección", "Teléfono"};
        String reg[] = new String[6];
        String sentence = "";
        m = new DefaultTableModel(null, titles){
        
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
        String variable = (String) cbDatoDeBusqueda.getSelectedItem(); 
         if(variable == "APELLIDO")
        {
            sentence = "CALL SP_SociosBusApe('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("Ced_Socio");
                reg[1] = rs.getString("Apellido_Socio");
                reg[2] = rs.getString("Nombre_Socio");
                reg[3] = rs.getString("Dir_Socio");
                reg[4] = rs.getString("Tel_Socio");
                m.addRow(reg);//agrega el registro a la tabla
            }
            tblBusqueda.setModel(m);//asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        }       
    }
    public void TablaSociosN(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"Cédula", "Apellidos", "Nombres", "Dirección", "Teléfono"};
        String reg[] = new String[6];
        String sentence = "";
        m = new DefaultTableModel(null, titles){
        
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
        String variable = (String) cbDatoDeBusqueda.getSelectedItem(); 
         if(variable == "NOMBRE")
        {
            sentence = "CALL SP_SociosBusNom('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("Ced_Socio");
                reg[1] = rs.getString("Apellido_Socio");
                reg[2] = rs.getString("Nombre_Socio");
                reg[3] = rs.getString("Dir_Socio");
                reg[4] = rs.getString("Tel_Socio");
                m.addRow(reg);//agrega el registro a la tabla
            }
            tblBusqueda.setModel(m);//asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        cbDatoDeBusqueda = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        lblErrorcb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBusqueda = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SOCIO A BUSCAR:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("TEXTO: ");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });

        cbDatoDeBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE:", "CEDULA", "APELLIDO", "NOMBRE" }));
        cbDatoDeBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDatoDeBusquedaActionPerformed(evt);
            }
        });

        jLabel3.setText("BUSCAR POR:");

        lblErrorcb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblErrorcb.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorcb.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(lblErrorcb))
                    .addComponent(cbDatoDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDatoDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(lblErrorcb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA COMPLETA DE  CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        tblBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBusquedaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBusqueda);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(0, 51, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setText("SOCIOS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        int valor = cbDatoDeBusqueda.getSelectedIndex();

        if (txtBusqueda.getText().trim().length() >= 1)
        {
            String filtro = txtBusqueda.getText();
            if(valor == 1)
            TablaSocios(filtro);
            tblBusqueda.setVisible(true);
            if(valor == 2)
            TablaSociosA(filtro);
            tblBusqueda.setVisible(true);
            if(valor == 3)
            TablaSociosN(filtro);
            tblBusqueda.setVisible(true);
            if (valor ==0)
            {

                lblErrorcb.setText("Seleccione el filtro de busqueda");
            }
        }

        else
        {
            tblBusqueda.setVisible(false);
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        int k=(int)evt.getKeyChar();
        if (k>=97 && k<=122 || k >= 65 && k <= 90 || k>=48 && k<=57 || k==32){

        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void cbDatoDeBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDatoDeBusquedaActionPerformed
        int valor = cbDatoDeBusqueda.getSelectedIndex();
        if (valor == 1)
        {
            txtBusqueda.setText("");
            lblErrorcb.setText("");
            txtBusqueda.requestFocus();
        }
        if (valor == 2)
        {
            txtBusqueda.setText("");
            lblErrorcb.setText("");
            txtBusqueda.requestFocus();
        }
        if (valor == 3)
        {
            txtBusqueda.setText("");
            lblErrorcb.setText("");
            txtBusqueda.requestFocus();
        }

    }//GEN-LAST:event_cbDatoDeBusquedaActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void tblBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBusquedaMouseClicked
        int row = tblBusqueda.rowAtPoint(evt.getPoint());
        cedulaSocio=tblBusqueda.getValueAt(row, 0).toString();
        nombresSocio=tblBusqueda.getValueAt(row, 1).toString();
        apellidosSocio=tblBusqueda.getValueAt(row, 2).toString();
    }//GEN-LAST:event_tblBusquedaMouseClicked

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if(clase=="Multas"){
            if(cedulaSocio=="")
                Multas.setSocio("","","");
            else
                Multas.setSocio(cedulaSocio, nombresSocio, apellidosSocio);
        }
         if(clase=="Terrenos"){
            Terrenos.setSocio(cedulaSocio, nombresSocio, apellidosSocio);
        }if(clase=="TerrenosVendedor"){
            TrasnTerrenos.setSocioVendedor(cedulaSocio,nombresSocio, apellidosSocio);
        }if(clase=="TerrenosComprador"){
            TrasnTerrenos.setSocioComprador(cedulaSocio,nombresSocio, apellidosSocio);
        }
    }//GEN-LAST:event_formInternalFrameClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbDatoDeBusqueda;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrorcb;
    private javax.swing.JTable tblBusqueda;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
