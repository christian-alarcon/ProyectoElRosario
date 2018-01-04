/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrenos;

import Conexion.Conexion;
import static Menu.Menu.jDesktopPane1;
import Multas.Metodos_Multas;
import Multas.Multas;
import Socios.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego
 */
public class Terrenos extends javax.swing.JInternalFrame {

    public static void setSocio(String cedulaSocio, String nombresSocio, String apellidosSocio) {
        //To change body of generated methods, choose Tools | Templates.
        
        txtSocio.setText(nombresSocio+" "+apellidosSocio);
       // ced_socio=cedula;
    }

    DefaultTableModel modelo;

    /**
     * Creates new form Socios
     */
      int limite  = 10;
    public Terrenos() {
        initComponents();
        crearTabla();
        cargarModulo();
      //  bloquearBotonesInicio() ;

        tblTerreno.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                int fila = tblTerreno.getSelectedRow();

                if (fila != -1) {

                    txtIdTerreno.setText(tblTerreno.getValueAt(fila, 0).toString());

                    txtMetaje.setText(tblTerreno.getValueAt(fila, 1).toString());
                    txtDireccion.setText(tblTerreno.getValueAt(fila, 2).toString());
                    txtSolar.setText(tblTerreno.getValueAt(fila, 3).toString());
                    txtSocio.setText(tblTerreno.getValueAt(fila, 5).toString());
                    cnbModulo.setSelectedItem(tblTerreno.getValueAt(fila, 4).toString());

                    txtIdTerreno.setEnabled(false);
                    //bloquearBotonesNuevo();
                    //Texto();
                    // bloquearBotonUpdate();
                    // btnBorrar.setEnabled(true);
                    // txtPlaca.setEditable(false);
                }

            }
        });
    
 
