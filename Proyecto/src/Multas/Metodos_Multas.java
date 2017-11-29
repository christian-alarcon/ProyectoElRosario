/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multas;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Chris
 */
public class Metodos_Multas {
    Connection conexion;
    String query;
    Statement st;
    
    public ArrayList obtenerMultas(){
        ArrayList<ArrayList> columnas=null;
        
        try {
            conexion=Conexion.GetConnection();
            query="SELECT * FROM MULTAS";
            st=conexion.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            
            ArrayList<Object> filas;
            columnas=new ArrayList<ArrayList>();
            
            
            
            while(rs.next()){
                
                filas=new ArrayList<Object>();
                filas.add(rs.getString(2));
                filas.add(rs.getDouble(3));
                filas.add(rs.getDate(4));
                
                columnas.add(filas);
                
            }
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }//fin del mètodo
    
    public void cargarTabla(JTable tabla){
        ArrayList<ArrayList> datos=obtenerMultas();
        
        ArrayList<String> columnNames=new ArrayList<>();
        columnNames.add("Multa");
        columnNames.add("Valor");
        columnNames.add("Fecha");
        DefaultTableModel modelo=new DefaultTableModel(columnNames.toArray(),0);
        
        System.out.println(""+datos.size());
        for(int i=0;i<datos.size();i++){
                modelo.addRow(datos.get(i).toArray());
                for(int j=0;j<datos.get(0).size();j++){
                    System.out.println("fila "+i+" "+datos.get(i).get(j));
                }
        }
        tabla.setModel(modelo);
      
    }
}//fin de la clase
