/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socios;
//guardaadoooo
import Conexion.Conexion;
import static Menu.Menu.jDesktopPane1;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego
 */
public class Socios extends javax.swing.JInternalFrame {

    /**
     * Creates new form Socios
     */
    public Socios() {
        initComponents();
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
        lblErrorcb.setText("");
        
        TablaSocios("");
        tblBusqueda.setVisible(true);
    }
    
    public  void Limpiar()
    {
        txtCedula.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        
        btnActualizar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    
     public void DesactivarCampos() {        
        txtCedula.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtNombres.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }
    //metodo donde crea la tabla de Socios
    DefaultTableModel m;

    public void TablaSocios(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"Cédula", "Apellidos", "Nombres", "Dirección", "Teléfono"};
        String reg[] = new String[6];
        String sentence = "";
        m = new DefaultTableModel(null, titles);

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
        m = new DefaultTableModel(null, titles);

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
        m = new DefaultTableModel(null, titles);

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
    
    public void InsertarSocios() {

        try {
            int cedula = Integer.parseInt(txtCedula.getText());
            String apellidos = txtApellidos.getText();
            String nombres = txtNombres.getText();
            String direccion = txtDireccion.getText();
            int telefono = Integer.parseInt(txtTelefono.getText());

            //SI LOS CAMPOS ESTAN LLENOS
            if (!txtCedula.getText().trim().isEmpty()
                    && !txtApellidos.getText().trim().isEmpty()
                    && !txtNombres.getText().trim().isEmpty()
                    && !txtDireccion.getText().trim().isEmpty()
                    && !txtTelefono.getText().trim().isEmpty()) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de insertar el socio?", "Confirmación", JOptionPane.YES_OPTION);

                if (JOptionPane.YES_OPTION == confirmado) {

                    //REGISTRAR LOS PRODUCTOS
                    Connection miConexion = (Connection) Conexion.GetConnection();
                    try {
                        Statement statement = (Statement) miConexion.createStatement();
                        statement.executeUpdate("CALL SP_InsertarSocios ('" + cedula + "','" + apellidos + "','" + nombres + "','" + direccion + "','" + telefono + "') ");
                        miConexion.close();
                        JOptionPane.showMessageDialog(this, "Se agrego correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                        Limpiar();

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cancelado correctamente", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    txtCedula.requestFocus();
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "No dejar vacio ningun campo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtCedula.requestFocus();
        }

    }
    
    public void Buscar_editar(String val) {

        Connection con;
        con = Conexion.GetConnection();
        String sentence = "";
        String ced = "", ape = "", nom = "", dir = "", tel = "";
        sentence = "CALL SP_SociosSel('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {                
                ced = rs.getString("Ced_Socio");
                ape = rs.getString("Apellido_Socio");
                nom = rs.getString("Nombre_Socio");
                dir = rs.getString("Dir_Socio");
                tel = rs.getString("Tel_Socio");

                txtCedula.setText(ced);
                txtApellidos.setText(ape);
                txtNombres.setText(nom);
                txtDireccion.setText(dir);
                txtTelefono.setText(tel);
                btnEliminar.setEnabled(false);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    String action = "Insertar";
    public void seleccionarfilamodificar() {
        int filasel;
        String id;
        try {
            filasel = tblBusqueda.getSelectedRow();
            if (filasel == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
                txtBusqueda.requestFocus();
            } else {
                txtApellidos.setEnabled(true);
                txtNombres.setEnabled(true);
                txtDireccion.setEnabled(true);
                txtTelefono.setEnabled(true);
                btnIngresar.setEnabled(false);
                btnActualizar.setEnabled(true);
                //btnEliminar.setEnabled(false);
                btnCancelar.setEnabled(true);

                txtBusqueda.setText("");
                tblBusqueda.setVisible(false);
                action = "Modificar";
                m = (DefaultTableModel) tblBusqueda.getModel();
                id = (String) m.getValueAt(filasel, 0);
                System.out.println("Id mandado para modificar"+id);
                Buscar_editar(id);
                TablaSocios("");
                txtBusqueda.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    
    public void ActualizarSocios()
    {
            Connection con;
        con = Conexion.GetConnection();
        String sentence = "";
        String msj = "";
        String ced, ape, nom, dir, tel;
        ced = txtCedula.getText();
        ape = txtApellidos.getText();
        nom = txtNombres.getText();
        dir = txtDireccion.getText();
        tel = txtTelefono.getText();

        //si los datos son diferentes de vacios
        if (!ced.isEmpty() && !ape.isEmpty() && !nom.isEmpty() && !dir.isEmpty() && !tel.isEmpty()) {

            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar el socio?", "Confirmación", JOptionPane.YES_OPTION);
            if (JOptionPane.YES_OPTION == confirmado) {

                sentence = "CALL SP_ModificarSocios('" + ced + "','" + ape + "','" + nom + "','" + dir + "','" + tel + "')";
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Socio modificado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

                try {
                    PreparedStatement pst = con.prepareStatement(sentence);
                    pst.executeUpdate();

                    txtCedula.setText("");
                    txtApellidos.setText("");
                    txtNombres.setText("");
                    txtDireccion.setText("");
                    txtTelefono.setText("");
                    btnIngresar.setEnabled(true);
                    btnActualizar.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    TablaSocios("");
                    //DesactivarCampos();

                    tblBusqueda.setVisible(false);
                    txtBusqueda.setText("");
                    txtBusqueda.requestFocus();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                txtBusqueda.setText("");
                TablaSocios("");
                JOptionPane.showMessageDialog(null, "Cancelado correctamente", "Información", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No dejar vacio ningun campo", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }
    }
    
   public void EliminarSocios(){
         int filasel;
        String id;
        try{
            filasel=tblBusqueda.getSelectedRow();
            if (filasel==-1) {
                JOptionPane.showMessageDialog(null,"No se ha seleccionado ninguna fila","Advertencia",JOptionPane.WARNING_MESSAGE);
                txtBusqueda.requestFocus();
            }else{
                int confirmado=javax.swing.JOptionPane.showConfirmDialog(null,"¿Realmente desea eliminar el socio?","Confirmación",JOptionPane.YES_OPTION);
                if(confirmado==JOptionPane.YES_OPTION){
                m=(DefaultTableModel) tblBusqueda.getModel();
                id=(String) m.getValueAt(filasel, 0);
                
                Connection con;
               con=Conexion.GetConnection();
                String sentence="";
                sentence="CALL SP_EliminarSocios("+id+")";
                
                try {
                       PreparedStatement pst=con.prepareStatement(sentence);
                       pst.executeUpdate();
                       JOptionPane.showMessageDialog(null,"Socio eliminado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
                        //Limpia de nuevo todos los campos
                       tblBusqueda.setVisible(false);
                       txtBusqueda.setText("");
                        txtBusqueda.requestFocus();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null,ex);
                            }
                            }else{
                            TablaSocios("");
                            tblBusqueda.setVisible(false);
                            txtBusqueda.setText("");
                             txtBusqueda.requestFocus();
                           JOptionPane.showMessageDialog(null, "Cancelado correctamente","Información",JOptionPane.ERROR_MESSAGE);
                                 }
                  }
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
  
    /**
     * This meth
     * od is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBusqueda = new javax.swing.JTable();
        cbDatoDeBusqueda = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        lblErrorcb = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Socios"); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ACCION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

        btnIngresar.setText("GUARDAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
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
                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jLabel2.setText("CEDULA:");

        jLabel3.setText("NOMBRE:");

        jLabel4.setText("APELLIDO:");

        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });

        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel7.setText("DIRECCION:");

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });

        jLabel8.setText("TELEFONO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDireccion))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LISTA DE CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 102, 153))); // NOI18N

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
        jScrollPane1.setViewportView(tblBusqueda);

        cbDatoDeBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE:", "CEDULA", "APELLIDO", "NOMBRE" }));
        cbDatoDeBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDatoDeBusquedaActionPerformed(evt);
            }
        });

        jLabel9.setText("BUSCAR POR:");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbDatoDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(lblErrorcb)
                        .addGap(0, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cbDatoDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblErrorcb))
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
                        .addGap(0, 24, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setBackground(new java.awt.Color(0, 51, 0));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cliente2.png"))); // NOI18N
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

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        InsertarSocios();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ActualizarSocios();

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarSocios();

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
         if (!Character.isDigit(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
         }
        
         if (txtCedula.getText().length() >= 10 ) 
                evt.consume();
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
     char c = evt.getKeyChar();
     int k=(int)evt.getKeyChar();
        
     if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
     }
        if (k>=97 && k<=122 || k >= 65 && k <= 90 || k==32){
            
        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
         
        
         if (txtNombres.getText().length() >= 40 ) 
                evt.consume();
    
    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
     char c = evt.getKeyChar();
     int k=(int)evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        } 
        
           if (k>=97 && k<=122 || k >= 65 && k <= 90 || k==32){
            
        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
         
        
         if (txtApellidos.getText().length() >= 40 ) 
                evt.consume();
    
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
         }
        
         if (txtTelefono.getText().length() >= 11 ) 
                evt.consume();
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
       int valor = cbDatoDeBusqueda.getSelectedIndex();

         if (txtApellidos.getText().length() >= 25 ) 
                evt.consume();
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

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        char c = evt.getKeyChar();
        int k=(int)evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        } 
        if (k>=97 && k<=122 || k >= 65 && k <= 90 || k>=45 && k<=57 || k==32 || k==35){
            
        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
         
        
         if (txtApellidos.getText().length() >= 25 ) 
                evt.consume();
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        int k=(int)evt.getKeyChar();
       
       if (k>=97 && k<=122 || k >= 65 && k <= 90 || k>=48 && k<=57 || k==32 ){
            
        }
        else{
            evt.setKeyChar((char)KeyEvent.VK_CLEAR);
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

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
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Socios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Socios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JComboBox<String> cbDatoDeBusqueda;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrorcb;
    private javax.swing.JTable tblBusqueda;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
