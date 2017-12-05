/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multas;

import Conexion.Conexion;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Chris
 */
public class Metodos_Multas {
    private Connection conexion;
    private String query;
    private Statement st;
    private PreparedStatement ps;
    
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
                
                //obtiene los nombre y apellidos segun cedula
                filas.add(getNombreApellidoUsuario(rs.getInt(5))[0]+" "+
                            getNombreApellidoUsuario(rs.getInt(5))[1]);
                
                columnas.add(filas);
                
            }
            st.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }//fin del mètodo
    
    public ArrayList<ArrayList> obtenerMultas(String where,String valor){
         ArrayList<ArrayList> columnas=new ArrayList<>();
        
        try {
            conexion=Conexion.GetConnection();
            query="SELECT * FROM MULTAS WHERE "+where+" ='"+valor+"'";
            //System.out.println(""+query);
            st=conexion.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            
            ArrayList<Object> filas=null;
            columnas=new ArrayList<ArrayList>();
                   
            while(rs.next()){

                filas=new ArrayList<Object>();
                filas.add(rs.getString(2));
                filas.add(rs.getDouble(3));
                filas.add(rs.getDate(4));
                
                //obtiene los nombre y apellidos segun cedula
                filas.add(getNombreApellidoUsuario(rs.getInt(5))[0]+" "+
                            getNombreApellidoUsuario(rs.getInt(5))[1]);
                
                columnas.add(filas);
                
            }
            st.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columnas;
    }
    
    public void cargarTabla(JTable tabla,ArrayList<ArrayList> datos1){
        ArrayList<ArrayList> datos=datos1;
        
        ArrayList<String> columnNames=new ArrayList<>();
        columnNames.add("Multa");
        columnNames.add("Valor");
        columnNames.add("Fecha");
        columnNames.add("Socio");
        DefaultTableModel modelo=new DefaultTableModel(columnNames.toArray(),0);
        
        //System.out.println(""+datos.size());
        for(int i=0;i<datos.size();i++){
                modelo.addRow(datos.get(i).toArray());
                for(int j=0;j<datos.get(0).size();j++){
                   // System.out.println("fila "+i+" "+datos.get(i).get(j));
                }
        }
        tabla.setModel(modelo);
      
    }
    
    public int insertar(String multa,double valor,String fecha,int id_socio){
            query="INSERT INTO MULTAS(Id_Multa,Nom_Multa,Val_Multa,Fec_Multa,Id_Socio)"+
                    "VALUES(?,?,?,?,?)";
            int v_retorno=0;
        try {
            
            conexion=Conexion.GetConnection();
            ps=conexion.prepareStatement(query);
            
            ps.setString(1, "2");
            ps.setString(2, multa);
            ps.setDouble(3, valor);
            ps.setString(4, fecha);
            ps.setInt(5,id_socio);
            
            if(ps.executeUpdate()==1){
                v_retorno=1;
            }

            ps.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v_retorno;
    }
    
    public static String fechaMySQL(JDateChooser miJDate){ 

        DecimalFormat sdf = new DecimalFormat("00"); 
        int anio = miJDate.getCalendar().get(Calendar. YEAR); 
        int mes = miJDate.getCalendar().get(Calendar. MONTH) + 1; 
        int dia = miJDate.getCalendar().get(Calendar. DAY_OF_MONTH); 

        return anio+"/"+sdf.format(mes)+"/"+sdf.format(dia); 
    }

    public String[] getNombreApellidoUsuario(int ced) {
            query="SELECT Apellido_Socio,Nombre_Socio FROM SOCIO WHERE CED_SOCIO='"+ced+"'";
            String[] socio=null;
        try {
            conexion=Conexion.GetConnection();
            st = conexion.createStatement();
            ResultSet rs=st.executeQuery(query);
            
            socio=new String[2];
            while (rs.next()) {                
                socio[0]=rs.getString(1);
                socio[1]=rs.getString(2);
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos_Multas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socio;
    }
    
    
    public void filtro(JComboBox comboFiltro,TableRowSorter trsFiltro,JTextField txtFiltro) {
        int columnaABuscar = 0;
        if (comboFiltro.getSelectedItem().toString() == "MOTIVO") {
            columnaABuscar = 0;
        }
        if (comboFiltro.getSelectedItem().toString() == "SOCIO") {
            columnaABuscar = 3;
        }
        
        trsFiltro.setRowFilter(RowFilter.regexFilter(txtFiltro.getText(), columnaABuscar));
  
        
    }//fin del metodo filtro
   
    
}//fin de la clase
