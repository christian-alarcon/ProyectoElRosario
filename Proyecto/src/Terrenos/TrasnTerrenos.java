/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrenos;

import Conexion.Conexion;
import static Menu.Menu.jDesktopPane1;
import Socios.*;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego
 */
public class TrasnTerrenos extends javax.swing.JInternalFrame {
 private static String ced_socio;
  private static String ced_sociocomp;
    public static void setSocioVendedor(String cedula ,String nombresSocio, String apellidosSocio) {
       txtSocioVendedor.setText(nombresSocio+" "+apellidosSocio);
       ced_socio=cedula;
        //To change body of generated methods, choose Tools | Templates.
    }
     public static void setSocioComprador(String cedula ,String nombresSocio, String apellidosSocio) {
       txtSocioComprador.setText(nombresSocio+" "+apellidosSocio);
       ced_sociocomp=cedula;
        //To change body of generated methods, choose Tools | Templates.
    }

     DefaultTableModel modelo; 
    /**
     * 
     * Creates new form Socios
     */
    public TrasnTerrenos() {
        initComponents();
        crearTabla();
        cargarModulo();
        
        tblTerreno.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                int fila = tblTerreno.getSelectedRow();
                
                if (fila != -1) {
                    

                    txtIdTerreno.setText(tblTerreno.getValueAt(fila, 0).toString());
                    txtMonto.setText(tblTerreno.getValueAt(fila, 4).toString());
                    txtSocioComprador.setText(tblTerreno.getValueAt(fila, 2).toString());
                    txtSocioVendedor.setText(tblTerreno.getValueAt(fila, 1).toString());
                  //  jDateChooser1.setDate(tblTerreno.getValueAt(fila, 3).toString());
            cnbModulo.setSelectedItem(tblTerreno.getValueAt(fila, 5).toString());
jComboBox2.setSelectedItem(tblTerreno.getValueAt(fila, 6).toString());
                    //txtMetaje.setText(tblTerreno.getValueAt(fila, 1).toString());
                    //txtDireccion.setText(tblTerreno.getValueAt(fila, 2).toString());
    
                   
                    txtIdTerreno.setEnabled(false);
                    //bloquearBotonesNuevo();
                    //Texto();
                   // bloquearBotonUpdate();
                   // btnBorrar.setEnabled(true);
                   // txtPlaca.setEditable(false);
                }

            }
        });
        int limite=8;
        txtMonto.addKeyListener(new KeyListener(){
 
public void keyTyped(KeyEvent e)
 
{if (txtMonto.getText().length()== limite)
 
     e.consume();
}
 
public void keyPressed(KeyEvent arg0) {
}
 
public void keyReleased(KeyEvent arg0) {
}
});
    }
    
    public void buscarDatoVendedor(String Dato) {
           String[] titulos = {"Terreno", "Socio_Vendedor", "Socio_Comprador", "Fecha","Monto", "Modulo","tipo_Transaccion"};
        
        String[] registros = new String[8];
        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";
  
        sql = "Select * from transaccionterreno where Socio_Vendedor LIKE'%" + Dato + "%'";

        try {

            Statement psd = con.createStatement();

            ResultSet rs = psd.executeQuery(sql);

         
            while (rs.next()) { 
                registros[0] = rs.getString("Id"); 
                registros[1] = rs.getString("Socio_Vendedor");
                registros[2] = rs.getString("Socio_Comprador");
                registros[3] = rs.getString("Fecha");
                registros[4] = rs.getString("monto");
                registros[5] = rs.getString("modulo");
                 registros[6] = rs.getString("tipo_transaccion");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
     public void buscarDatoComprador(String Dato) {
           String[] titulos = {"Terreno", "Socio_Vendedor", "Socio_Comprador", "Fecha","Monto", "Modulo","tipo_Transaccion"};
        
        String[] registros = new String[8];
        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";
  
        sql = "Select * from transaccionterreno where Socio_Comprador LIKE'%" + Dato + "%'";

        try {

            Statement psd = con.createStatement();

            ResultSet rs = psd.executeQuery(sql);

         
            while (rs.next()) { 
                registros[0] = rs.getString("Id"); 
                registros[1] = rs.getString("Socio_Vendedor");
                registros[2] = rs.getString("Socio_Comprador");
                registros[3] = rs.getString("Fecha");
                registros[4] = rs.getString("monto");
                registros[5] = rs.getString("modulo");
                 registros[6] = rs.getString("tipo_transaccion");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public void buscarDatoTipoTra(String Dato) {
           String[] titulos = {"Terreno", "Socio_Vendedor", "Socio_Comprador", "Fecha","Monto", "Modulo","tipo_Transaccion"};
        
        String[] registros = new String[8];
        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";
  
        sql = "Select * from transaccionterreno where tipo_transaccion LIKE'%" + Dato + "%'";

        try {

            Statement psd = con.createStatement();

            ResultSet rs = psd.executeQuery(sql);

         
            while (rs.next()) { 
                registros[0] = rs.getString("Id"); 
                registros[1] = rs.getString("Socio_Vendedor");
                registros[2] = rs.getString("Socio_Comprador");
                registros[3] = rs.getString("Fecha");
                registros[4] = rs.getString("monto");
                registros[5] = rs.getString("modulo");
                 registros[6] = rs.getString("tipo_transaccion");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void cargarModulo() {
         java.sql.Connection con;
            con = Conexion.GetConnection();
            
        String sql = "";
        sql = "Select Id_Modulo from terreno";
        
        try {
            Statement psd = con.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
               
                cnbModulo.addItem(rs.getString("Id_Modulo"));
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se cargo los datos del Modiulo");
        }
    }
    
    public void crearTabla() {
        String[] titulos = {"Terreno", "Socio_Vendedor", "Socio_Comprador", "Fecha","Monto", "Modulo","tipo_Transaccion"};
        
        String[] registros = new String[8];
        
        modelo = new DefaultTableModel(null, titulos);



       
        java.sql.Connection con;
            con = Conexion.GetConnection();

        String sql = "";


       
        sql = "Select * from transaccionterreno"; 

        try {
            
            Statement psd = con.createStatement();
            ResultSet rs = psd.executeQuery(sql); 
            while (rs.next()) { 
                registros[0] = rs.getString("Id"); 
                registros[1] = rs.getString("Socio_Vendedor");
                registros[2] = rs.getString("Socio_Comprador");
                registros[3] = rs.getString("Fecha");
                registros[4] = rs.getString("monto");
                registros[5] = rs.getString("modulo");
                 registros[6] = rs.getString("tipo_transaccion");
              
               
                modelo.addRow(registros);

            }

           tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

  public void modificar() {
  if (txtMonto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el monto");
            txtMonto.requestFocus();

        } else if (txtSocioComprador.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Comprador");
            txtSocioComprador.requestFocus();
        } else if (txtSocioVendedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Vendedor");
            txtSocioVendedor.requestFocus();
        
      } else if (cnbModulo.getSelectedIndex()==-1) {
            JOptionPane.showMessageDialog(null, "Ingrese el Modulo");
            cnbModulo.requestFocus();
        
      
        
        } else {
            java.sql.Connection con;
            con = Conexion.GetConnection();
            
            String sql = "";
            sql = "update transaccionterreno set Id='" +  txtIdTerreno.getText() + "',"
                    + "Socio_Vendedor='" + txtSocioVendedor.getText() + "',"
                    + "Socio_Comprador='" + txtSocioComprador.getText() + "',"
                    + "Fecha='" + jDateChooser1.getDateFormatString()+ "',"
                     + "monto='" + txtMonto.getText() + "',"   
                  + "modulo='" + cnbModulo.getSelectedItem()+ "', "
                      + "tipo_transaccion='" + jComboBox2.getSelectedItem()+ "' "
                    + "where Id='" +txtIdTerreno.getText()+ "'";
            
            try {

                PreparedStatement psd = (PreparedStatement) con.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se actualizo Correctamente");
                }
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
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTerreno = new javax.swing.JTable();
        jComboBoxBuscar = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSocioVendedor = new javax.swing.JTextField();
        txtIdTerreno = new javax.swing.JTextField();
        txtSocioComprador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cnbModulo = new javax.swing.JComboBox<String>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnBuscar1 = new javax.swing.JButton();
        btnBuscar2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

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
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSACCIONES REALIZADAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        tblTerreno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTerreno);

        jComboBoxBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE:", "COMPRADOR", "VENDEDOR", "TIPO DE ADQUISICION" }));

        jLabel9.setText("BUSCAR POR:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jComboBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TRANSACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(0, 102, 153));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel12.setText("TRANSACCION A REALIZAR:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE:", "COMPRA-VENTA", "HERENCIA", "TRASPASO" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(30, 30, 30)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INGRESO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("ID:");

        jLabel3.setText("SOCIO VENDEDOR:");

        jLabel5.setText("SOCIO COMPRADOR:");

        jLabel6.setText("MONTO:");

        txtSocioVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSocioVendedorActionPerformed(evt);
            }
        });
        txtSocioVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSocioVendedorKeyTyped(evt);
            }
        });

        txtSocioComprador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSocioCompradorKeyTyped(evt);
            }
        });

        jLabel7.setText("MODULO:");

        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel8.setText("FECHA:");

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnBuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSocioVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSocioComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cnbModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtIdTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtSocioVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cnbModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtSocioComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(0, 51, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente2.png"))); // NOI18N
        jLabel11.setText("TERRENOS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
 verificarDatos();
        crearTabla();
 
    }//GEN-LAST:event_btnGuardarActionPerformed
private void verificarDatos() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String id = txtIdTerreno.getText();
      java.sql.Connection con;
            con = Conexion.GetConnection();
        try {
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("Select * from transaccionterreno where transaccionterreno.Id like '" + id + "'");
            if (!resultado.next()) {
                //  control();
                GuardarTerrenos();
            } else {
                JOptionPane.showMessageDialog(null, "el terreno ya existe");
            }


        } catch (Exception ex) {
        }

    }
 private void GuardarTerrenos() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if (txtIdTerreno.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Ingrese Id Terreno");
            txtIdTerreno.requestFocus(true);
        } else if (txtSocioVendedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Direccion");
            txtSocioVendedor.requestFocus(true);


        } else if (txtSocioComprador.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Metaje");
            txtSocioComprador.requestFocus(true);
        } else if (txtMonto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el socio");
            txtMonto.requestFocus(true);
        }else if (jComboBox2.getSelectedIndex()==-1) {
            JOptionPane.showMessageDialog(null, "Ingrese el Tipo de Transacción");
            txtMonto.requestFocus(true);
        }
// else if (txtAn.getText().isEmpty() ) {
        //  JOptionPane.showMessageDialog(null, "Ingrese la Año");
        //  txtAn.requestFocus(true);
        // }
        else {
            String Id,Socio_Vendedor,Socio_Comprador,Fecha,modulo,tipo_transaccion;
            double monto;
            int anio=jDateChooser1.getCalendar().get(Calendar.YEAR);
            int mes=jDateChooser1.getCalendar().get(Calendar.MONTH)+1;
            int dia=jDateChooser1.getCalendar().get(Calendar.DAY_OF_WEEK_IN_MONTH);
            Fecha=dia+"/"+mes+"/"+anio+"";
            Id = txtIdTerreno.getText();
            Socio_Vendedor =ced_socio;
            Socio_Comprador=ced_sociocomp;
            modulo=cnbModulo.getSelectedItem().toString();
            tipo_transaccion=jComboBox2.getSelectedItem().toString();
            monto=Double.valueOf( txtMonto.getText());

            java.sql.Connection con;
            con = Conexion.GetConnection();
            String sql = "";
            sql = "insert into transaccionterreno(Id,Socio_Vendedor,Socio_Comprador,Fecha,monto,modulo,tipo_transaccion) values (?,?,?,?,?,?,?) ";
            try {
                java.sql.PreparedStatement psd = con.prepareStatement(sql);



                psd.setString(1, Id);
                psd.setString(2, Socio_Vendedor);
                psd.setString(3, Socio_Comprador);
                psd.setString(4, Fecha);
                psd.setDouble(5, monto);
                psd.setString(6, modulo);
                psd.setString(7, tipo_transaccion);

                int n = psd.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
                    limpiarTexto();
               //     BloquearTexto();
               //     bloquearBotonesInicio();



                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }

        }
    }
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
 modificar();
      crearTabla();        // TODO add your handling code here:
      
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
//        BuscarSocio obj = new BuscarSocio();
     //   obj.setVisible(true);
      
        
        if (jComboBoxBuscar.getSelectedItem()== "COMPRADOR" ){
            buscarDatoComprador(txtBuscar.getText());
        }else if (jComboBoxBuscar.getSelectedItem()== "VENDEDOR" ){
            buscarDatoVendedor(txtBuscar.getText());
        }else if (jComboBoxBuscar.getSelectedItem()== "TIPO DE ADQUISICION" ){
            buscarDatoTipoTra(txtBuscar.getText());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void txtSocioVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSocioVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSocioVendedorActionPerformed

    private void txtSocioVendedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSocioVendedorKeyTyped
        // TODO add your handling code here:
        
        controlSoloLetras(evt);
    }//GEN-LAST:event_txtSocioVendedorKeyTyped

    private void txtSocioCompradorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSocioCompradorKeyTyped
        // TODO add your handling code here:
        controlSoloLetras(evt);
    }//GEN-LAST:event_txtSocioCompradorKeyTyped

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        // TODO add your handling code here:
        controlSoloNumeros(evt);
    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
  BuscarSocio socioBuscar=new BuscarSocio("TerrenosVendedor");
        
        jDesktopPane1.add(socioBuscar);
        socioBuscar.setLocation(20,20);

        socioBuscar.show();          // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
  BuscarSocio socioBuscar=new BuscarSocio("TerrenosComprador");
        
        jDesktopPane1.add(socioBuscar);
        socioBuscar.setLocation(20,20);

        socioBuscar.show();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscar2ActionPerformed

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
            java.util.logging.Logger.getLogger(TrasnTerrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrasnTerrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrasnTerrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrasnTerrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrasnTerrenos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cnbModulo;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxBuscar;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTerreno;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdTerreno;
    private javax.swing.JTextField txtMonto;
    public static javax.swing.JTextField txtSocioComprador;
    public static javax.swing.JTextField txtSocioVendedor;
    // End of variables declaration//GEN-END:variables

    private void limpiarTexto() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    txtIdTerreno.setText("");
    txtMonto.setText("");
    txtSocioComprador.setText("");
    txtSocioVendedor.setText("");
    cnbModulo.setSelectedIndex(-1);
    jComboBox2.setSelectedIndex(-1);
    }
    
     public void controlSoloLetras(KeyEvent evt) {
        if (evt.getKeyChar() < 65 || evt.getKeyChar() > 90 && evt.getKeyChar() < 97 || evt.getKeyChar() > 122 && evt.getKeyChar() == 32) {
            evt.consume();
        }
    }

    public void controlSoloNumeros(KeyEvent evt) {
        if (evt.getKeyChar() < 48 || evt.getKeyChar() > 57) {
            evt.consume();
        }
    }
}
