/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cuotas;

import Conexion.Conexion;
import static Menu.Menu.jDesktopPane1;
import Socios.BuscarSocio;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego
 */
public class Cuotas extends javax.swing.JInternalFrame {
    
    
    /////////YA NO VA SOCIOS BORRAR EL CAMPO
    /**
     * Creates new form Cuotas
     */
    public Cuotas() {
        initComponents();
         btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
        txtID.setEnabled(false);
        TablaSocios("");
        RecuperarID();
    }
public  void Limpiar()
    {
        txtID.setText("");
        txtxMotivo.setText("");
        txtValor.setText("");
//        txtSocio.setText("");
        
        
        btnActualizar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
  public void DesactivarCampos() {        
        txtID.setEnabled(false);
        txtxMotivo.setEnabled(false);
        txtValor.setEnabled(false);
//        txtSocio.setEnabled(false);
       
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
   DefaultTableModel m;
      public void RecuperarID() {//Realiza la consulta de los productos que tenemos en la base de datos
      
    
        String sentence = "";
        

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
         String variable = (String) jcbBuscar.getSelectedItem();
      
            sentence = "Select MAX(Id_Cuota) From cuota; ";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
               txtID.setText(String.valueOf(Integer.parseInt( rs.getString(1))+1));
              
               
            }
           //asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        
    }

    public void TablaSocios(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"ID", "MOTIVO", "VALOR", "FECHA"};
        String reg[] = new String[4];
        String sentence = "";
        m = new DefaultTableModel(null, titles);

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
         String variable = (String) jcbBuscar.getSelectedItem();
      
            sentence = "CALL SP_ConsultarCuota()";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("Id_Cuota");
                reg[1] = rs.getString("Mot_Cuota");
                reg[2] = rs.getString("Val_Cuota");
                reg[3] = rs.getString("Fec_Cuota");
               // reg[4] = rs.getString("Id_Socio");
                m.addRow(reg);//agrega el registro a la tabla
            }
            jtbCuotas.setModel(m);//asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        
    }
    public void TablaSociosF(String val) {//Realiza la consulta las cuotas 
        String titles[] = {"ID", "MOTIVO", "VALOR", "FECHA"};
        String reg[] = new String[4];
        String sentence = "";
        m = new DefaultTableModel(null, titles);

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
        String variable = (String) jcbBuscar.getSelectedItem(); 
         if(variable == "FECHA")
        {
            sentence = "CALL SP_ConsultarCuotaFecha('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("Id_Cuota");
                reg[1] = rs.getString("Mot_Cuota");
                reg[2] = rs.getString("Val_Cuota");
                reg[3] = rs.getString("Fec_Cuota");
             m.addRow(reg);//agrega el registro a la tabla
            }
         jtbCuotas.setModel(m);//asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        }       
    }
     public void TablaSociosM(String val) {//Realiza la consulta las cuotas 
        String titles[] = {"ID", "MOTIVO", "VALOR", "FECHA"};
        String reg[] = new String[4];
        String sentence = "";
        m = new DefaultTableModel(null, titles);

        Connection con;
        con = Conexion.GetConnection();
        //sentence = "CALL SP_SociosSel('%" + val + "%')";
        String variable = (String) jcbBuscar.getSelectedItem(); 
         if(variable == "MOTIVO")
        {
            sentence = "CALL SP_ConsultarCuotaMotivo('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("Id_Cuota");
                reg[1] = rs.getString("Mot_Cuota");
                reg[2] = rs.getString("Val_Cuota");
                reg[3] = rs.getString("Fec_Cuota");
             m.addRow(reg);//agrega el registro a la tabla
            }
         jtbCuotas.setModel(m);//asigna a la tabla el modelo creado
          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
        }       
    }
  public void InsertarCuotas() {

        try {
            String ID = txtID.getText();
            String motivo = txtxMotivo.getText();
            String valorCuota = txtValor.getText();
//            int socio = Integer.parseInt(txtSocio   .getText());
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format( jdcFecha.getDate());

            //SI LOS CAMPOS ESTAN LLENOS
            if (!txtID.getText().trim().isEmpty()
                    && !txtxMotivo.getText().trim().isEmpty()
                    && !txtValor.getText().trim().isEmpty()
                  //  && !txtSocio.getText().trim().isEmpty()
                   // && jdcFecha.getDate().toString().isEmpty()
                    ) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de insertar la cuota?", "Confirmación", JOptionPane.YES_OPTION);

                if (JOptionPane.YES_OPTION == confirmado) {

                    //REGISTRAR LOS PRODUCTOS
                    Connection miConexion = (Connection) Conexion.GetConnection();
                    try {
                        Statement statement = (Statement) miConexion.createStatement();
                        statement.executeUpdate("CALL SP_InsertarCuota ('" + ID + "','" + motivo + "','" + valorCuota + "','" + fecha + "') ");
                        miConexion.close();
                        JOptionPane.showMessageDialog(this, "Se agrego correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                        Limpiar();

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cancelado correctamente", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    txtxMotivo.requestFocus();
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "No dejar vacio ningun campo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtxMotivo.requestFocus();
        }

    }
  public void ActualizarSocios()
    {
            Connection con;
        con = Conexion.GetConnection();
        String sentence = "";
        String msj = "";
        String id, mot, val, fec,  soc;
        id = txtID.getText();
        mot = txtxMotivo.getText();
        val = txtValor.getText();
       fec = new SimpleDateFormat("yyyy-MM-dd").format( jdcFecha.getDate());
       
//        soc = txtSocio.getText();

        //si los datos son diferentes de vacios
        if (!id.isEmpty() && !mot.isEmpty() && !val.isEmpty() && !fec.isEmpty() ) {

            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar la cuota?", "Confirmación", JOptionPane.YES_OPTION);
            if (JOptionPane.YES_OPTION == confirmado) {

                sentence = "CALL SP_ModificarCuota('" + id + "','" + mot + "','" + val + "','" + fec + "')";
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Cuota modificado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

                try {
                    PreparedStatement pst = con.prepareStatement(sentence);
                    pst.executeUpdate();

                    txtID.setText("");
                    txtxMotivo.setText("");
                    txtValor.setText("");
                
                    btnGuardar.setEnabled(true);
                    btnActualizar.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                   // TablaSocios("");
                    //DesactivarCampos();

                    //tblBusqueda.setVisible(false);
                    txtBuscar.setText("");
                    txtBuscar.requestFocus();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                txtBuscar.setText("");
            //    TablaSocios("");
                JOptionPane.showMessageDialog(null, "Cancelado correctamente", "Información", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No dejar vacio ningun campo", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }
    }
    public void EliminarCuota(){
         int filasel;
        String id;
        try{
            filasel=jtbCuotas.getSelectedRow();
            if (filasel==-1) {
                JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna fila","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtBuscar.requestFocus();
            }else{
                int confirmado=javax.swing.JOptionPane.showConfirmDialog(null,"¿Realmente desea eliminar el socio?","Confirmación",JOptionPane.YES_OPTION);
                if(confirmado==JOptionPane.YES_OPTION){
                m=(DefaultTableModel) jtbCuotas.getModel();
                id=(String) m.getValueAt(filasel, 0);
                
                Connection con;
               con=Conexion.GetConnection();
                String sentence="";
                sentence="CALL SP_EliminarCuota("+id+")";
                
                try {
                       PreparedStatement pst=con.prepareStatement(sentence);
                       pst.executeUpdate();
                       JOptionPane.showMessageDialog(null,"Socio eliminado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                        //Limpia de nuevo todos los campos
                       jtbCuotas.setVisible(false);
                       txtBuscar.setText("");
                        txtBuscar.requestFocus();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,ex);
                            }
                            }else{
                            TablaSocios("");
                            jtbCuotas.setVisible(false);
                            txtBuscar.setText("");
                             txtBuscar.requestFocus();
                             Limpiar();
                           JOptionPane.showMessageDialog(null, "Cancelado correctamente","Información",JOptionPane.ERROR_MESSAGE);
                                 }
                  }
        }catch(Exception e){
            e.printStackTrace();
            
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
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtxMotivo = new javax.swing.JTextField();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCuotas = new javax.swing.JTable();
        jcbBuscar = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        lblErrorcb = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 153)));

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 153)));
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel3.setText("VALOR:");

        jLabel5.setText("MOTIVO:");

        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        jLabel7.setText("FECHA:");

        jLabel6.setText("ID:");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(txtxMotivo))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtValor)))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE CUOTAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 153)));

        jtbCuotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtbCuotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbCuotasMouseClicked(evt);
            }
        });
        jtbCuotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtbCuotasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jtbCuotas);

        jcbBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE:", "MOTIVO", "FECHA" }));
        jcbBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbBuscarActionPerformed(evt);
            }
        });

        jLabel9.setText("BUSCAR POR:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        lblErrorcb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblErrorcb.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorcb.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorcb, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbBuscar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblErrorcb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 51, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(0, 51, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente2.png"))); // NOI18N
        jLabel11.setText("CUOTAS");

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        InsertarCuotas();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
             // TODO add your handling code here:
        ActualizarSocios();
       Limpiar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       EliminarCuota();        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jcbBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbBuscarActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void jtbCuotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbCuotasMouseClicked
       int row = jtbCuotas.rowAtPoint(evt.getPoint());
       txtID.setText(jtbCuotas.getValueAt(row, 0).toString());
    txtValor.setText(jtbCuotas.getValueAt(row, 2).toString());
    txtxMotivo.setText(jtbCuotas.getValueAt(row, 1).toString());
     try {
      String fecha =jtbCuotas.getValueAt(row, 3).toString();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
      Date fechaDate = formato.parse(fecha.replace('-', '/'));
      jdcFecha.setDate(fechaDate);
      } catch(Exception e){
            e.printStackTrace();
            
        }
     btnGuardar.setEnabled(false);
     btnActualizar.setEnabled(true);
     btnEliminar.setEnabled(true);
    }//GEN-LAST:event_jtbCuotasMouseClicked

    private void jtbCuotasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbCuotasKeyReleased
 
           // TODO add your handling code here:
    }//GEN-LAST:event_jtbCuotasKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       int valor = jcbBuscar.getSelectedIndex();
        if (txtBuscar.getText().trim().length() >= 1) 
        {
            String filtro = txtBuscar.getText();
            if(valor == 1)
            TablaSociosM(filtro);
            jtbCuotas.setVisible(true);
            if(valor == 2)
            TablaSociosF(filtro);
            jtbCuotas.setVisible(true);
           
        if (valor ==0)
        {

             lblErrorcb.setText("Seleccione el filtro de busqueda");
        }
        } 
        
        else 
        {
            jtbCuotas.setVisible(false);
        } // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorKeyPressed

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
if (!Character.isDigit(evt.getKeyChar())&&evt.getKeyChar()!='.') {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
         }        // TODO add your handling code here:
if (evt.getKeyChar()=='.'&& txtValor.getText().contains(".")) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
         } 
    }//GEN-LAST:event_txtValorKeyTyped
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cuotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cuotas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbBuscar;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTable jtbCuotas;
    private javax.swing.JLabel lblErrorcb;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtxMotivo;
    // End of variables declaration//GEN-END:variables
}
