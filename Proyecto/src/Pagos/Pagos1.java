package Pagos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Conexion.Conexion;
import Menu.Menu;
import Multas.Metodos_Multas;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Diego
 */
public class Pagos1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form Pagos
     */
    private Metodos_Pagos pagos;
    private ArrayList datos_busqueda;
    private Connection conexion;
    private String query;
    private Statement st;
    private PreparedStatement ps;
    DefaultTableModel modelo;
    private TableRowSorter trsFiltro;
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    String deuda;
    int pago=0;

    public Pagos1() {
        initComponents();
        //inicio();
        cargarTabla1("");

    }

    public void cancelar(){
        txtID.setText(null);
        txtModulo.setText(null);
        txtValor.setText(null);
        txtCuota.setText(null);
        txtEstado.setText(null);
    }
    
    private String[] getCuota(int ced) {
        query = "SELECT Mot_Cuota,Val_Cuota,Fec_Cuota FROM cuota WHERE Id_Cuota='" + ced + "'";
        String[] socio = null;
        try {
            conexion = Conexion.GetConnection();
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            socio = new String[2];
            while (rs.next()) {
                socio[0] = rs.getString(1);
                socio[1] = rs.getString(2);
                socio[2] = rs.getString(3);
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socio;
    }

    public void cargarTabla1(String Dato) {
        String[] titulos = {"ID MULTAS", "RAZON", "VALOR", "SOCIO", "ESTADO"};
        String[] registros = new String[5];
        modelo = new DefaultTableModel(null, titulos);

        Conexion cc = new Conexion();
        Connection cn = cc.GetConnection();
        String sql = "";
        sql = "SELECT multas.Id_Multa, multas.Nom_Multa, multas.Val_Multa, socio.Nombre_Socio, socio.Apellido_Socio, multas.estado_multa FROM multas " +
                "INNER JOIN socio ON multas.Id_Socio = socio.Ced_Socio ";
        try {
            Statement psd = cn.createStatement(); //Hace las consultas para poder visualizar lo q esta en la tabla
            ResultSet rs = psd.executeQuery(sql);//es vuna clase de java para optener los resultados del statement

            while (rs.next()) {
                //int pago = Integer.parseInt(rs.getString("pagos.id_pagos"));
                String socio = rs.getString("socio.Nombre_Socio") +" "+ rs.getString("socio.Apellido_Socio");
                registros[0] = rs.getString("multas.Id_Multa");
                registros[1] = rs.getString("multas.Nom_Multa");
                registros[2] = rs.getString("multas.Val_Multa");
                registros[3] = socio;
                registros[4] = rs.getString("multas.estado_multa");
                modelo.addRow(registros);
            }
            tblPagos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        int filas = modelo.getRowCount() + 1;
        txtID.setText(String.valueOf(filas));
    }

    public void cargarTabla(String Dato) {
        String[] titulos = {"ID MULTAS", "RAZON", "VALOR", "SOCIO", "ESTADO"};
        String[] registros = new String[5];
        modelo = new DefaultTableModel(null, titulos);

        Conexion cc = new Conexion();
        Connection cn = cc.GetConnection();
        String sql = "";
        sql = "SELECT multas.Id_Multa, multas.Nom_Multa, multas.Val_Multa, socio.Nombre_Socio, socio.Apellido_Socio, multas.estado_multa FROM multas " +
                "INNER JOIN socio ON multas.Id_Socio = socio.Ced_Socio WHERE  multas.estado_multa = 'pendiente'";
        try {
            Statement psd = cn.createStatement(); //Hace las consultas para poder visualizar lo q esta en la tabla
            ResultSet rs = psd.executeQuery(sql);//es vuna clase de java para optener los resultados del statement

            while (rs.next()) {
                //int pago = Integer.parseInt(rs.getString("pagos.id_pagos"));
                String socio = rs.getString("socio.Nombre_Socio") +" "+ rs.getString("socio.Apellido_Socio");
                registros[0] = rs.getString("multas.Id_Multa");
                registros[1] = rs.getString("multas.Nom_Multa");
                registros[2] = rs.getString("multas.Val_Multa");
                registros[3] = socio;
                registros[4] = rs.getString("multas.estado_multa");
                modelo.addRow(registros);
            }
            tblPagos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        int filas = modelo.getRowCount() + 1;
        txtID.setText(String.valueOf(filas));
    }

    public void inicio() {
        pagos = new Metodos_Pagos();

        datos_busqueda = pagos.obtenerMultas();
        pagos.cargarTabla(tblPagos, datos_busqueda);

    }

    private void insertar() {
        String id = txtID.getText();
        String motivo = txtEstado.getText();
        double valor = 0;
        String val = txtValor.getText().substring(2).replace(",", ".");

        valor = Double.valueOf(val);
        System.out.println("valor: " + valor);
        String fecha = Metodos_Multas.fechaMySQL(txtFecha);
        int id_cuota = Integer.parseInt(txtModulo.getText());

        int n = pagos.insertar(id, valor, fecha, motivo, id_cuota);

        if (n == 1) {

            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            //limpiar();
            cargarTabla("");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo insertar");
        }
    }

    private void guardar() {
        if (txtModulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar al Presidente");
            txtModulo.requestFocus();
        } else if ("".equals(String.valueOf(txtFecha.getDate()))) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el año");
            txtFecha.requestFocus();
        } else if ("".equals(txtValor.getText())) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el Valor");
            txtValor.requestFocus();
        } else if ("".equals(txtEstado.getText())) {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Motivo");
            txtEstado.requestFocus();
        }
//            txtCapacidad.setText("0");

        Conexion cc = new Conexion();
        Connection cn = cc.GetConnection();
        String sql = "";
        String id_pagos, val_pago, fec_pago, estado_pago, id_cuota;
        fec_pago = f.format(txtFecha.getDate());
        id_pagos = txtID.getText();
        val_pago = txtValor.getText();
        estado_pago = txtEstado.getText();
        id_cuota = txtModulo.getText();
        sql = "INSERT INTO pagos(id_pagos,val_pagado,fec_pago,estado_pago,id_cuota)"
                + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement psd = cn.prepareStatement(sql);    //permite mandar los datos a la base de datos mediante cn que es la coneccion dentro hay q mandar el insert que es el sql

            psd.setString(1, id_pagos); //parametros indican el campo(un numero entero empieza en 1) y lo q quiero mandar(las variable como la placa, marca, modelo, etc..)
            psd.setString(2, val_pago);
            psd.setString(3, fec_pago);
            psd.setString(4, estado_pago);
            psd.setString(5, id_cuota);
            int n = psd.executeUpdate(); //devuelve un numero entero que me devuelve que me afecto la base de datos 
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Se inserto correctamente");
                cargarTabla("");
                //limpiar();
//                bloquear();
//                botonlimpio();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void modificar() {        
            String fecha = f.format(txtFecha.getDate());
            Conexion cc = new Conexion();
            Connection cn = cc.GetConnection();
            String txtN = tblPagos.getValueAt(tblPagos.getSelectedRow(), 0).toString().trim();
            int valor = Integer.parseInt(txtValor.getText());
            String sql = "";
            sql = "update multas set estado_multa='PAGADA' where Id_Multa='" + txtID.getText() + "'";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "PAGO REALIZADO EXITOSAMENTE");
                    cargarTabla1("");
                    //limpiar();
                    //bloquearV();
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL REALIZAR PAGO");
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
        btnCancelar = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtModulo = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        modulo = new javax.swing.JLabel();
        txtCuota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jcbPendientes = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnPagar.setText("PAGAR");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPagar)
                .addGap(28, 28, 28)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("VALOR:  $");

        jLabel3.setText("ESTADO:");

        jLabel5.setText("SOCIO:");

        txtEstado.setEditable(false);
        txtEstado.setEnabled(false);

        txtID.setEnabled(false);

        jLabel7.setText("FECHA:");

        jLabel6.setText("ID MULTA:");

        txtModulo.setEditable(false);
        txtModulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtModulo.setEnabled(false);
        txtModulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtModuloMouseClicked(evt);
            }
        });

        txtFecha.setEnabled(false);

        txtCuota.setEditable(false);
        txtCuota.setEnabled(false);

        jLabel1.setText("MOTIVO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(txtModulo)
                    .addComponent(txtValor))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(modulo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCuota, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                        .addGap(16, 16, 16))))
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
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(modulo))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE PAGOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPagosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPagos);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE:", "MOTIVO", "SOCIO" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel9.setText("BUSCAR POR:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jcbPendientes.setText("PENDIENTES");
        jcbPendientes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jcbPendientesStateChanged(evt);
            }
        });
        jcbPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPendientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jcbPendientes)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbPendientes))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 78, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(0, 51, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente2.png"))); // NOI18N
        jLabel11.setText("PAGOS CUOTAS");

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
                        .addGap(21, 21, 21)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtModuloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtModuloMouseClicked
        
    }//GEN-LAST:event_txtModuloMouseClicked

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBuscar.getText());
                txtBuscar.setText(cadena);
                repaint();
                pagos.filtromultas(jComboBox1,trsFiltro,txtBuscar);
            }
        });
        trsFiltro = new TableRowSorter(tblPagos.getModel());
        tblPagos.setRowSorter(trsFiltro);    
        
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased

   
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosMouseClicked
                    int row = tblPagos.rowAtPoint(evt.getPoint());
            btnPagar.setEnabled(true);
       //txtIdMulta.setText(tblBusqueda.getValueAt(row, 0).toString());
    txtID.setText(tblPagos.getValueAt(row, 0).toString());
    
    txtModulo.setText(tblPagos.getValueAt(row, 3).toString());
    txtEstado.setText(tblPagos.getValueAt(row, 4).toString());
    txtCuota.setText(tblPagos.getValueAt(row, 1).toString());
    
    pago = Integer.parseInt(tblPagos.getValueAt(row, 2).toString());
    String valor=tblPagos.getValueAt(row, 2).toString();
    deuda = valor;
    txtValor.setText(valor);
    
    try {
        Date now = new Date();
        //String fecha1 = Metodos_Multas.fechaMySQL(txtFecha);
      //String fecha =tblPagos.getValueAt(row, 3).toString();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
      String fechaDate = formato.format(now);
      txtFecha.setDate(now);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    //setea la cedula del socio
    
    }//GEN-LAST:event_tblPagosMouseClicked

    private void jcbPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPendientesActionPerformed
        if(jcbPendientes.isSelected()){
            cargarTabla("");
        }else if(!jcbPendientes.isSelected()){
            cargarTabla1("");
        };
    }//GEN-LAST:event_jcbPendientesActionPerformed

    private void jcbPendientesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jcbPendientesStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPendientesStateChanged

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        
        if(Integer.parseInt(deuda)==Integer.parseInt(txtValor.getText())){
            modificar();
            cancelar();
            
        }else{
            
            JOptionPane.showMessageDialog(this, "La deuda a pagar es de $ "+deuda+ "\n y no puede pagar por partes");
            txtValor.setText(deuda.toString());
            
        }
        
    }//GEN-LAST:event_btnPagarActionPerformed

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
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JCheckBox jcbPendientes;
    private javax.swing.JLabel modulo;
    private javax.swing.JTable tblPagos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCuota;
    private javax.swing.JTextField txtEstado;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtModulo;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
