/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multas;

import static Menu.Menu.jDesktopPane1;
import Pagos.Pagos;
import Socios.Buscar_Socio;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Diego
 */
public class Multas extends javax.swing.JInternalFrame {
       private Metodos_Multas multas;
       private TableRowSorter trsFiltro;
       private Rectangle dimBusqueda;
       private ArrayList datos_busqueda;
       private static String ced_socio;
       
       
    /**
     * Creates new form Multas
     */
    public Multas() {
        initComponents();
        inicio();
        estado_inicio_controles(Color.WHITE);
        control_componentes();
        txtIdMulta.setHighlighter(null);
        txtValor.setHighlighter(null);
        ((JTextField) this.txtFechaIngreso.getDateEditor()).setHighlighter(null);
        
    }

    
    public void inicio(){
       multas=new Metodos_Multas();
       
       txtIdMulta.setText(multas.obtenerNuevoIdMulta());
       
       datos_busqueda=multas.obtenerMultas();
       multas.cargarTabla(tblBusqueda,datos_busqueda);
       
       
       
       ((JTextField) this.txtFechaIngreso.getDateEditor()).setEditable(false);
       
       iniciarFecha(txtFechaIngreso);
       
       txtFechaBusqueda.setVisible(false);
       
       txtValor.setText("$ ");
       
             
    }
    
    public void limpiar(){
        txtMotivo.setText("");
        txtValor.setText("$ ");
        txtSocio.setText("");
        
        txtIdMulta.setText(multas.obtenerNuevoIdMulta());
        
        iniciarFecha(txtFechaIngreso);
    }
    
    public void control_componentes(){
        btnInserta.setEnabled(false);
        btnCliActualizar.setEnabled(false);
        btnBuscarSocio.setEnabled(false);
    }
    
    public void estado_inicio_controles(Color color){
        txtIdMulta.setForeground(color);
        
        for (Component field : txtFechaIngreso.getComponents()) {
            if (field instanceof JTextField) {
                field.setForeground(color);
            }
        }
        txtValor.setForeground(color);
        
        txtMotivo.setEditable(false);
    }
    
    private void iniciarFecha(JDateChooser fecha){
        Calendar c2 = new GregorianCalendar();
        fecha.setCalendar(c2);
    }
    
    private void actualizar(){
        String id=txtIdMulta.getText();
        String multa=txtMotivo.getText().toUpperCase();
        double valor=0;
        String val=txtValor.getText().substring(2).replace(",",".");
        valor=Float.valueOf(val);
        System.out.println("valor: "+valor);
        String fecha=Metodos_Multas.fechaMySQL(txtFechaIngreso);
        int id_socio=Integer.parseInt(ced_socio);
        
        if(multas.actualizar(id,multa, valor, fecha, id_socio)==1){
            
            JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
            limpiar();
            datos_busqueda=multas.obtenerMultas();
            multas.cargarTabla(tblBusqueda, datos_busqueda);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        }
    }
    
    private void insertar(){
        String id=txtIdMulta.getText();
        String multa=txtMotivo.getText().toUpperCase();
        double valor=0;
        String val=txtValor.getText().substring(2).replace(",",".");
        valor=Float.valueOf(val);
        System.out.println("valor: "+valor);
        String fecha=Metodos_Multas.fechaMySQL(txtFechaIngreso);
        int id_socio=Integer.parseInt(ced_socio);
        
        if(multas.insertar(id,multa, valor, fecha, id_socio)==1){
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            limpiar();
            datos_busqueda=multas.obtenerMultas();
            multas.cargarTabla(tblBusqueda, datos_busqueda);
        }
        else{
            JOptionPane.showMessageDialog(null, "No se pudo insertar");
        }
    }
    
