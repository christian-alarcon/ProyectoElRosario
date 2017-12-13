/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

/**
 *
 * @author Diego
 */

import Conexion.Conexion;
import Menu.Menu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login_Metodos {
    
 public static String Usuario;
    public static String Password;  
    
    public void IniciarSesion(JTextField txtUsuario,JTextField txtContrasena,JLabel lblError) {
        
        Usuario = txtUsuario.getText();
        Password = txtContrasena.getText();
        Connection con;
        con = Conexion.GetConnection();
        String consulta = "CALL SP_UsuariosAcceso ('" + Usuario + "','" + Password + "') ";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
           
            if (rs.first()) {
                if(rs.getString(1).equals(Usuario)&& rs.getString(2).equals(Password)){
                    Menu ob = new Menu();
                    
                    ob.setVisible(true);
                }
                System.out.println(rs.getString(1)+" "+rs.getString(2));

            }
            else {
                txtUsuario.setText("");
                txtContrasena.setText("");
                txtUsuario.requestFocus();
                lblError.setVisible(true);
            }
            rs.close();
            con.close();
        } catch (Exception SQL) {
            System.out.println(SQL.getMessage());
        }
    }
}