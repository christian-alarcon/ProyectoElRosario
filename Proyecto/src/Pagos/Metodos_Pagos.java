/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pagos;

import Conexion.Conexion;
import Multas.Metodos_Multas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jonathan
 */
public class Metodos_Pagos {

    private Connection conexion;
    private String query;
    private Statement st;
    private PreparedStatement ps;

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

    public ArrayList obtenerMultas() {
        ArrayList<ArrayList> columnas = null;

        try {
            conexion = Conexion.GetConnection();
            query = "SELECT * FROM pagos";
            st = conexion.createStatement();

            ResultSet rs = st.executeQuery(query);

            ArrayList<Object> filas;
            columnas = new ArrayList<ArrayList>();

            while (rs.next()) {

                filas = new ArrayList<Object>();

                filas.add(rs.getDouble(1));
                filas.add((String) getCuota(rs.getInt(1))[1]);
                filas.add(getCuota(rs.getInt(1))[2]);
                filas.add(rs.getDouble(4));
                filas.add(getCuota(rs.getInt(1))[0]);

                columnas.add(filas);

            }
            st.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }//fin del mètodo

    public void cargarTabla(JTable tabla, ArrayList<ArrayList> datos1) {
        ArrayList<ArrayList> datos = datos1;

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Pago");
        columnNames.add("Valor");
        columnNames.add("Fecha Pago");
        columnNames.add("Estado Pago");
        columnNames.add("Motivo");
        DefaultTableModel modelo = new DefaultTableModel(columnNames.toArray(), 0);

        //System.out.println(""+datos.size());
        for (int i = 0; i < datos.size(); i++) {
            modelo.addRow(datos.get(i).toArray());
            for (int j = 0; j < datos.get(0).size(); j++) {
                // System.out.println("fila "+i+" "+datos.get(i).get(j));
            }
        }
        tabla.setModel(modelo);

    }

    public int insertar(String id, Double valor, String fecha, String estado, int id_cuota) {
        query = "INSERT INTO pagos(id_pagos,val_pagado,fec_pago,estado_pago,id_cuota)"
                + "VALUES(?,?,?,?,?)";
        int v_retorno = 0;
        try {

            conexion = Conexion.GetConnection();
            ps = conexion.prepareStatement(query);

            ps.setString(1, id);
            ps.setDouble(2, valor);
            ps.setString(3, fecha);
            ps.setString(4, estado);
            ps.setInt(5, id_cuota);
                int n = ps.executeUpdate();
            if (n > 0) {
                v_retorno = 1;
            }
            
        } catch (SQLException ex) {
           // Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null, ex);
        }
        return v_retorno;
        
    }

        public void filtro(JComboBox comboFiltro,TableRowSorter trsFiltro,JTextField txtFiltro) {
        int columnaABuscar = 0;
        if (comboFiltro.getSelectedItem().toString() == "MOTIVO") {
            columnaABuscar = 6;
        }
        if (comboFiltro.getSelectedItem().toString() == "MODULO") {
            columnaABuscar = 7;
        }
        
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), columnaABuscar));
  
        
    }//fin del metodo filtro
    
                public void filtromultas(JComboBox comboFiltro,TableRowSorter trsFiltro,JTextField txtFiltro) {
        int columnaABuscar = 0;
        if (comboFiltro.getSelectedItem().toString() == "MOTIVO") {
            columnaABuscar = 1;
        }
        if (comboFiltro.getSelectedItem().toString() == "SOCIO") {
            columnaABuscar = 3;
        }
        
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), columnaABuscar));
  
        
    }
}