txtMetaje.addKeyListener(new KeyListener(){
 
public void keyTyped(KeyEvent e)
 
{if (txtMetaje.getText().length()== limite)
 
     e.consume();
}
 
public void keyPressed(KeyEvent arg0) {
}
 
public void keyReleased(KeyEvent arg0) {
}
});
txtSolar.addKeyListener(new KeyListener(){
 
public void keyTyped(KeyEvent e)
 
{if (txtSolar.getText().length()== limite)
 
     e.consume();
}
 
public void keyPressed(KeyEvent arg0) {
}
 
public void keyReleased(KeyEvent arg0) {
}
});
    }
    
      public void bloquearBotonesInicio() {
        
        btnCliGuardar.setEnabled(true);
        btnCancelar.setEnabled(false);
        
        btnCliEliminar.setEnabled(false);
        btnCliActualizar.setEnabled(false);

    }
        public void bloquearBotonesActualizar() {
        
        btnCliGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        
        btnCliEliminar.setEnabled(false);
        btnCliActualizar.setEnabled(false);

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
        String[] titulos = {"Terreno", "Metros", "Dirección", "Solar", "Modulo", "Socio"};

        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";

        sql = "Select * from terreno";

        try {

            Statement psd = con.createStatement();
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("Id_Terreno");
                registros[1] = rs.getString("Met_Terreno");
                registros[2] = rs.getString("Dir_Terreno");
                registros[3] = rs.getString("Sol_Terrono");
                registros[4] = rs.getString("Id_Modulo");
                registros[5] = rs.getString("Socio");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void buscarDato(String Dato) {
        String[] titulos = {"Terreno", "Metros", "Dirección", "Solar", "Modulo", "Socio"};

        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";

        sql = "Select * from terreno where Id_Terreno LIKE'%" + Dato + "%'";

        try {

            Statement psd = con.createStatement();

            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("Id_Terreno");
                registros[1] = rs.getString("Met_Terreno");
                registros[2] = rs.getString("Dir_Terreno");
                registros[3] = rs.getString("Sol_Terrono");
                registros[4] = rs.getString("Id_Modulo");
                registros[5] = rs.getString("Socio");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void buscarDatoModulo(String Dato) {
        String[] titulos = {"Terreno", "Metros", "Dirección", "Solar", "Modulo", "Socio"};

        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";

        sql = "Select * from terreno where Id_Modulo LIKE'%" + Dato + "%'";

        try {

            Statement psd = con.createStatement();

            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("Id_Terreno");
                registros[1] = rs.getString("Met_Terreno");
                registros[2] = rs.getString("Dir_Terreno");
                registros[3] = rs.getString("Sol_Terrono");
                registros[4] = rs.getString("Id_Modulo");
                registros[5] = rs.getString("Socio");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void buscarDatoSocio(String Dato) {
        String[] titulos = {"Terreno", "Metros", "Dirección", "Solar", "Modulo", "Socio"};

        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos);

        java.sql.Connection con;
        con = Conexion.GetConnection();

        String sql = "";

        sql = "Select * from terreno where Socio LIKE'%" + Dato + "%'";

        try {

            Statement psd = con.createStatement();

            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("Id_Terreno");
                registros[1] = rs.getString("Met_Terreno");
                registros[2] = rs.getString("Dir_Terreno");
                registros[3] = rs.getString("Sol_Terrono");
                registros[4] = rs.getString("Id_Modulo");
                registros[5] = rs.getString("Socio");

                modelo.addRow(registros);

            }

            tblTerreno.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void modificar() {
        if (txtSocio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Socio");
            txtSocio.requestFocus();

        } else if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Dirección");
            txtDireccion.requestFocus();

        } else {
            java.sql.Connection con;
            con = Conexion.GetConnection();

            String sql = "";
            sql = "update terreno set Id_Terreno='" + txtIdTerreno.getText() + "',"
                    + "Dir_Terreno='" + txtDireccion.getText() + "',"
                    + "Met_Terreno='" + txtMetaje.getText() + "',"
                    + "Id_Modulo='" + cnbModulo.getSelectedItem() + "',"
                    + "Sol_Terrono='" + txtSolar.getText() + "',"
                    + "Socio='" + txtSocio.getText() + "' "
                    + "where Id_Terreno ='" + txtIdTerreno.getText() + "'";

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
        btnCliGuardar = new javax.swing.JButton();
        btnCliActualizar = new javax.swing.JButton();
        btnCliEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtSocio = new javax.swing.JTextField();
        txtMetaje = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cnbModulo = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        txtIdTerreno = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSolar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTerreno = new javax.swing.JTable();
        jComboBoxBuscar = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        btnCliGuardar.setText("GUARDAR");
        btnCliGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliGuardarActionPerformed(evt);
            }
        });

        btnCliActualizar.setText("ACTUALIZAR");
        btnCliActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActualizarActionPerformed(evt);
            }
        });

        btnCliEliminar.setText("ELIMINAR");
        btnCliEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliEliminarActionPerformed(evt);
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
                    .addComponent(btnCliEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCliGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCliActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCliGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnCliActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(btnCliEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS PERSONALES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("SOCIO:");

        jLabel3.setText("DIRECCION:");

        jLabel4.setText("METRAJE:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        txtSocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSocioKeyTyped(evt);
            }
        });

        txtMetaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMetajeActionPerformed(evt);
            }
        });
        txtMetaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMetajeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMetajeKeyTyped(evt);
            }
        });

        jLabel7.setText("MODULO:");

        jLabel5.setText("ID");

        jLabel8.setText("SOLAR :");

        txtSolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSolarActionPerformed(evt);
            }
        });
        txtSolar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSolarKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdTerreno)
                    .addComponent(cnbModulo, 0, 154, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(txtSocio, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMetaje, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSolar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtMetaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(txtSolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnbModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(21, 21, 21))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE TERRENOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

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

        jComboBoxBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE:", "SOCIO", "MODULO" }));

        jLabel9.setText("BUSCAR POR:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCliGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliGuardarActionPerformed
        verificarDatos();

        crearTabla();

    }//GEN-LAST:event_btnCliGuardarActionPerformed

    private void btnCliActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActualizarActionPerformed
        // TODO add your handling code here:
        modificar();
        crearTabla();
    }//GEN-LAST:event_btnCliActualizarActionPerformed

    private void btnCliEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliEliminarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCliEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
//        BuscarSocio obj = new BuscarSocio();
        // obj.setVisible(true); 
        buscarDato(txtBuscar.getText());
        
        if (jComboBoxBuscar.getSelectedItem()== "SOCIO" ){
            buscarDatoSocio(txtBuscar.getText());
        }else if (jComboBoxBuscar.getSelectedItem()== "MODULO" ){
            buscarDatoModulo(txtBuscar.getText());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtMetajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMetajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMetajeActionPerformed

    private void txtSolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSolarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSolarActionPerformed

    private void txtSocioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSocioKeyTyped
        // TODO add your handling code here:

        controlSoloLetras(evt);
    }//GEN-LAST:event_txtSocioKeyTyped

    private void txtSolarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSolarKeyTyped
        // TODO add your handling code here:
        controlSoloNumeros(evt);
    }//GEN-LAST:event_txtSolarKeyTyped

    private void txtMetajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMetajeKeyTyped
        // TODO add your handling code here:
        controlSoloNumeros(evt);
    }//GEN-LAST:event_txtMetajeKeyTyped

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        // TODO add your handling code here:

        controlSoloLetras(evt);
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  BuscarSocio socioBuscar=new BuscarSocio("Terreno");
        
        jDesktopPane1.add(socioBuscar);
        socioBuscar.setLocation(20,20);

        socioBuscar.show();
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMetajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMetajeKeyPressed
  
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMetajeKeyPressed

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
            java.util.logging.Logger.getLogger(Terrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Terrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Terrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Terrenos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Terrenos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliActualizar;
    private javax.swing.JButton btnCliEliminar;
    private javax.swing.JButton btnCliGuardar;
    private javax.swing.JComboBox<String> cnbModulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxBuscar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTerreno;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdTerreno;
    private javax.swing.JTextField txtMetaje;
    public static javax.swing.JTextField txtSocio;
    private javax.swing.JTextField txtSolar;
    // End of variables declaration//GEN-END:variables

    private void verificarDatos() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String id = txtIdTerreno.getText();
        java.sql.Connection con;
        con = Conexion.GetConnection();
        try {
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("Select * from terreno where terreno.Id_Terreno like '" + id + "'");
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
        } else if (txtDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Direccion");
            txtDireccion.requestFocus(true);

        } else if (txtMetaje.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Metaje");
            txtMetaje.requestFocus(true);
        } else if (txtSocio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el socio");
            txtSocio.requestFocus(true);
        }// else if (txtAn.getText().isEmpty() ) {
        //  JOptionPane.showMessageDialog(null, "Ingrese la Año");
        //  txtAn.requestFocus(true);
        // }
        else {
            String Id_Terreno, Dir_Terreno, Id_Modulo, Socio;
            double Met_Terreno, Sol_Terrono;
            Id_Terreno = txtIdTerreno.getText();
            Met_Terreno = Double.valueOf(txtMetaje.getText());
            Dir_Terreno = txtDireccion.getText();
            Sol_Terrono = Double.valueOf(txtSolar.getText());
            Id_Modulo = String.valueOf(cnbModulo.getSelectedItem());
            Socio = txtSocio.getText();

            java.sql.Connection con;
            con = Conexion.GetConnection();
            String sql = "";
            sql = "insert into terreno(Id_Terreno,Met_Terreno,Dir_Terreno,Sol_Terrono,Id_Modulo,Socio) values (?,?,?,?,?,?) ";
            try {
                java.sql.PreparedStatement psd = con.prepareStatement(sql);

                psd.setString(1, Id_Terreno);
                psd.setDouble(2, Met_Terreno);
                psd.setString(3, Dir_Terreno);
                psd.setDouble(4, Sol_Terrono);
                psd.setString(5, Id_Modulo);
                psd.setString(6, Socio);

                int n = psd.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
                    limpiarTexto();
                    //     BloquearTexto();
                    //     bloquearBotonesInicio();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }

    private void limpiarTexto() {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        txtDireccion.setText("");
        txtIdTerreno.setText("");
        txtMetaje.setText("");
        txtSocio.setText("");
        txtSolar.setText("");
        cnbModulo.setSelectedIndex(-1);

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