    public static void setSocio(String cedula,String nombres,String apellidos){
        if(cedula==""){
            txtSocio.setText("");
        }
        else{
            txtSocio.setText(apellidos+" "+nombres);
            ced_socio=cedula;
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
        btnInserta = new javax.swing.JButton();
        btnCliActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSocio = new javax.swing.JTextField();
        txtIdMulta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnBuscarSocio = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        txtFechaIngreso = new com.toedter.calendar.JDateChooser();
        txtValor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBusqueda = new javax.swing.JTable();
        jcboBusqueda = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        txtFechaBusqueda = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        btnInserta.setText("GUARDAR");
        btnInserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertaActionPerformed(evt);
            }
        });

        btnCliActualizar.setText("ACTUALIZAR");
        btnCliActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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
                    .addComponent(btnInserta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCliActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInserta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCliActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel2.setText("SOCIO:");

        jLabel3.setText("VALOR:");

        jLabel5.setText("MOTIVO:");

        txtSocio.setEditable(false);

        txtIdMulta.setEditable(false);

        jLabel7.setText("FECHA:");

        btnBuscarSocio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar1.png"))); // NOI18N
        btnBuscarSocio.setText("BUSCAR");
        btnBuscarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSocioActionPerformed(evt);
            }
        });

        jLabel6.setText("ID:");

        txtMotivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotivoActionPerformed(evt);
            }
        });
        txtMotivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMotivoKeyTyped(evt);
            }
        });

        txtValor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtValorFocusLost(evt);
            }
        });
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });
        txtValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdMulta, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(txtMotivo))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtValor)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarSocio)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtIdMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnBuscarSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE MULTAS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        tblBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

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

        jcboBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MOTIVO", "FECHA", "SOCIO" }));
        jcboBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcboBusquedaItemStateChanged(evt);
            }
        });
        jcboBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboBusquedaActionPerformed(evt);
            }
        });

        jLabel9.setText("BUSCAR POR:");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });

        txtFechaBusqueda.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtFechaBusquedaPropertyChange(evt);
            }
        });

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFechaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jcboBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtFechaBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
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
                        .addGap(0, 51, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("MULTAS");

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
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertaActionPerformed
       String mensaje="";
        if(txtMotivo.getText().equals("")){
           mensaje+="Debe ingresar un motivo\n";
        }
            if(!txtValor.getText().contains(",")){
                mensaje+="El valor de la multa debe tener un formato adecuado\n";
            }
                if(txtSocio.getText().equals("")){
                    mensaje+="Debe escoger un socio";
                }
        if(mensaje==""){
            insertar();
            estado_inicio_controles(Color.WHITE);
            btnInserta.setEnabled(false);
            txtMotivo.requestFocus();
        }
        else{
            JOptionPane.showMessageDialog(null, mensaje);
        }
        

    }//GEN-LAST:event_btnInsertaActionPerformed

    private void btnCliActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActualizarActionPerformed
       String mensaje="";
        if(txtMotivo.getText().equals("")){
           mensaje+="Debe ingresar un motivo\n";
        }
            if(!txtValor.getText().contains(",")){
                mensaje+="El valor de la multa debe tener un formato adecuado\n";
            }
                if(txtSocio.getText().equals("")){
                    mensaje+="Debe escoger un socio";
                }
        if(mensaje==""){
            actualizar();
            estado_inicio_controles(Color.WHITE);
            btnCliActualizar.setEnabled(false);
            //txtMotivo.requestFocus();
        }
        else{
            JOptionPane.showMessageDialog(null, mensaje);
        }
       
        
    }//GEN-LAST:event_btnCliActualizarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        btnInserta.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnCliActualizar.setEnabled(false);
        limpiar();
        estado_inicio_controles(Color.WHITE);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSocioActionPerformed

        Buscar_Socio socioBuscar=new Buscar_Socio("Multas");
        
        jDesktopPane1.add(socioBuscar);
        socioBuscar.setLocation(20,20);

        socioBuscar.show();

    }//GEN-LAST:event_btnBuscarSocioActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jcboBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcboBusquedaActionPerformed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
         txtBusqueda.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                String cadena = (txtBusqueda.getText());
                txtBusqueda.setText(cadena);
                repaint();
                multas.filtro_Busqueda(jcboBusqueda,trsFiltro,txtBusqueda);
            }
        });
        trsFiltro = new TableRowSorter(tblBusqueda.getModel());
        tblBusqueda.setRowSorter(trsFiltro);    
       
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void jcboBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcboBusquedaItemStateChanged
        
        txtBusqueda.setText("");
        txtBusqueda.requestFocus();

        if (jcboBusqueda.getSelectedItem().toString() == "FECHA") {
            txtFechaBusqueda.setVisible(true);
            txtBusqueda.setVisible(false);
            txtFechaBusqueda.requestFocus();
            iniciarFecha(txtFechaBusqueda);
        }else{
            txtFechaBusqueda.setVisible(false);
            txtBusqueda.setVisible(true);
        }
        tblBusqueda.setRowSorter(null);
        multas.cargarTabla(tblBusqueda, datos_busqueda);
    }//GEN-LAST:event_jcboBusquedaItemStateChanged

    private void txtFechaBusquedaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtFechaBusquedaPropertyChange

        try{
            if (jcboBusqueda.getSelectedItem().toString() == "FECHA") {

                ArrayList datos=multas.obtenerMultas(
                        "Fec_Multa",Metodos_Multas.fechaMySQL(txtFechaBusqueda));

                
                if(!datos.isEmpty()){
                    System.out.println("datos "+datos.size());
                    multas.cargarTabla(tblBusqueda,datos);
                }
                else{
                    multas.cargarTabla(tblBusqueda,datos);
                }
            }
        }catch(Exception ex){
            
        }
        
        
    }//GEN-LAST:event_txtFechaBusquedaPropertyChange

    private void txtMotivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoKeyTyped
        int k=(int)evt.getKeyChar();
        if (k>=97 && k<=122 || k >= 65 && k <= 90 || k>=48 && k<=57 || k==32){
            
        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
        
         if (txtMotivo.getText().length() >= 30 ) 
                evt.consume();
    }//GEN-LAST:event_txtMotivoKeyTyped

    private void txtValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyTyped
        //solo numeros y coma
        int k=(int)evt.getKeyChar();
        if (k >= 48 && k <= 57 || k==44){
            
        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }   
        
        //no repite la coma
        if(txtValor.getText().contains(",")){
            
             if(k==44)
                evt.consume();
        }
        
        //que no tipee en las 2 posiciones donde estan "$ "
        if(txtValor.getText().contains(",")){
            int pos=txtValor.getText().indexOf(",");
             
            if((txtValor.getText().length()-pos)>2){
                evt.consume();
            }
        }
        
        //que no digite en las 2 pos iniciales
        if(txtValor.getCaretPosition()<2)
            evt.consume();
        
         //limite a 10 caracteres
        if (txtValor.getText().length() >= 7 ) 
                evt.consume();
    }//GEN-LAST:event_txtValorKeyTyped

    private void txtValorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorKeyPressed
        int k=(int)evt.getKeyChar();
        if(txtValor.getText().length()<=2 || txtValor.getCaretPosition()<=2){
            if(k == KeyEvent.VK_BACK_SPACE || k==KeyEvent.VK_SPACE ){
                evt.consume();       
            }
        }
        
        if(txtValor.getCaretPosition()<=1){
            if(k == KeyEvent.VK_DELETE  ){
                evt.consume();       
            }
        }
        
    }//GEN-LAST:event_txtValorKeyPressed

    private void txtMotivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotivoActionPerformed
        txtValor.requestFocus();
    }//GEN-LAST:event_txtMotivoActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        btnBuscarSocio.requestFocus();
    }//GEN-LAST:event_txtValorActionPerformed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        txtMotivo.requestFocus();
    }//GEN-LAST:event_formMouseEntered

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        btnNuevo.setEnabled(false);
        btnCliActualizar.setEnabled(false);
        
        limpiar();
        estado_inicio_controles(Color.BLACK);
        txtMotivo.setEditable(true);
        txtMotivo.requestFocus();
        
        btnInserta.setEnabled(true);
        txtMotivo.setEnabled(true);
        btnBuscarSocio.setEnabled(true);
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBusquedaMouseClicked
        
            int row = tblBusqueda.rowAtPoint(evt.getPoint());
            
       //txtIdMulta.setText(tblBusqueda.getValueAt(row, 0).toString());
    txtSocio.setText(tblBusqueda.getValueAt(row, 4).toString());
    
    txtIdMulta.setText(tblBusqueda.getValueAt(row, 0).toString());
    txtMotivo.setText(tblBusqueda.getValueAt(row, 1).toString());
    
    String valor="$ "+ tblBusqueda.getValueAt(row, 2).toString();
    txtValor.setText(valor.replace(".", ","));
    
    try {
      String fecha =tblBusqueda.getValueAt(row, 3).toString();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
      Date fechaDate = formato.parse(fecha.replace('-', '/'));
      txtFechaIngreso.setDate(fechaDate);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    //setea la cedula del socio
    ced_socio=multas.getCedula(tblBusqueda.getValueAt(row, 0).toString());
     
        estado_inicio_controles(Color.BLACK);
        btnCliActualizar.setEnabled(true);
        txtMotivo.setEditable(true);
        btnBuscarSocio.setEnabled(true);
    }//GEN-LAST:event_tblBusquedaMouseClicked

    private void txtValorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorFocusLost
        if(txtValor.getText().contains(",")){
            int comaPos=txtValor.getText().indexOf(",");

            if(comaPos==txtValor.getText().length()-1){
                txtValor.setText(txtValor.getText()+"00");
            }
            
        }
        else{
            txtValor.setText(txtValor.getText()+",00");
        }
        
    }//GEN-LAST:event_txtValorFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           
        MessageFormat header=new MessageFormat("Lista de Socios");
        MessageFormat footer=new MessageFormat("Pagina");
        
        try {
          tblBusqueda.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        }
        catch(PrinterException pe) {
            JOptionPane.showMessageDialog(null, "No se pudo imprimir");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Multas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Multas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Multas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Multas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Multas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarSocio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCliActualizar;
    private javax.swing.JButton btnInserta;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JComboBox<String> jcboBusqueda;
    private javax.swing.JTable tblBusqueda;
    private javax.swing.JTextField txtBusqueda;
    private com.toedter.calendar.JDateChooser txtFechaBusqueda;
    private com.toedter.calendar.JDateChooser txtFechaIngreso;
    private javax.swing.JTextField txtIdMulta;
    private javax.swing.JTextField txtMotivo;
    public static javax.swing.JTextField txtSocio;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
